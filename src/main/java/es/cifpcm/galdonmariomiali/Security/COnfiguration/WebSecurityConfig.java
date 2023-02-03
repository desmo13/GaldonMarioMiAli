package es.cifpcm.galdonmariomiali.Security.COnfiguration;


import es.cifpcm.galdonmariomiali.Security.Services.UserDetailsServiceImpl;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

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

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authorizeRequests(authorize -> authorize
                .requestMatchers("/login","/deleteCarrito/**","/addCarrito/**","/Imagenes/**", "/js/**", "/javascripts/**", "/Producto", "/obtenerMunicipio", "/","/show/**","/GetRegister","/PostRegister","/error" ).permitAll()
                //.requestMatchers("/create","/save","/delete","/update","/save").hasRole("gestores")
               // .requestMatchers("/Users","/UsersEdit/**","/UserUpdate","/UserDelete","/Usercreate","/UserSave").hasRole("1")
                .anyRequest().authenticated()
        );

        http.formLogin().loginProcessingUrl("/login").defaultSuccessUrl("/").permitAll().and().csrf().disable();
        return http.build();
    }
}
