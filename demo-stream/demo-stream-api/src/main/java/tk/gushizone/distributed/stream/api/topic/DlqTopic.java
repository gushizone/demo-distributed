package tk.gushizone.distributed.stream.api.topic;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author gushizone@gmail.com
 * @date 2021/9/7 2:19 下午
 */
public interface DlqTopic {

    String INPUT = "dlq-consumer";
    String OUTPUT = "dlq-producer";

    @Input(INPUT)
    SubscribableChannel input();

    @Output(OUTPUT)
    MessageChannel output();

}
