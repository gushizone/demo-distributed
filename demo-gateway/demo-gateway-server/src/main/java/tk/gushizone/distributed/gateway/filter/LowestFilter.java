package tk.gushizone.distributed.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * GlobalFilter 全局过滤器
 * @author gushizone@gmail.com
 * @date 2021/3/22 10:36 下午
 */
@Slf4j
@Component
public class LowestFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // pre
        StopWatch timer = new StopWatch();
        timer.start(exchange.getRequest().getURI().getRawPath());

        log.info("LowestFilter - pre: {}", exchange.getRequest().getURI().getRawPath());


//        exchange.getAttributes().put();

        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    // post
                    timer.stop();
                    log.info("LowestFilter - post: {}, {} millis", exchange.getResponse().getStatusCode(), timer.getTotalTimeMillis());
                })
        );
    }

    /**
     * 设置filter的顺序，越小 filter 越优先，pre 越优先，post 越落后。
     */
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
