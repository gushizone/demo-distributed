package tk.gushizone.distributed.stream.api.topic.output;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author gushizone@gmail.com
 * @date 2021/9/7 2:19 下午
 */
public interface RequeueOutput {

    String OUTPUT = "requeue-producer";

    @Output(OUTPUT)
    MessageChannel output();

}
