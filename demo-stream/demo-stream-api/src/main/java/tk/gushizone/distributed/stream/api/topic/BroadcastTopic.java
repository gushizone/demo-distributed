package tk.gushizone.distributed.stream.api.topic;

import tk.gushizone.distributed.stream.api.topic.input.BroadcastInput;
import tk.gushizone.distributed.stream.api.topic.output.BroadcastOutput;

/**
 * fixme 一般来说生产者和消费者不会在同一个应用，@Input 和 @Output 会根据 value 创建默认名称的 bean
 * fixme 当应用同时是消费者和生产者时才引入该 Topic，否则会创建多余的队列
 *
 * @author gushizone@gmail.com
 * @date 2021/9/7 2:19 下午
 */
public interface BroadcastTopic extends BroadcastInput, BroadcastOutput {

}
