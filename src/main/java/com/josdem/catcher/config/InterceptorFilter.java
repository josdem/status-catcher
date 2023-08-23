package com.josdem.catcher.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class InterceptorFilter implements WebFilter {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    return chain
        .filter(exchange)
        .doFinally(
            request -> {
              exchange.getRequest().getHeaders().forEach((key, value) -> log.info("header: {} - {}", key, value));
              exchange.getAttributes().forEach((key, value) -> log.info("attribute: {} - {}", key, value));
            });
  }
}
