package ua.hudyma.ratingservice.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.client.RestTemplate;

@Configuration
@Log4j2
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .additionalInterceptors(bearerTokenInterceptor())
                .build();
    }

    @Bean
    public ClientHttpRequestInterceptor bearerTokenInterceptor() {
        return (request, body, execution) -> {
            var auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth instanceof JwtAuthenticationToken jwtAuth) {
                String token = jwtAuth.getToken().getTokenValue();
                request.getHeaders().add("Authorization", "Bearer " + token);
            }
            log.info(">>> BearerTokenInterceptor ratingservice active <<<");
            return execution.execute(request, body);
        };
    }
}

