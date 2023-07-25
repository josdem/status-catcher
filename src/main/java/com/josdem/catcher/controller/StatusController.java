package com.josdem.catcher.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/catcher")
public class StatusController {

  @PostMapping("/{key}/{value}")
  public Mono<Void> storeStatus(@PathVariable String key, @PathVariable String value) {
    log.info("Storing status with key: {} and value: {}", key, value);
    return Mono.empty();
  }
}
