package tk.gushizone.distributed.stream.api.dto;

import lombok.Data;

/**
 * @author gushizone@gmail.com
 * @date 2021/9/7 11:27 上午
 * @see org.springframework.messaging.Message
 */
@Data
public class MessageDto {

    private String payload;
}
