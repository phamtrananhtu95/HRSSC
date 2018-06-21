package com.hrssc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by Thien on 6/16/2018.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    @Autowired
    private BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.permission-query}")
    private String permissionQuery;

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/resources/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(permissionQuery)
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");

            }
        };
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and()
                .authorizeRequests()
                .mvcMatchers("/",
                        "/login",
                        "/register",
                        "/humanResource/**",
                        "/manage-project/**",
                        "/manage-companies/**",
                        "/manage-companies/**",
                        
                        
                        "/manage-manager/**",
                        "/load-form-info/**",
                        "/chooseDomain/create"
                ).permitAll()
//                .mvcMatchers("/manage-companies/**").hasAuthority("MANAGE_COMPANIES")
//                .mvcMatchers("/manage-manager/**").access("hasAuthority('MANAGE_MANAGER')")
//                .mvcMatchers("/criteria/**").access("hasAuthority('EDIT_PERMISSION') or hasAuthority('EDIT_FEEDBACK')")
//                .mvcMatchers("/template/**").access("hasAuthority('EDIT_PERMISSION') or hasAuthority('EDIT_FEEDBACK')")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").failureUrl("/login")
//                .failureHandler(new CustomAuthenticationFailureHandler())
//                .successHandler(new CustomAuthenticationSuccessHandler())
                .defaultSuccessUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/access-denied").and().httpBasic()
                .and().csrf().disable();
    }
}
