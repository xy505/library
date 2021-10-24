package team.library.service;

import team.library.common.R;
import team.library.entity.LiBook;
import com.baomidou.mybatisplus.extension.service.IService;
import team.library.vo.book.bookQueryVo;

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
    public R queryBook(bookQueryVo bookQueryVo);



}
