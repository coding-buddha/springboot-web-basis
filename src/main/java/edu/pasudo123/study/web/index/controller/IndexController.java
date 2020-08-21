package edu.pasudo123.study.web.index.controller;

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
}
