package edu.pasudo123.study.web.member.service;

import edu.pasudo123.study.web.member.model.Container;
import edu.pasudo123.study.web.member.model.RetryHistory;
import edu.pasudo123.study.web.member.repository.ContainerRepository;
import edu.pasudo123.study.web.member.repository.RetryHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContainerService {

    private final ContainerRepository containerRepository;
    private final RetryHistoryRepository retryHistoryRepository;

    @Transactional
    public Container saveContainer() {
        final Container container = new Container();
        return containerRepository.save(container);
    }

    @Transactional(rollbackFor = UnsupportedOperationException.class)
    public RetryHistory failedChangeContainerStateById(final long containerId) {
        RetryHistory retryHistory = null;

        Container container = containerRepository.findById(containerId).get();
        try {
            container.stateToPending();
            throw new UnsupportedOperationException("Error while changing data");
        } catch (UnsupportedOperationException e) {
            log.error("=============> Error : {}", e.getMessage());
            return retryHistoryRepository.save(new RetryHistory(container.getName()));
        }
    }

    @Transactional(rollbackFor = UnsupportedOperationException.class)
    public RetryHistory failedOnCreateContainer() {
        final Container container = new Container();

        try {
            Container savedContainer = containerRepository.save(container);
            throw new UnsupportedOperationException("Force Error");
        } catch (UnsupportedOperationException e) {
            log.error("Error : {}", e.getMessage());
            return retryHistoryRepository.save(new RetryHistory(container.getName()));
        }
    }

}
