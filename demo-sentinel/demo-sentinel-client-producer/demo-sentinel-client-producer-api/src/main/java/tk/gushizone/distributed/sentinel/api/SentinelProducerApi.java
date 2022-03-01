package tk.gushizone.distributed.sentinel.api;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "demo-sentinel-client-producer")
public interface SentinelProducerApi {

    @GetMapping("/ping")
    String ping();
}
