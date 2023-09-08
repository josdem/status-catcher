package com.josdem.catcher.controller;

import com.josdem.catcher.model.Product;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class StatusControllerTest {

  private final WebTestClient webTestClient;
  private final Product product = new Product("PIZZA", new BigDecimal("0.00"));

  @Test
  @DisplayName("it stores status")
  void shouldStoreStatus(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    webTestClient
        .post()
        .uri("/catcher/sku")
        .bodyValue(BodyInserters.fromValue(product))
        .exchange()
        .expectStatus()
        .isOk();
  }

  @Test
  @DisplayName("it gets status")
  void shouldGetStatus(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    webTestClient.get().uri("/catcher/sku").exchange().expectStatus().isOk();
  }

  @Test
  @DisplayName("it gets empty status")
  void shouldGetEmptyStatus(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    webTestClient
        .get()
        .uri("/catcher/thisSkuDoesNotExist")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(Product.class)
        .isEqualTo(new Product());
  }
}
