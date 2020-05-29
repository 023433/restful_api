package dev.j.api.restful.common.component.actuator;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ActuatorOsUptime {

  @Autowired
  private ComponentUptime componentUptime; 

  @Bean
  public Gauge systemUptime(MeterRegistry registry) {
    return Gauge.builder("os.uptime", () -> componentUptime.getSystemUptime()).register(registry);
  }

 
}