package edu.pasudo123.study.web.member.service;

import edu.pasudo123.study.web.member.model.Container;
import edu.pasudo123.study.web.member.model.RetryHistory;
import edu.pasudo123.study.web.member.repository.ContainerRepository;
import edu.pasudo123.study.web.member.repository.RetryHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

    @Transactional
    public RetryHistory failedOnCreateContainer() {
        final Container container = new Container();

        try {
            // 롤백이 안되네 ?
            Container savedContainer = containerRepository.save(container);
            throw new RuntimeException("에러 강제 발생");
        } catch(RuntimeException e) {
            log.error("Error : {}", e.getMessage());
            // 저장도 됨.
            return retryHistoryRepository.save(new RetryHistory(container.getName()));
        }
    }
}
