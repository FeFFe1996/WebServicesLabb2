package com.example.bff.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.stripPrefix;
import static org.springframework.cloud.gateway.server.mvc.filter.TokenRelayFilterFunctions.tokenRelay;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
@EnableWebSecurity
public class BffConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/**")
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index", "/index.html", "/views/**", "/css/**", "/js/**", "/error", "/public/**").permitAll()
                        .requestMatchers("/api/auth/register").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(Customizer.withDefaults())
                .oauth2Client(Customizer.withDefaults())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                );

        return httpSecurity.build();
    }

    @Bean
    public RouterFunction<ServerResponse> publicRegistrationRoute() {
        return route("public_registration_route")
                .POST("/api/auth/register", http())
                .before(uri("http://localhost:8081"))
                .before(request -> org.springframework.web.servlet.function.ServerRequest.from(request)
                        .headers(headers -> {
                            headers.remove("Cookie");
                            headers.remove("Authorization");
                        })
                        .build())
                .before(stripPrefix(0))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userRoute(){
        return route("secured_user_route")
                .GET("/api/users/**", http())
                .before(uri("http://localhost:8081"))
                .filter(tokenRelay())
                .build();
    }
}