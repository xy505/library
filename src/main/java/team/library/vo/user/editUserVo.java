package team.library.vo.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class editUserVo {
    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "密码")
    private String passWord;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "性别 1 女，2 男'")
    private Integer sex;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    private String code;
}
