package com.security;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.services.UserService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Config extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class).csrf().disable();
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/order/**").authenticated()
                .antMatchers("/admin/user/update", "/admin/cart-detail/update").hasAuthority("MEMBER")
                //update lai thong tin, gio hang cua minh
                .antMatchers("/admin/**").hasAnyAuthority("ADMIN", "STAFF")
                .antMatchers("/author/**").hasAuthority("ADMIN")
                .anyRequest().permitAll();


        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/home", false)
                .failureUrl("/login");

        http.rememberMe()
                .tokenValiditySeconds(86400);

        http.exceptionHandling()
                .accessDeniedPage("/unauthoried");

        http.logout()
                .logoutUrl("/logoff")
                .logoutSuccessUrl("/logoff/success");
    }

    // Cơ chế mã hóa mật khẩu
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Cho phép truy xuất REST API từ domain khác
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
