package com.issja.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 得分
 * </p>
 *
 * @author issja
 * @since 2023-01-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Score对象", description="得分")
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学生id")
    private Integer uid;

    @ApiModelProperty(value = "比赛id")
    private Integer cid;

    @ApiModelProperty(value = "正式赛时间内积分")
    @TableField("duringScore")
    private Integer duringscore;

    @ApiModelProperty(value = "赛后补题")
    @TableField("afterScore")
    private Integer afterscore;


}
