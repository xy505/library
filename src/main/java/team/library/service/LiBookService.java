package team.library.service;

import team.library.common.R;
import team.library.entity.LiBook;
import com.baomidou.mybatisplus.extension.service.IService;
import team.library.vo.book.*;
import team.library.vo.pageQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-10-11
 */
public interface LiBookService extends IService<LiBook> {

    /**
     * 查询书籍
     * @param bookQueryVo
     * @return
     */
    public R queryBook(queryBookVo bookQueryVo);

    /**
     * 编辑书籍
     * @param vo
     * @return
     */
    public R editBook(editBookVo vo);

    /**
     * 添加书籍
     * @param vo
     * @return
     */
    public R addBook(addBookVo vo);

    /**
     * 删除书籍
     * @param vo
     * @return
     */
    public R deleteBook(deleteBookVo vo);

    /**
     * 查询用户捐赠图书的情况
     */
    public R queryUserBook(donateBookVo vo);
}
