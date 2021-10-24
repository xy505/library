package team.library.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author testjava
 * @since 2021-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="LiUserBook对象", description="")
public class LiUserBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Date gmtModified;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "是否超时")
    private Integer isOvertime;

    @ApiModelProperty(value = "借书时长")
    @TableField("borrowingTime")
    private Integer borrowingTime;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @TableField("userId")
    private Integer userId;

    @TableField("bookId")
    private Integer bookId;

    @ApiModelProperty(value = "是否归还 1（true）已归还，  0（false）未归还")
    private Boolean isReturn;


}
