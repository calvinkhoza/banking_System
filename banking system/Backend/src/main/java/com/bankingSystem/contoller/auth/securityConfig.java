// auth/SecurityConfig.java
package com.banking.system.auth;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/api/auth/**", "/form.html", "/interface.html").permitAll()
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/interface.html")
                .loginProcessingUrl("/api/auth/login")
                .defaultSuccessUrl("/dashboard.html", true)
            .and()
            .logout()
                .logoutUrl("/api/auth/logout")
                .logoutSuccessUrl("/interface.html");
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}