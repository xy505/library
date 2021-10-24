package team.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import team.library.common.R;
import team.library.entity.LiUserBook;
import team.library.mapper.LiUserBookMapper;
import team.library.service.LiUserBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.library.vo.book.bookBorrowVo;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-10-13
 */
@Service
public class LiUserBookServiceImpl extends ServiceImpl<LiUserBookMapper, LiUserBook> implements LiUserBookService {

    @Override
    public R borrowBook(bookBorrowVo bookBorrowVo) {
        LiUserBook liUserBook = new LiUserBook();
        liUserBook.setBookId(bookBorrowVo.getBookId());
        liUserBook.setUserId(bookBorrowVo.getUserId());
        int insert = this.baseMapper.insert(liUserBook);
        if(insert!=1){
            return R.error().message("借书失败");
        }
        return R.ok().message("借书成功");
    }

    @Override
    public R returnBook(bookBorrowVo bookBorrowVo) {
        R r = queryItem(bookBorrowVo);
        LiUserBook item = (LiUserBook) r.getData().get("item");
        if (item!=null){
            //还书
            LiUserBook liUserBook = item.setIsDeleted(1);
            int insert = this.baseMapper.updateById(liUserBook);
            if (insert!=1){
                return R.error().message("归还失败");
            }else {
                return R.ok().message("归还成功");
            }
        }else {
            return R.error().message("不存在该单");
        }
    }

    @Override
    public R queryItem(bookBorrowVo bookBorrowVo) {
        QueryWrapper<LiUserBook> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",bookBorrowVo.getUserId());
        wrapper.eq("bookId",bookBorrowVo.getBookId());
        wrapper.eq("is_deleted",0);
        LiUserBook liUserBook = this.baseMapper.selectOne(wrapper);
        if(liUserBook!=null){
            return R.ok().data("item",liUserBook);
        }
        return R.error().data("item",null);
    }


}
