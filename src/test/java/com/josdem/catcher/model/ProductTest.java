package com.josdem.catcher.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {

  @Test
  @DisplayName("Should create a product")
  void shouldCreateAProduct() {
    Product product = new Product();
    assertEquals(product.getStatus(), Status.NEW, "should have status NEW");
  }
}
