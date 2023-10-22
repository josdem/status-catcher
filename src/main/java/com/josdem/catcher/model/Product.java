package com.josdem.catcher.model;

import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  private String name;

  @DecimalMin(value = "0", inclusive = false)
  private BigDecimal price;

  private final Status status = Status.NEW;
}
