package com.josdem.catcher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class CatcherApplicationTests {

  private final ApplicationContext applicationContext;

  @Test
  void contextLoads(TestInfo testInfo) {
    log.info("Running test: {}", testInfo.getDisplayName());
    CatcherApplication.main(new String[] {});
    assertNotNull(applicationContext, "The application context should not be null");
  }
}
