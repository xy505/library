package team.library.service;

import team.library.common.R;
import team.library.entity.LiBook;
import com.baomidou.mybatisplus.extension.service.IService;
import team.library.vo.book.addBookVo;
import team.library.vo.book.deleteBookVo;
import team.library.vo.book.editBookVo;
import team.library.vo.book.queryBookVo;

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
}
