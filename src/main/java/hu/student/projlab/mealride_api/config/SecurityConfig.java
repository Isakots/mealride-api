package hu.student.projlab.mealride_api.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${username.query}")
    private String usernameQuery;

    @Value("${password.query}")
    private String pwQuery;

    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }


    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder())
                .and()
                .jdbcAuthentication()
                .usersByUsernameQuery(usernameQuery)
                .authoritiesByUsernameQuery(pwQuery)
                .dataSource(dataSource);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/*").permitAll();
                /*.httpBasic()

                .and()

                .authorizeRequests()
                // Guest access configuration
                .antMatchers("/", "/restaurant","/registration","/fragments/**")
                .permitAll()

                .antMatchers("/restaurant/incoming-orders","/restaurant/menu")
                .access("hasRole('RESTWORKER')")

                .antMatchers("/restaurant/**")
                .access("hasRole('RESTADMIN')")

                // Access of admin pages configuration
                .antMatchers("/administration/**")
                .access("hasRole('ADMIN')")

                // Only authenticated users can see other pages
                .anyRequest().authenticated()


                // Logout configuration
                .and()
                .logout().permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);*/
     }

}