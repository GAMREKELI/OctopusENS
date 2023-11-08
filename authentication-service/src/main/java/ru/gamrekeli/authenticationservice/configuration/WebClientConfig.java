package ru.gamrekeli.authenticationservice.configuration;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebClientConfig {

//    @Autowired
//    private LoadBalancedExchangeFilterFunction filterFunction;
//
//    /*
//        Клиент сервиса user-service
//    */
//    @Bean
//    public WebClient userWebClient() {
//        return WebClient.builder()
//                .baseUrl("http://user-service")
//                .filter(filterFunction)
//                .build();
//    }
//
//    @Bean
//    public UserClient userClient() {
//        HttpServiceProxyFactory httpServiceProxyFactory
//                = HttpServiceProxyFactory
//                .builder(WebClientAdapter.forClient(userWebClient()))
//                .build();
//
//        return httpServiceProxyFactory.createClient(UserClient.class);
//    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
