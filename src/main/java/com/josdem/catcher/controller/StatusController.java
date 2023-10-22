package com.josdem.catcher.controller;

import com.josdem.catcher.model.Product;
import com.josdem.catcher.model.Status;
import jakarta.validation.Valid;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/catcher")
public class StatusController {

  private final Map<String, Product> memory = new ConcurrentHashMap<>();

  @PostMapping("/{key}")
  public Mono<Void> storeStatus(@PathVariable String key, @Valid @RequestBody Product product) {
    log.info("Storing status with key: {} and value: {}", key, product);
    memory.put(key, product);
    return Mono.empty();
  }

  @GetMapping("/{key}")
  public Mono<Product> getStatus(@PathVariable String key) {
    log.info("Getting status from key: {}", key);
    return Mono.just(memory.containsKey(key) ? memory.get(key) : new Product());
  }

  @PutMapping("/{key}/{status}")
  public Mono<Product> updateStatus(@PathVariable String key, @PathVariable String status) {
    log.info("Updating status with key: {} and status: {}", key, status);
    if(!memory.containsKey(key)){
      throw new NoSuchElementException("Key not found");
    }
    Product product = memory.get(key);
    product.setStatus(Status.valueOf(status));
    return Mono.just(product);
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<String> handleException() {
    return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
  }
}
