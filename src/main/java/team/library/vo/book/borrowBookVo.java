package team.library.vo.book;

import lombok.Data;

/**
 * 用户借书/还书,查询借阅单
 */
@Data
public class borrowBookVo {
    //用户Id
    private Integer userId;
    //书籍Id
    private Integer bookId;

}
