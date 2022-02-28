package tk.gushizone.distributed.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import tk.gushizone.distributed.gateway.filter.CacheGatewayFilterFactory;

/**
 * @author gushizone@gmail.com
 * @date 2021/3/22 10:45 下午
 */
@Configuration
public class GatewayRouteConfig {

    @Autowired
    private CacheGatewayFilterFactory cacheGatewayFilterFactory;

    @Bean
    @Order
    @ConditionalOnProperty(name = "demo.gateway.cache.enabled", havingValue = "true")
    public RouteLocator cache(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(r -> r.path("/demo-client1/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .filter(cacheGatewayFilterFactory.apply(new CacheGatewayFilterFactory.Config()))
                        ).uri("lb://DEMO-CLIENT1"))
                .build();
    }


}
