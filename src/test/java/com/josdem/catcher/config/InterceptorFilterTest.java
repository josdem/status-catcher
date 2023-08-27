package com.josdem.catcher.config;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
class InterceptorFilterTest {

  private InterceptorFilter interceptorFilter;
  @Mock private ServerWebExchange exchange;
  @Mock private WebFilterChain chain;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    interceptorFilter = new InterceptorFilter(false);
  }

  @Test
  @DisplayName("it filters messages")
  void shouldFilterMessages(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    when(chain.filter(exchange)).thenReturn(Mono.empty());
    interceptorFilter.filter(exchange, chain);
    verify(chain, Mockito.times(1)).filter(exchange);
  }
}
