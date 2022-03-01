package tk.gushizone.distributed.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.gushizone.distributed.sentinel.api.SentinelProducerApi;
import tk.gushizone.distributed.sentinel.config.feign.SentinelProducerBreakableApi;
import tk.gushizone.distributed.sentinel.config.feign.SentinelProducerThrowBreakableApi;
import tk.gushizone.distributed.sentinel.config.feign.handler.SentinelFallBackHandler;

import javax.annotation.Resource;

/**
 * 降级
 *
 * <pre>
 *  降级规则: {@link com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule}
 *  {
 *      "resource":"/sentinel/degrade/url",
 *      "limitApp":"default",
 *      "grade":2,                  // 熔断策略：0-慢调用比例 1-异常比例 2-异常数策略
 *      "count":1,                  // 慢调用比例模式下为慢调用临界 RT（超出该值计为慢调用）；异常比例/异常数模式下为对应的阈值
 *      "minRequestAmount":1,       // 熔断触发的最小请求数，请求数小于该值时即使异常比率超出阈值也不会熔断（1.7.0 引入）
 *      "slowRatioThreshold":1,     // 统计时长（单位为 ms），如 60*1000 代表分钟级（1.8.0 引入）
 *      "statIntervalMs":1000,      // 慢调用比例阈值，仅慢调用比例模式有效（1.8.0 引入）
 *      "timeWindow":100            // 熔断时长，单位为 s
 *  }
 * </pre>
 *
 *
 * @author gushizone@gmail.com
 * @date 2022/3/1 4:15 下午
 */
@Slf4j
@RestController
@RequestMapping("/sentinel/degrade")
public class SentinelDegradeController {

    @Resource
    private SentinelProducerApi sentinelProducerApi;
    @Qualifier
    @Autowired
//    @Resource(name = "sentinelProducerBreakableApi")
    private SentinelProducerBreakableApi sentinelProducerBreakableApi;
    @Qualifier
    @Autowired
    private SentinelProducerThrowBreakableApi sentinelProducerThrowBreakableApi;


    /**
     * url 资源降级
     * <pre>
     *    Blocked by Sentinel (flow limiting)
     *   {
     *       "resource":"/sentinel/degrade/url",
     *       "limitApp":"default",
     *       "grade":2,
     *       "count":1,
     *       "minRequestAmount":1,
     *       "slowRatioThreshold":1,
     *       "statIntervalMs":1000,
     *       "timeWindow":100
     *   }
     * </pre>
     */
    @GetMapping("/url")
    public String url() {
        log.info("=======");
        int i = 1 / 0;
        return "OK";
    }

    /**
     * feign 资源 - {@link com.alibaba.cloud.sentinel.feign.SentinelInvocationHandler}
     * <pre>
     *   {
     *       "resource":"GET:http://demo-sentinel-client-producer/ping",
     *       "limitApp":"default",
     *       "grade":2,
     *       "count":1,
     *       "minRequestAmount":1,
     *       "slowRatioThreshold":1,
     *       "statIntervalMs":1000,
     *       "timeWindow":100
     *   }
     * </pre>
     */
    @GetMapping("/feign-resource")
    public String feignResource() {
        return sentinelProducerApi.ping();
    }

    /**
     * feign 资源 - fallback
     */
    @GetMapping("/fallback/feign-resource")
    public String feignResourceFallback() {
        return sentinelProducerBreakableApi.ping();
    }

    /**
     * feign 资源 - fallbackFactory
     */
    @GetMapping("/fallback-factory/feign-resource")
    public String feignResourceFallbackFactory() {
        return sentinelProducerThrowBreakableApi.ping();
    }

    /**
     * 注解资源降级
     * <pre>
     *  {
     *      "resource":"degradeResource",
     *      "limitApp":"default",
     *      "grade":2,
     *      "count":1,
     *      "minRequestAmount":1,
     *      "slowRatioThreshold":1,
     *      "statIntervalMs":1000,
     *      "timeWindow":100
     *  }
     * </pre>
     */
    @SentinelResource(value = "degradeResource",
            fallbackClass = SentinelFallBackHandler.class, fallback = "degradeResource")
    @GetMapping("/degrade-resource")
    public String degradeResource() {
        log.info("=======");
        int i = 1 / 0;
        return "OK";
    }

}
