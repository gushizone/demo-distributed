package tk.gushizone.distributed.sentinel.config.feign.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("unchecked")
public class SentinelBlockHandler {

    /**
     * 有返回值的限流阻塞处理
     */
    public static String returnBlock(BlockException exception) {
        return "访问过于频繁，请稍后重试！";
    }

    /**
     * 无返回值的限流阻塞处理
     */
    public static void voidBlock(BlockException exception) {
        log.error("触发限流, 资源名: {}", exception.getRule().getResource());
        // 可以抛出统一异常等...
    }
}
