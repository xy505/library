package team.library.vo.comment;

import lombok.Data;
import team.library.vo.pageQuery;

@Data
public class queryCommentVo extends pageQuery {
    //用户Id
    private Integer userId;
    //书籍Id
    private Integer bookId;
}
