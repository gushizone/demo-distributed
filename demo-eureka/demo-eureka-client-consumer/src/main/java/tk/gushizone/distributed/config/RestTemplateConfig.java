package tk.gushizone.distributed.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author gushizone@gmail.com
 * @date 2021-01-23 23:22
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate simpleRest() {
        return new RestTemplate();
    }


    @Bean
    @LoadBalanced
    public RestTemplate loadBalanceRest() {
        return new RestTemplate();
    }
}
