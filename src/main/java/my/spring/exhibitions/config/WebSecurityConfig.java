package my.spring.exhibitions.config;

import my.spring.exhibitions.entity.RoleType;
import my.spring.exhibitions.serviice.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/registration").permitAll();
//                .antMatchers("/login").not().fullyAuthenticated()
//                .antMatchers("/resources/**", "/", "/main", "/error").permitAll()
//                .antMatchers("/admin_exhibition", "/admin_hall").hasAuthority(RoleType.ADMIN.name())
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .exceptionHandling().accessDeniedPage("/error")
//                .and()
//                .logout()
//                .permitAll()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login")
//                .invalidateHttpSession(true);
    }

}
