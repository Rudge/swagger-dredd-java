package com.github.rudge.poc.config;

import com.fasterxml.classmate.TypeResolver;
import com.github.rudge.poc.controller.UserController;
import com.github.rudge.poc.domain.ResponseError;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {

        StopWatch watch = new StopWatch();
        watch.start();
        List<ResponseMessage> responses = new ArrayList<>();
        ModelRef modelRef = new ModelRef(ResponseError.class.getSimpleName());
        responses.add(new ResponseMessageBuilder().code(INTERNAL_SERVER_ERROR.value())
               .message(INTERNAL_SERVER_ERROR.getReasonPhrase()).responseModel(modelRef).build());

        // @formatter:off
        Docket docket= new Docket(SWAGGER_2)
                .apiInfo(apiInfo())
                .genericModelSubstitutes(ResponseEntity.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage(UserController.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responses)
                .globalResponseMessage(RequestMethod.POST, responses)
                .globalResponseMessage(RequestMethod.PUT, responses)
                .globalResponseMessage(RequestMethod.PATCH, responses)
                .globalResponseMessage(RequestMethod.DELETE, responses);
        // @formatter:on
        watch.stop();
        return docket;
    }

    @Bean
    public UiConfiguration uiConfig() {
        return new UiConfiguration(null);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Test Swagger API")
                .description("API para teste do swagger")
                .termsOfServiceUrl("")
                .contact(contact())
                .license("Teste")
                .licenseUrl("")
                .version("1.0")
                .build();
    }

    @Bean
    public Contact contact() {
        return new Contact("TestLabs", "","");
    }
}
