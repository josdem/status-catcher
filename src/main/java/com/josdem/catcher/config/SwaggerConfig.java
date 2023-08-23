package com.josdem.catcher.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class SwaggerConfig {

  @Value("${swagger.server}")
  private String swaggerServer;

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI().servers(Arrays.asList(new Server().url(swaggerServer)));
  }
}
