package tk.gushizone.distributed.stream.api.topic;

import tk.gushizone.distributed.stream.api.topic.input.RequeueInput;
import tk.gushizone.distributed.stream.api.topic.output.RetryOutput;

/**
 * @author gushizone@gmail.com
 * @date 2021/9/7 2:19 下午
 */
public interface RetryTopic extends RetryOutput, RequeueInput {

}
