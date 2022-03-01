package tk.gushizone.distributed.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import tk.gushizone.distributed.stream.api.topic.input.BroadcastInput;
import tk.gushizone.distributed.stream.api.topic.input.DelayedInput;
import tk.gushizone.distributed.stream.api.topic.input.DlqInput;
import tk.gushizone.distributed.stream.api.topic.input.FallbackInput;
import tk.gushizone.distributed.stream.api.topic.input.GroupInput;
import tk.gushizone.distributed.stream.api.topic.input.RequeueInput;
import tk.gushizone.distributed.stream.api.topic.input.RetryInput;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/28 4:32 下午
 */
@EnableBinding({
        Sink.class,
        BroadcastInput.class,
        DelayedInput.class,
        DlqInput.class,
        FallbackInput.class,
        GroupInput.class,
        RequeueInput.class,
        RetryInput.class,
})
@SpringBootApplication
public class StreamConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamConsumerApplication.class, args);
    }
}
