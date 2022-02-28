package tk.gushizone.distributed.sharding.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 字典表
 * @TableName sys_dict
 */
@TableName(value ="sys_dict")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysDict implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 类型
     */
    private String type;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}