package dev.j.api.restful.common.component.actuator;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ActuatorOsMemory {

  @Autowired
  private ComponentMemory componentMemory; 

  @Bean
  public Gauge osSystemTotalMemory(MeterRegistry registry) {
    return Gauge.builder("os.memory.total", () -> componentMemory.getTotalMemory()).register(registry);
  }

  @Bean
  public Gauge osSystemFreeMemory(MeterRegistry registry) {
    return Gauge.builder("os.memory.free", () -> componentMemory.getFreeMemory()).register(registry);
  }
}