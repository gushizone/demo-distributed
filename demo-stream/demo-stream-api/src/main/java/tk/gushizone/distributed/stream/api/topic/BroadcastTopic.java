package tk.gushizone.distributed.stream.api.topic;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * todo 一般来说生产者和消费者不会在同一个应用，@Input 和 @Output 会根据 value 创建默认名称的 bean ，所以这里要使用不同名称，并在配置中手动关联
 *
 * @author gushizone@gmail.com
 * @date 2021/9/7 2:19 下午
 */
public interface BroadcastTopic {

    String INPUT = "broadcast-consumer";
    String OUTPUT = "broadcast-producer";

    @Input(INPUT)
    SubscribableChannel input();

    @Output(OUTPUT)
    MessageChannel output();

}
