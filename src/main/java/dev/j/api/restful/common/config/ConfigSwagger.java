package dev.j.api.restful.common.config;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class ConfigSwagger {

  @Bean
  public Docket swaggerConfig(){
    return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(
                    Arrays.asList(
                        new ParameterBuilder()
                            .name("X-Auth-Token")
                            .description("accessToken")
                            .modelRef(new ModelRef("string"))
                            .parameterType("header")
                            .required(true)
                            .build()
                    )
                )
                .apiInfo(swaggerInfo())
                .select()
                .apis(RequestHandlerSelectors.any())              
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false);
  }

  private ApiInfo swaggerInfo() {
    return new ApiInfoBuilder()
                .title("REST API Documentation")
                .description("서버 API 연동 문서")
                .build();
  }

}