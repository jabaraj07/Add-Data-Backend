package com.example.practice_backend_using_spring.configuration;

        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.web.SecurityFilterChain;
        import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class config {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//                .csrf(csrf -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/") )
//                )
                .csrf(csrf->csrf.ignoringRequestMatchers("/**","/","/signup","/login","/success"));
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/","/css/**", "/js/**", "/signup/**").permitAll()// Allow access to /signu
//                        .anyRequest().authenticated() // Secure all other endpoints
//                );
        return http.build();
    }
}
//                .formLogin(form -> form
//                        .loginPage("/login") // Specify custom login page
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout") // Default logout URL
//                        .logoutSuccessUrl("/login?logout") // Redirect to login page after logout
//                        .permitAll()
//                );