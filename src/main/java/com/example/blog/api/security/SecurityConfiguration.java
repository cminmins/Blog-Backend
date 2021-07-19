package com.example.blog.api.security;

import com.example.blog.service.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }

    private static final String[] PERMIT_GET_URI = {
            "/aritcles/**",
            "/profiles/**",
            "tags"
    };

    private static final String[] PERMIT_POST_URI = {
            "/users",
            "/user/login"
    };

    private static final String[] AUTH_GET_URI = {
            "/articles/feed"
    };



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        Filter filter = new JwtFilter();
        http
                .csrf()
                    .disable()
                .httpBasic()
                    .disable()
                .formLogin()
                    .disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.GET, PERMIT_GET_URI)
                        .permitAll()
                    .antMatchers(HttpMethod.POST, PERMIT_POST_URI)
                        .permitAll()
                    .antMatchers(HttpMethod.OPTIONS)
                        .permitAll()
                    .antMatchers(HttpMethod.GET, AUTH_GET_URI)
                        .authenticated()
                    .anyRequest()
                        .authenticated();
        http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
