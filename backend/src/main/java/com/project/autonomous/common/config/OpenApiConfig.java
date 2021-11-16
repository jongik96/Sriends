package com.project.autonomous.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Properties;

@Component
public class OpenApiConfig {

    // http://localhost:8080/api/swagger-ui.html
    // http://k5d106.p.ssafy.io:8080/api/swagger-ui.html
    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String appVersion) {
        Info info = new Info().title("S-riends").version(appVersion)
            .description("API 명세")
            .termsOfService("https://www.notion.so/PJT-8c6d7f34574343fda50ceac6f9a0acdb")
            .contact(new Contact().name("D106").url("https://s-riends.me/").email(""))
            .license(new License().name("Apache License Version 2.0")
                .url("http://www.apache.org/licenses/LICENSE-2.0"));

//        List<Server> servers = Arrays.asList(new Server().url(url).description("demo (" + active +")"));

        SecurityScheme securityScheme = new SecurityScheme()
            .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
            .in(SecurityScheme.In.HEADER).name("Authorization");
        SecurityRequirement schemaRequirement = new SecurityRequirement().addList("bearerAuth");

        return new OpenAPI()
            .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
            .addSecurityItem(schemaRequirement)
            .security(Arrays.asList(schemaRequirement))
            .info(info);
//            .servers(servers);
    }

    @Bean
    public GroupedOpenApi userApi() {
        String packagesToscan[] = {"com.project.autonomous.user.controller"};
        return GroupedOpenApi.builder().group("Users").packagesToScan(packagesToscan)
            .build();
//        return GroupedOpenApi.builder()
//            .group("Users")
//            .pathsToMatch("/auth/**")
//            .pathsToMatch("/users/**")
//            .build();
    }

    @Bean
    public GroupedOpenApi teamApi() {
        String packagesToscan[] = {"com.project.autonomous.team.controller"};
        return GroupedOpenApi.builder().group("Teams").packagesToScan(packagesToscan)
            .build();
    }

    @Bean
    public GroupedOpenApi matchBoardApi() {
        String packagesToscan[] = {"com.project.autonomous.matchboard.comments.controller",
            "com.project.autonomous.matchboard.posts.controller"};
        return GroupedOpenApi.builder().group("MatchBoard").packagesToScan(packagesToscan)
            .build();
    }

    @Bean
    public GroupedOpenApi pictureApi() {
        return GroupedOpenApi.builder()
            .group("Pictures")
            .pathsToMatch("/picture/**")
            .build();
    }

    @Bean
    public GroupedOpenApi chat(){
        return GroupedOpenApi.builder()
                .group("Chat")
                .pathsToMatch("/chat/**")
                .build();
    }
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("ssafyD106@gmail.com");
        mailSender.setPassword("FinalProjD106");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

}
