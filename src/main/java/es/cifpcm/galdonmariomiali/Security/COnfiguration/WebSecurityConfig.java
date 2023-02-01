package es.cifpcm.galdonmariomiali.Security.COnfiguration;

import es.cifpcm.galdonmariomiali.Security.Services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    //@Bean
   // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     //   auth.authenticationProvider(authenticationProvider());
    //}//

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/Imagenes/**","/js/**","/","/Producto").permitAll()
                        .requestMatchers("/Users","/UserEdit/**","/UserUpdate","/UserDelete","/UserSave").hasRole("administradores")
                        .requestMatchers("/create","/save","/delete","Update","/edit/**").hasRole("gestores")
                        .anyRequest().authenticated()
                );
        http.formLogin().permitAll();
        return http.build();
    }
}
