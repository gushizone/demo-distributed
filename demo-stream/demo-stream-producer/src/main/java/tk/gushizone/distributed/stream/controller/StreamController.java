package tk.gushizone.distributed.stream.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.gushizone.distributed.stream.api.dto.MessageDto;
import tk.gushizone.distributed.stream.api.topic.output.BroadcastOutput;
import tk.gushizone.distributed.stream.api.topic.output.DelayedOutput;
import tk.gushizone.distributed.stream.api.topic.output.DlqOutput;
import tk.gushizone.distributed.stream.api.topic.output.FallbackOutput;
import tk.gushizone.distributed.stream.api.topic.output.GroupOutput;
import tk.gushizone.distributed.stream.api.topic.output.RequeueOutput;
import tk.gushizone.distributed.stream.api.topic.output.RetryOutput;

/**
 * @author gushizone@gmail.com
 * @date 2021/9/7 2:35 下午
 */
@Slf4j
@RestController
@RequestMapping("/stream")
public class StreamController {

    @Autowired
    private BroadcastOutput broadcastOutput;
    @Autowired
    private GroupOutput groupOutput;
    @Autowired
    private DelayedOutput delayedOutput;
    @Autowired
    private RetryOutput retryOutput;
    @Autowired
    private RequeueOutput requeueOutput;
    @Autowired
    private DlqOutput dlqOutput;
    @Autowired
    private FallbackOutput fallbackOutput;

    /**
     * 广播
     */
    @PostMapping("/broadcast/send")
    public String broadcast(@RequestBody String body) {

        broadcastOutput.output().send(MessageBuilder
                .withPayload(body)
                .build());
        return "OK";
    }

    /**
     * 消费分组，消费分区
     */
    @PostMapping("/group/send")
    public String group(@RequestBody String body) {

        groupOutput.output().send(MessageBuilder
                .withPayload(body)
                .build());
        return "OK";
    }

    /**
     * 延时消息
     */
    @PostMapping("/delayed/send")
    public String delayed(@RequestBody String body) {

        log.info("read to send delayed message");
        delayedOutput.output().send(MessageBuilder
                .withPayload(body)
                .setHeader("x-delay", 1000 * 5)
                .build());
        return "OK";
    }

    /**
     * 异常重试
     */
    @PostMapping("/retry/send")
    public String retry(@RequestBody String body) {

        retryOutput.output().send(MessageBuilder
                .withPayload(body)
                .build());
        return "OK";
    }

    /**
     * 异常重新入队
     */
    @PostMapping("/requeue/send")
    public String requeue(@RequestBody String body) {

        requeueOutput.output().send(MessageBuilder
                .withPayload(body)
                .build());
        return "OK";
    }

    /**
     * 死信队列
     */
    @PostMapping("/dlq/send")
    public String dlq(@RequestBody String body) {

        dlqOutput.output().send(MessageBuilder
                .withPayload(body)
                .build());
        return "OK";
    }

    /**
     * 订阅错误
     */
    @PostMapping("/fallback/send")
    public String fallback(@RequestBody MessageDto msg) {

        fallbackOutput.output().send(MessageBuilder
                .withPayload(msg)
                .setHeader("version", "1.0")
                .build());
        return "OK";
    }

}
