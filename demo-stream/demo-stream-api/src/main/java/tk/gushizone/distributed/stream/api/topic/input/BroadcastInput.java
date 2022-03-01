package tk.gushizone.distributed.stream.api.topic.input;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author gushizone@gmail.com
 * @date 2021/9/7 2:19 下午
 */
public interface BroadcastInput {

    String INPUT = "broadcast-consumer";

    @Input(INPUT)
    SubscribableChannel input();

}
