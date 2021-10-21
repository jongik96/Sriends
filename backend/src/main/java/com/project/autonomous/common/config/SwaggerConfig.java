package com.project.autonomous.common.config;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    //3.0.0 http://localhost:8088/swagger-ui/index.html

    RequestParameterBuilder tokenBuilder = new RequestParameterBuilder();
    List<RequestParameter> headers = new ArrayList<>();

    private final ApiInfo apiInfo = new ApiInfoBuilder()
        .title("스포츠 동호회 관리 / 매칭(가제) 웹앱")
        .description("프로젝트 메인 API")
        .contact(new Contact("Name", "https://naver.com", "my@email.com"))
        .license("MIT License")
        .version("5.0")
        .build();

    // 하단의 코드는 글로벌한 설정이 필요할 때 주석해제하시고 추가하시면 되겠습니다.
    // swagger 내에서 글로벌하게 적용되어야 하는 헤더가 필요하다면 RequestParameterBuilder 하나 더 만들어서
    // aParameters에 추가하기.
//    public SwaggerConfig() {
//        tokenBuilder
//            .name("Authorization")
//            .description("jwtToken here")
//            .required(false)
//            .in("header")
//            .accepts(Collections.singleton(MediaType.APPLICATION_JSON));
//        headers.add(tokenBuilder.build());
//    }

    @Bean
    public Docket mainApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .globalRequestParameters(headers) // 글로벌 파라미터 필요시 추가하기
            .apiInfo(apiInfo)
            .groupName("User")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.project.autonomous.user.controller"))
            // api 필요한 class path 추가
            .paths(
                        PathSelectors.ant("/**/users/**")
//                        .or(PathSelectors.ant("/**/auth/**"))
//                PathSelectors.any()
            )
            .build()
            .useDefaultResponseMessages(false);
    }

    @Bean
    public Docket teamApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .globalRequestParameters(headers) // 글로벌 파라미터 필요시 추가하기
            .apiInfo(apiInfo)
            .groupName("Team")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.project.autonomous.team.controller"))
            // api 필요한 class path 추가
            .paths(
                        PathSelectors.ant("/**/teams/**")
//                        .or(PathSelectors.ant("/**/auth/**"))
//                PathSelectors.any()
            )
            .build()
            .useDefaultResponseMessages(false);
    }

}
