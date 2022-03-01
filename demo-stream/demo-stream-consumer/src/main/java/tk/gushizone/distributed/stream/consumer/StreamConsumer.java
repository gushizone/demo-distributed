package tk.gushizone.distributed.stream.consumer;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import tk.gushizone.distributed.stream.api.dto.MessageDto;
import tk.gushizone.distributed.stream.api.topic.DelayedTopic;
import tk.gushizone.distributed.stream.api.topic.DlqTopic;
import tk.gushizone.distributed.stream.api.topic.FallbackTopic;
import tk.gushizone.distributed.stream.api.topic.GroupTopic;
import tk.gushizone.distributed.stream.api.topic.RequeueTopic;
import tk.gushizone.distributed.stream.api.topic.RetryTopic;
import tk.gushizone.distributed.stream.api.topic.input.BroadcastInput;

/**
 * stream 中的模型用的都是 topic 模型，即 exchange type 为 topic
 *
 * @author gushizone@gmail.com
 * @date 2021/9/7 11:31 上午
 */
@Slf4j
@Component
public class StreamConsumer {

    /**
     * 默认是临时queue： input.anonymous.vZ4jYeIpRjqxFNpTTAozNg
     */
    @StreamListener(Sink.INPUT)
    public void consume(Object payload) {

        log.info("consume message: {}", payload);
    }

    /**
     * 广播模型：消息会被每个消费者接收
     * 自动创建 exchange : broadcast
     * 自动创建 queue : broadcast.anonymous.l1LCPEfkTvWdX2GWWDE-CA
     * 每个消费者拥有自己独立的 queue
     */
    @StreamListener(BroadcastInput.INPUT)
    public void broadcast(Object payload) {

        log.info("consume broadcast message: {}", payload);
    }

    /**
     * 消费分组 - 轮询单播模型：消息发给每个组，一个组只有一个消费者会接收
     * 自动创建 exchange : group
     * 自动创建 queue : group.group-A
     * 每个消费者根据编组，监听 queue，同组只会有一个消费者接收
     * <p>
     * 消费组分区：对消费组在分区，消息根据SpEL路由到不同的分区
     * 不同分区对应不同到 queue 和 routing key : group.group-A-0 , group-0
     */
    @StreamListener(GroupTopic.INPUT)
    public void group(Object payload) {

        log.info("consume group message: {}", payload);
    }


    /**
     * 延时队列
     * 若配置成功启动后，有 exchange： delayed | x-delayed-message | D DM Args
     */
    @StreamListener(DelayedTopic.INPUT)
    public void delayed(Object payload) {

        log.info("consume delayed message: {}", payload);
    }

    /**
     * 异常重试 - 单机重试
     * 若重试成功，异常不会抛出，反之，抛出
     * 若重试都没有成功就会消息丢失
     */
    @StreamListener(RetryTopic.INPUT)
    public void retry(Object payload) {
        log.info("consume retry start message: {}", payload);
        if (RandomUtil.randomInt(0, 10) >= 5) {
            log.error("random error...");
            throw new RuntimeException("random error");
        }
        log.info("consume retry end message: {}", payload);
    }


    /**
     * 异常重新入队 - 联机重试
     * 若一致失败就会消息堵塞
     */
    @StreamListener(RequeueTopic.INPUT)
    public void requeue(Object payload) {
        log.info("consume requeue start message: {}", payload);
        if (RandomUtil.randomInt(0, 10) >= 1) {
            log.error("random error...");
            throw new RuntimeException("random error");
        }
        log.info("consume requeue end message: {}", payload);
    }

    /**
     * 死信队列
     * 若设置成功，启动后自动创建并绑定死信队列，且队列显示 D DLX DLK
     * 死信队列为 [topicName].[groupName].dlq
     */
    @StreamListener(DlqTopic.INPUT)
    public void dql(Object payload) {
        log.info("consume dql start message: {}", payload);
        if (RandomUtil.randomInt(0, 10) >= 5) {
            log.error("random error...");
            throw new RuntimeException("random error");
        }
        log.info("consume dql end message: {}", payload);
    }

    @StreamListener(FallbackTopic.INPUT)
    public void fallback(MessageDto msg, @Header("version") String version) {
        log.info("consume fallback start message: {}", msg);
        if (StringUtils.equalsIgnoreCase("2.0", version)) {

            log.info("hello version {}", version);
        } else {
            log.info("illegal version {}", version);
            throw new RuntimeException("illegal version error");
        }
        log.info("consume fallback end message: {}", msg);
    }

    /**
     * 订阅错误通道: [topicName].[groupName].errors
     * 类似于降级功能，会在尝试阈值后执行
     */
    @ServiceActivator(inputChannel = "fallback.fallback-group.errors")
    public void fallback(Message<?> message) {



        log.info("fallback: {}", message);
//        throw new RuntimeException("error");
    }
}
