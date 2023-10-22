package com.josdem.catcher.model;

import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Product {

  private String name;

  @DecimalMin(value = "0", inclusive = false)
  private BigDecimal price;

  private Status status = Status.NEW;
}
