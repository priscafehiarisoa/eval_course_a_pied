package ev.eval_course_a_pied.security;


import ev.eval_course_a_pied.services.auth.CustomUserDetailsService;
import ev.eval_course_a_pied.utils.Statics;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebSecurity
@Getter
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/jsp/");
        bean.setSuffix(".jsp");
        return bean;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        auth -> {
                            auth.requestMatchers(HttpMethod.GET,"/login","/register","registerAdmin","/loginAdmin","/accessDenied").permitAll();
                            auth.requestMatchers(HttpMethod.GET,"/public/**","/WEB-INF/**").permitAll();
                            auth.requestMatchers(HttpMethod.POST,"/register","registerAdmin").permitAll();
                            auth.requestMatchers(HttpMethod.GET,"/admin/**").hasAnyAuthority("ADMIN");
                            auth.requestMatchers(HttpMethod.POST,"/admin/**").hasAnyAuthority("ADMIN");
                            auth.requestMatchers(HttpMethod.GET,"/equipe/**").hasAnyAuthority(Statics.EQUIPEROLE);
                            auth.requestMatchers(HttpMethod.POST,"/equipe/**").hasAnyAuthority(Statics.EQUIPEROLE);
                            auth.anyRequest().authenticated();

                        })
                .exceptionHandling(
                        exception
                                -> exception.accessDeniedPage(
                                "/accessDenied"))
                .formLogin(
                        form -> {
                            form.loginPage("/login");
                            form.defaultSuccessUrl("/", true);
                            form.failureHandler((request, response, exception) -> {
                                if(request.getParameter("admin")!=null){
                                    response.sendRedirect("/loginAdmin?error");
                                }
                                else {
                                    response.sendRedirect("/login?error");
                                }
                            });
                        }
                )
                .logout(
                        logout -> {
                            logout.logoutUrl("/logout");
                            logout.logoutSuccessUrl("/login");
                            logout.permitAll();
                        }
                )
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(getUserDetailsService())
                .passwordEncoder(bCryptPasswordEncoder);
        return authenticationManagerBuilder.build();
    }

}
