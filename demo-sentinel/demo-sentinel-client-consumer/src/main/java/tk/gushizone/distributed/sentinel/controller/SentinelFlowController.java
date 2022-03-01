package tk.gushizone.distributed.sentinel.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.gushizone.distributed.sentinel.config.feign.handler.SentinelBlockHandler;
import tk.gushizone.distributed.sentinel.service.SentinelFlowService;

import javax.annotation.Resource;

/**
 * 流控
 * https://sentinelguard.io/zh-cn/docs/flow-control.html
 * <pre>
 * 流控规则 {@link com.alibaba.csp.sentinel.slots.block.flow.FlowRule}
 *  {
 *    "resource": "/api/sentinel/flow/url",      // 资源名，即限流规则的作用对象
 *    "limitApp": "default",                     // 限制源（用于根据调用方进行流量控制）
 *    "grade": 1,                                // 限流阈值类: 0-线程数, 1-QPS
 *    "count": 1,                                // 限流阈值
 *    "strategy": 0,                             // 调用关系选择策略: 0-根据调用方限流（按来源）1-相关流量控制（与相关资源）2-链路限流
 *    "controlBehavior": 0,                      // 流控行为: 0-直接拒绝, 1-冷启动, 2-匀速器
 *    "clusterMode": false
 *  }
 * </pre>
 *
 * @author gushizone@gmail.com
 * @date 2022/3/1 4:15 下午
 */
@Slf4j
@RestController
@RequestMapping("/sentinel/flow")
@SuppressWarnings("unchecked")
public class SentinelFlowController {

    @Resource
    private SentinelFlowService sentinelFlowService;


    /**
     * url 资源
     * <pre>
     *  {
     *     "resource": "/api/sentinel/flow/url",
     *     "limitApp": "default",
     *     "grade": 1,
     *     "count": 1,
     *     "strategy": 0,
     *     "controlBehavior": 0,
     *     "clusterMode": false
     *  }
     * </pre>
     */
    @GetMapping("/url")
    public String url() {
        return "OK";
    }

    /**
     * api 资源
     * <pre>
     *  {
     *      "resource": "apiResource",
     *      "limitApp": "default",
     *      "grade": 1,
     *      "count": 1,
     *      "strategy": 0,
     *      "controlBehavior": 0,
     *      "clusterMode": false
     *  }
     * </pre>
     */
    @SentinelResource(value = "apiResource",
            blockHandlerClass = SentinelBlockHandler.class, blockHandler = "returnBlock")
    @GetMapping("/api-resource")
    public String apiResource() {
        return "OK";
    }

    /**
     * 方法资源
     * <pre>
     *  {
     *      "resource": "methodResource",
     *      "limitApp": "default",
     *      "grade": 1,
     *      "count": 1,
     *      "strategy": 0,
     *      "controlBehavior": 0,
     *      "clusterMode": false
     *  }
     * </pre>
     */
    @GetMapping("/method-resource")
    public String methodResource() {

        sentinelFlowService.doSomething();
        return "OK";
    }

    /**
     * 代码块资源
     * <pre>
     *  {
     *      "resource": "blockResource",
     *      "limitApp": "default",
     *      "grade": 1,
     *      "count": 1,
     *      "strategy": 0,
     *      "controlBehavior": 0,
     *      "clusterMode": false
     *  }
     * </pre>
     */
    @GetMapping("/block-resource")
    public String blockResource() {

        doSomething();
        return "OK";
    }

    /**
     * {@link SentinelResource} 基于 AOP，
     * 如果希望 this 调用 可以使用代码块限流
     */
    public void doSomething() {
        // ...

        // 限流代码块
        try (Entry entry = SphU.entry("blockResource")) {
            // 被保护的业务逻辑
            // do something here...
        } catch (BlockException ex) {
            // 资源访问阻止，被限流或被降级
            // 在此处进行相应的处理操作
            log.error("触发限流, 资源名: {}", ex.getRule().getResource());
        }
    }

}
