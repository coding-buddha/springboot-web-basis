package edu.pasudo123.study.web.member.controller;

import edu.pasudo123.study.web.member.service.ContainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/container")
@RequiredArgsConstructor
public class ContainerController {

    private final ContainerService containerService;

    @GetMapping
    public ResponseEntity<Object> createContainer() {
        return ResponseEntity.ok().body(containerService.saveContainer());
    }

    @GetMapping("fail")
    public ResponseEntity<Object> failedOnCreateContainer() {
        return ResponseEntity.ok().body(containerService.failedOnCreateContainer());
    }
}
