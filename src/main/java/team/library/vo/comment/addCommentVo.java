package team.library.vo.comment;

import lombok.Data;

@Data
public class addCommentVo {
    //用户Id
    private Integer userId;
    //书籍Id
    private Integer bookId;
    //评论
    private String comment;
}
