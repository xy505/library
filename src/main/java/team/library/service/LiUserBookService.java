package team.library.service;

import team.library.common.R;
import team.library.entity.LiUserBook;
import com.baomidou.mybatisplus.extension.service.IService;
import team.library.vo.book.borrowBookVo;
import team.library.vo.pageQuery;

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
    public R borrowBook(borrowBookVo bookBorrowVo);

    /**
     * 用户还书
     */
    public R returnBook(borrowBookVo bookBorrowVo);

    /**
     * 查询特定借阅单
     * @param bookBorrowVo
     * @return
     */
    public LiUserBook queryItem(borrowBookVo bookBorrowVo);

    /**
     * 查询所有借阅单
     * @param borrowBookVo
     * @return
     */
    public R queryAllItem(borrowBookVo borrowBookVo);

    /**
     * 定时更新借阅时间
     */
    public void updateBorrowIngTime();

    /**
     * 查看哪些人逾期还没还书
     */
    public R getOverTime(pageQuery pageQuery);

}
