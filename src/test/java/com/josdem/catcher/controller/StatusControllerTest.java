package com.josdem.catcher.controller;

import com.josdem.catcher.model.Product;
import com.josdem.catcher.model.Status;
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
  private final Product product = new Product("PIZZA", new BigDecimal("15.99"), Status.NEW);

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

  @Test
  @DisplayName("it updates status")
  void shouldUpdateStatus(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());

    webTestClient
        .put()
        .uri("/catcher/sku/ACTIVE")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(Product.class)
        .value(product -> product.getStatus().equals(Status.ACTIVE));
  }

  @Test
  @DisplayName("it throws an exception when update status")
  void shouldNotUpdateStatusDueToProductDoesNotExist(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());

    webTestClient.put().uri("/catcher/doNotExist/ACTIVE").exchange().expectStatus().isNotFound();
  }
}
