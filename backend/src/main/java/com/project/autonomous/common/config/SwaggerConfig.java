package com.project.autonomous.common.config;


import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    //3.0.0 http://localhost:8088/swagger-ui/index.html

    private final TypeResolver typeResolver;

    RequestParameterBuilder tokenBuilder = new RequestParameterBuilder();
    List<RequestParameter> headers = new ArrayList<>();

    private final ApiInfo apiInfo = new ApiInfoBuilder()
        .title("S-riends")
        .description("프로젝트 메인 API")
        .contact(new Contact("Name", "https://naver.com", "my@email.com"))
        .license("MIT License")
        .version("5.0")
        .build();

    public SwaggerConfig() {
        typeResolver = new TypeResolver();
    }

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
                        .or(PathSelectors.ant("/**/auth/**"))
//                PathSelectors.any()
            )
            .build()
            .useDefaultResponseMessages(false);
    }

    @Bean
    public Docket teamApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .alternateTypeRules(
                AlternateTypeRules.newRule(typeResolver.resolve(Pageable.class), typeResolver.resolve(Page.class)))
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

    // Swagger JPA pageable 적용
    @Getter
    @Setter
    @ApiModel
    static class Page {
        @ApiModelProperty(value = "페이지 번호(0..N)")
        private Integer page;

        @ApiModelProperty(value = "페이지 크기", allowableValues="range[0, 100]")
        private Integer size;

        @ApiModelProperty(value = "정렬(사용법: 컬럼명,ASC|DESC)")
        private List<String> sort;
    }
}
