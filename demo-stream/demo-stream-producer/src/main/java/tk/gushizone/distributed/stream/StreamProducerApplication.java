package tk.gushizone.distributed.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import tk.gushizone.distributed.stream.api.topic.output.BroadcastOutput;
import tk.gushizone.distributed.stream.api.topic.output.DelayedOutput;
import tk.gushizone.distributed.stream.api.topic.output.DlqOutput;
import tk.gushizone.distributed.stream.api.topic.output.FallbackOutput;
import tk.gushizone.distributed.stream.api.topic.output.GroupOutput;
import tk.gushizone.distributed.stream.api.topic.output.RequeueOutput;
import tk.gushizone.distributed.stream.api.topic.output.RetryOutput;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/28 4:33 下午
 */
@EnableBinding({
        Sink.class,
        BroadcastOutput.class,
        DelayedOutput.class,
        DlqOutput.class,
        FallbackOutput.class,
        GroupOutput.class,
        RequeueOutput.class,
        RetryOutput.class,
})
@SpringBootApplication
public class StreamProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamProducerApplication.class, args);
    }
}
