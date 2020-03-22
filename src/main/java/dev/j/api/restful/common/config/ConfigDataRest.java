package dev.j.api.restful.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class ConfigDataRest {

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer(){
        return new RepositoryRestConfigurer() {
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.setBasePath("/");
                RepositoryDetectionStrategy repositoryDetectionStrategy = new RepositoryDetectionStrategy(){
                
                    @Override
                    public boolean isExported(RepositoryMetadata metadata) {
                        return true;
                    }
                };

                config.setRepositoryDetectionStrategy(repositoryDetectionStrategy);
            }
        };
    }
    
}