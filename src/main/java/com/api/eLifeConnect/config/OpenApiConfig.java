package com.api.elifeconnect.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class OpenApiConfig {

        private static final String SECURITY_SCHEME_NAME = "Bearer Authentication";

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                                .info(new Info()
                                                .title("eLife Connect API Portal")
                                                .version("1.0")
                                                .description("API Documentation for eLife Connect Services."))
                                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                                .components(new Components()
                                                .addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
                                                                .name(SECURITY_SCHEME_NAME)
                                                                .type(SecurityScheme.Type.HTTP)
                                                                .scheme("bearer")
                                                                .bearerFormat("JWT")
                                                                .in(SecurityScheme.In.HEADER)));
        }

        @Bean
        @Profile({ "kenya-dev", "kenya-test", "kenya-prod" })
        public GroupedOpenApi kenyaApi() {
                return GroupedOpenApi.builder()
                                .group("Kenya")
                                .packagesToScan("com.api.elifeconnect.controller.kenya")
                                .pathsToMatch("/api/v1/kenya/**", "/api/mobile/**", "/api/web/**", "/api/client-info")
                                .build();
        }

        @Bean
        @Profile({ "lanka-dev", "lanka-test", "lanka-prod" })
        public GroupedOpenApi lankaApi() {
                return GroupedOpenApi.builder()
                                .group("Lanka")
                                .packagesToScan("com.api.elifeconnect.controller.lanka")
                                .pathsToMatch("/api/v1/lanka/**")
                                .build();
        }

        @Bean
        @Profile({ "nepal-dev", "nepal-test", "nepal-prod" })
        public GroupedOpenApi nepalApi() {
                return GroupedOpenApi.builder()
                                .group("Nepal")
                                .packagesToScan("com.api.elifeconnect.controller.nepal")
                                .pathsToMatch("/api/v1/nepal/**")
                                .build();
        }

        @Bean
        public GroupedOpenApi commonApi() {
                return GroupedOpenApi.builder()
                                .group("Common")
                                .packagesToScan("com.api.elifeconnect.controller.common",
                                                "com.api.elifeconnect.controller")
                                .packagesToExclude(
                                                "com.api.elifeconnect.controller.kenya",
                                                "com.api.elifeconnect.controller.lanka",
                                                "com.api.elifeconnect.controller.nepal")
                                .pathsToMatch("/api/token/**", "/api/public/**", "/sample/**")
                                .build();
        }
}
