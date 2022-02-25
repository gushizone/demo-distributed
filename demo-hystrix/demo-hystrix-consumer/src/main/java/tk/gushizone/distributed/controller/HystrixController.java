package tk.gushizone.distributed.controller;

import com.google.common.base.Throwables;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.gushizone.distributed.api.ProducerPingApi;
import tk.gushizone.distributed.feign.ProducerPingBreakableApi;
import tk.gushizone.distributed.feign.ProducerPingThrowBreakableApi;
import tk.gushizone.distributed.service.ProducerCacheService;

import javax.annotation.Resource;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/25 4:12 下午
 */
@Slf4j
@RestController
@RequestMapping("/hystrix")
public class HystrixController {

    @Resource
    private ProducerPingApi producerPingApi;
    @Qualifier
    @Autowired
    private ProducerPingBreakableApi producerPingBreakableApi;
    @Qualifier
    @Autowired
    private ProducerPingThrowBreakableApi producerPingThrowBreakableApi;

    @Resource
    private ProducerCacheService producerCacheService;

    @GetMapping("/fallback")
    public String fallback(@RequestParam("timeout") Long timeout) {
        return producerPingBreakableApi.timeout(timeout);
    }

    @GetMapping("/fallback-factory")
    public String fallbackFactory(@RequestParam("timeout") Long timeout) {
        return producerPingThrowBreakableApi.timeout(timeout);
    }

    /**
     * request cache : 在同一个上下文内，可以对 request 进行缓存。
     * 即：一个上下文内，只会发生一次调用
     */
    @GetMapping("/cache")
    public String cache(@RequestParam("timeout") Long key) {

        @Cleanup HystrixRequestContext context = HystrixRequestContext.initializeContext();

        String result = producerCacheService.cache(key);
        result = producerCacheService.cache(key);
        return result;
    }


    /**
     * Hystrix 不仅可以作用服务调用，也可以作用本地方法
     * <p>
     * 注意这里的 timeout 一定要大于其所有调用的方法的timeout，否则都会走默认的Fallback
     * （feign 超时配置和 hystrix 的全局超时配置，都有影响）
     */
    @HystrixCommand(
            fallbackMethod = "fallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
            }
    )
    @GetMapping("/hystrix-command")
    public String hystrixCommand(@RequestParam("timeout") Long timeout) {

        String result = producerPingApi.timeout(timeout);
//        String result = producerPingBreakableApi.timeout(timeout);
        return result;
    }

    /**
     * 降级方法可以接受异常
     */
    private String fallback(Long timeout, Throwable throwable) {

        Throwable rootCause = Throwables.getRootCause(throwable);
        log.warn("触发降级：{}", rootCause.getMessage(), rootCause);
        return "HystrixCommand 触发降级";
    }
}
