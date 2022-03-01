package tk.gushizone.distributed.sentinel.config.feign.handler;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SentinelFallBackHandler {


    public static String degradeResource(Throwable throwable) {
        if (throwable instanceof DegradeException) {
            DegradeException degradeException = (DegradeException) throwable;
            log.error("触发熔断降级，资源名: {}", degradeException.getRule().getResource());
        } else {
            log.error("触发降级: {}", throwable.getMessage());
        }
        return "系统异常，请稍后重试！";
    }
}
