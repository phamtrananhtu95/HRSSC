package com.example.HRSSC.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

/**
 * Created by Thien on 6/11/2018.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    private BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/resources/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().usersByUsernameQuery("Select u.username, u.password, u.id from hrssc.user u where u.username=?")
                .authoritiesByUsernameQuery("Select ur.username, prp.title from(Select u.username, r.id from hrssc.user u inner join hrssc.role r on(u.role_id=r.id) where u.username = ?)ur inner join (Select p.title, pr.role_id from hrssc.role_permissons pr inner join hrssc.permisson p on(pr.permisson_id = p.id))prp where ur.id = prp.role_id")
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/",
                        "/login"
                ).permitAll()
                .mvcMatchers("/roles/**").hasAuthority("EDIT_PERMISSION")
                .mvcMatchers("/users/**").access("hasAuthority('EDIT_USER')")
                .mvcMatchers("/criteria/**").access("hasAuthority('EDIT_PERMISSION') or hasAuthority('EDIT_FEEDBACK')")
                .mvcMatchers("/template/**").access("hasAuthority('EDIT_PERMISSION') or hasAuthority('EDIT_FEEDBACK')")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").failureHandler(new CustomAuthenticationFailureHandler())
                .successHandler(new CustomAuthenticationSuccessHandler())
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
    }
}
