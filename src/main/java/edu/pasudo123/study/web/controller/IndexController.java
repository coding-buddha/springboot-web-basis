package edu.pasudo123.study.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping
    public ResponseEntity<String> index(){
        return ResponseEntity.ok().body("index");
    }

    @GetMapping("/student/{id}/{name}/{age}")
    public ResponseEntity<String> createStudent(@PathVariable("id") String id,
                                                @PathVariable("name") String name,
                                                @PathVariable("age") int age) {

        log.info("id : {}, name : {}, age : {}", id, name, age);

        return ResponseEntity.ok().body("student");
    }

    @GetMapping("/opt-student/{id}/{name}/{age}/{gender}")
    public ResponseEntity<String> optCreateStudent(@PathVariable final Map<String, Object> variables) {

        variables.entrySet().stream()
                .forEach(entry -> log.info("key : {}, value : {}", entry.getKey(), entry.getValue()));

        return ResponseEntity.ok().body("opt-student");
    }
}
