package team.library.vo.book;

import lombok.Data;
import team.library.vo.pageQuery;

import java.util.ArrayList;

/**
 * 分页查询书籍
 */
@Data
public class queryBookVo extends pageQuery {
    //分类
    private ArrayList<Integer> sorts;

    //作者
    private String author;

    //书名
    private String bookName;
}
