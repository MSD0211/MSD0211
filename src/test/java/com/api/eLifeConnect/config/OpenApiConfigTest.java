package com.api.eLifeConnect.config;

import com.api.elifeconnect.config.OpenApiConfig;
import org.junit.jupiter.api.Test;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@SpringBootTest(classes = {
    com.api.elifeconnect.ELifeConnectApplication.class,
    OpenApiConfigTest.TestConfig.class
})
@ActiveProfiles("kenya-dev")
class OpenApiConfigTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void testKenyaProfileLoadsCorrectGroups() {
        // Common group should always load
        assertThat(context.containsBean("commonApi")).isTrue();
        GroupedOpenApi commonApi = context.getBean("commonApi", GroupedOpenApi.class);
        assertThat(commonApi.getGroup()).isEqualTo("Common");

        // Kenya group should load under "kenya-dev" profile
        assertThat(context.containsBean("kenyaApi")).isTrue();
        GroupedOpenApi kenyaApi = context.getBean("kenyaApi", GroupedOpenApi.class);
        assertThat(kenyaApi.getGroup()).isEqualTo("Kenya");

        // Lanka and Nepal groups should NOT load under "kenya-dev" profile
        assertThat(context.containsBean("lankaApi")).isFalse();
        assertThat(context.containsBean("nepalApi")).isFalse();
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public ClientRegistrationRepository clientRegistrationRepository() {
            return mock(ClientRegistrationRepository.class);
        }

        @Bean
        public OAuth2AuthorizedClientService oAuth2AuthorizedClientService() {
            return mock(OAuth2AuthorizedClientService.class);
        }

        @Bean
        public JwtDecoder jwtDecoder() {
            return mock(JwtDecoder.class);
        }
    }
}
