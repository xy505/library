package team.library.service;

import team.library.common.R;
import team.library.entity.LiUserBook;
import com.baomidou.mybatisplus.extension.service.IService;
import team.library.vo.book.bookBorrowVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-10-13
 */
public interface LiUserBookService extends IService<LiUserBook> {
    /**
     * 用户借书
     * @param bookBorrowVo
     * @return
     */
    public R borrowBook(bookBorrowVo bookBorrowVo);

    /**
     * 用户还书
     */
    public R returnBook(bookBorrowVo bookBorrowVo);

    /**
     * 查询借阅单
     * @param bookBorrowVo
     * @return
     */
    public R queryItem(bookBorrowVo bookBorrowVo);

}
