package br.letscode.configuration;

import br.letscode.configuration.security.JWTAuthenticationFilter;
import br.letscode.configuration.security.JWTAuthorizationFilter;
import br.letscode.configuration.security.UserServiceImpl;
import net.bytebuddy.agent.builder.AgentBuilder.Ignored;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity //Para ter a possibilidade de customizar
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class WebSecurityConfig {

    final UserServiceImpl userDetailsService;

    //Inicializa o servico, que sera utilizado para fazer a autenticacao.
    public WebSecurityConfig(UserServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    //objeto bean para a criptacao das senhas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //Todas as requests passarao por esse filtro
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //Criacao do AuthenticationManager, servico que cuida das autenticacoes. No caso, userDetailsService
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        //this will allow frames with same origin which is much more safe
        http.headers().frameOptions().sameOrigin(); // h2

        http.cors().and().csrf().disable()//Habilita CORS (Cross-origin resource sharing, requests vindas de outro domain sao permitidas) e desabilita CSRF (Cross-site request forgery)
                .authorizeRequests()
                    //Requests para as URLs e metodos abaixo sao  permitidas SEM AUTENTICACAO
                    .antMatchers(HttpMethod.POST, "/cliente").permitAll()
                    .antMatchers("/instances/**").permitAll()
                    .antMatchers("/actuator/**").permitAll()
                    
                    // .antMatchers(HttpMethod.GET,"/pedido/**").permitAll()
                    // .antMatchers(HttpMethod.POST,"/pedido/**").permitAll()
                    // .antMatchers(HttpMethod.PUT,"/pedido/**").permitAll()
                    // .antMatchers(HttpMethod.DELETE,"/pedido/**").permitAll()
                    // .antMatchers(HttpMethod.GET,"/cliente").permitAll()

                    .antMatchers(HttpMethod.GET, "/produtos/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/produtos").permitAll()
                    // H2 database
                    .antMatchers("/h2/**").permitAll()
                    .antMatchers("/h2/login.jsp?jsessionid=*").permitAll()
                    //Swagger
                    .antMatchers("/api-docs/**").permitAll()
                    .antMatchers("/swagger-ui/**").permitAll()
                    .antMatchers("/v3/api-docs/**").permitAll()

                .anyRequest().authenticated() //Qualquerr outra requisicao obriga autenticacao
                .and()
                //Filtros para as outras requisicoes, que requerem autenticacao e autorizacaoo
                .addFilter(new JWTAuthenticationFilter(authenticationManager)) //autenticacao
                .addFilter(new JWTAuthorizationFilter(authenticationManager)) //autorizacao
                //Indicar qual authentication manager sera um utilizado
                .authenticationManager(authenticationManager)
                //Criar policy
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                // .and().headers().frameOptions();
                // http.headers().frameOptions().disable();
                // http.headers().frameOptions().sameOrigin();

        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*");
            }
        };
    }

}
