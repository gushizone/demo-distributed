package tk.gushizone.distributed.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tk.gushizone.distributed.api.ProducerPingApi;

import javax.annotation.Resource;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/25 5:18 下午
 */
@Slf4j
@Component
public class ProducerCacheService {

    @Resource
    private ProducerPingApi producerPingApi;

    @CacheResult
    @HystrixCommand(commandKey = "cacheKey")
    public String cache(@CacheKey Long key) {
        log.info("request cache key: " + key);

        String result = producerPingApi.timeout(key);

        log.info("request cache result: " + result);
        return result;
    }
}
