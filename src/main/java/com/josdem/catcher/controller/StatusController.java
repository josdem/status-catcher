package com.josdem.catcher.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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

  private final Map<String, String> memory = new ConcurrentHashMap<>();

  @PostMapping("/{key}/{value}")
  public Mono<Void> storeStatus(@PathVariable String key, @PathVariable String value) {
    log.info("Storing status with key: {} and value: {}", key, value);
    memory.put(key, value);
    return Mono.empty();
  }

  @GetMapping("/{key}")
  public Mono<String> getStatus(@PathVariable String key) {
    log.info("Getting status from key: {}", key);
    return Mono.just(memory.containsKey(key) ? memory.get(key) : " ");
  }
}
