package tk.gushizone.distributed.gateway.filter;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyResponseBodyGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * @author gushizone@gmail.com
 * @date 2021/8/1 10:04 下午
 */
@Slf4j
@Component
public class CacheGatewayFilterFactory extends AbstractGatewayFilterFactory<CacheGatewayFilterFactory.Config> {

    /**
     * 实际应用时应为分布式缓存
     */
    public static final ConcurrentMap<String, String> CACHE_MAP = Maps.newConcurrentMap();

    private final static String HEADER_GATEWAY_CACHE = "Gateway-Cache";

    @Resource
    private ModifyResponseBodyGatewayFilterFactory modifyResponseBodyGatewayFilterFactory;

    @Override
    public GatewayFilter apply(Config config) {
        return new OrderedGatewayFilter((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            // request 标识
            RequestIdentifier requestIdentifier = RequestIdentifier.builder()
                    .uri(request.getURI().toString())
                    .method(request.getMethodValue())
                    .params(request.getQueryParams().toSingleValueMap())
                    .build();
            String requestKey = DigestUtil.md5Hex(JSONUtil.toJsonStr(requestIdentifier));

            String cache = CACHE_MAP.get(requestKey);
            if (StringUtils.isNotBlank(cache)) {
                ResponseCache responseCache = JSONUtil.toBean(cache, ResponseCache.class);
                ServerHttpResponse response = exchange.getResponse();

                responseCache.getHeaders().forEach((k, v) -> response.getHeaders().add(k, v));
                response.getHeaders().add(HEADER_GATEWAY_CACHE, requestKey);
                log.info("cache获取成功");
                return response.writeWith(Flux.just(response.bufferFactory().wrap(responseCache.getBody().getBytes())));
            }

            GatewayFilter gatewayFilter = modifyResponseBodyGatewayFilterFactory.apply(cfg -> cfg.setRewriteFunction(String.class, String.class, (exc, s) -> {

                ServerHttpResponse response = exc.getResponse();
                // 获取 response 内容
                ResponseCache responseCache = ResponseCache.builder()
                        .headers(response.getHeaders().toSingleValueMap())
                        .body(s)
                        .build();

                CACHE_MAP.put(requestKey, JSONUtil.toJsonStr(responseCache));
                return Mono.just(s);
            }));

            return gatewayFilter.filter(exchange, chain);
            // 若大于NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER，modifyResponseBodyGatewayFilterFactory无法生效
        }, NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER - 1);
    }


    @Override
    public List<String> shortcutFieldOrder() {
        return Lists.newArrayList();
    }

    @Data
    public static class Config {

        private Long timeout;
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestIdentifier {

        private String uri;

        private String method;

        private Map<String, String> params;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseCache {

        private Map<String, String> headers;

        private String body;
    }
}
