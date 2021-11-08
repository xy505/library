package team.library.vo.book;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class editBookVo {

    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "漫画名称")
    private String bookName;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "封面")
    private String cover;

    @ApiModelProperty(value = "简介")
    private String description;

    @ApiModelProperty(value = "分类id")
    //分类
    private ArrayList<Integer> sorts;;

    @ApiModelProperty(value = "数量")
    private Integer number;

    @ApiModelProperty(value = "是否禁用 1（true）已禁用，  0（false）未禁用")
    private Boolean isDisabled;

    @ApiModelProperty(value = "捐献者名字")
    private String donor;
}
