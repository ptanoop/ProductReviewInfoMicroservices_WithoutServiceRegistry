package com.adidas.review.configuration;

import jdk.nashorn.internal.objects.annotations.Property;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${application.username}")
    private String APPLICATION_USERNAME;
    @Value("${application.password}")
    private String APPLICATION_PASSWORD;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception
    {
        log.info("APPLICATION_USERNAME="+APPLICATION_USERNAME);
        auth.inMemoryAuthentication()
                .withUser(APPLICATION_USERNAME)
                .password("{noop}"+APPLICATION_PASSWORD)
                .roles("USER");
    }

}
