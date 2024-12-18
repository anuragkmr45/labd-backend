// package com.labd.labd.config;

// import io.swagger.v3.oas.models.Components;
// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.info.Contact;
// import io.swagger.v3.oas.models.info.Info;
// import io.swagger.v3.oas.models.security.SecurityRequirement;
// import io.swagger.v3.oas.models.security.SecurityScheme;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class SwaggerConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 .info(new Info()
//                         .title("LabD API Documentation")
//                         .description("API documentation for the LabD application.")
//                         .version("1.0.0")
//                         .contact(new Contact().name("LabD Support").email("support@labd.com")))
//                 .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
//                 .components(new Components()
//                         .addSecuritySchemes("Bearer Authentication", new SecurityScheme()
//                                 .name("Authorization")
//                                 .type(SecurityScheme.Type.HTTP)
//                                 .scheme("bearer")
//                                 .bearerFormat("JWT")));
//     }
// }