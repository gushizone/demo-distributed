package tk.gushizone.distributed.seata.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
 * @TableName stock
 */
@TableName(value ="stock")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long itemId;

    /**
     * 
     */
    private Integer stock;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}