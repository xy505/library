package team.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import team.library.common.R;
import team.library.entity.LiBook;
import team.library.entity.LiUserBook;
import team.library.mapper.LiBookMapper;
import team.library.mapper.LiUserBookMapper;
import team.library.service.LiUserBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.library.vo.book.borrowBookVo;

import java.util.List;
import java.util.Map;

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

    @Autowired
    LiBookMapper liBookMapper;

    @Override
    public synchronized R borrowBook(borrowBookVo bookBorrowVo) {
        //不能反复借同一本书
        LiUserBook book=this.queryItem(bookBorrowVo);
        if (book!=null){
            return R.error().message("已经借过这本书了");
        }
        //先查询到该书，在修改
        LiBook liBook = liBookMapper.selectById(bookBorrowVo.getBookId());
        if (liBook.getNumber()<1){
            return R.error().message("该书已经被借完");
        }
        LiUserBook liUserBook = new LiUserBook();
        liUserBook.setBookId(bookBorrowVo.getBookId());
        liUserBook.setUserId(bookBorrowVo.getUserId());
        int insert = this.baseMapper.insert(liUserBook);
        if(insert!=1){
            return R.error().message("借书失败");
        }
        //书籍的数量还要减1
        liBook.setNumber(liBook.getNumber()-1);
        int result = liBookMapper.updateById(liBook);
        if (result!=1){
            //如果修改失败，那么回滚
            LiUserBook liUserBook1 = this.queryItem(bookBorrowVo);
            this.baseMapper.deleteById(liUserBook1.getId());
            return R.error().message("借书失败");
        }
        return R.ok().message("借书成功");
    }

    @Override
    public synchronized R returnBook(borrowBookVo bookBorrowVo) {
        LiUserBook item = queryItem(bookBorrowVo);
        if (item!=null){
            //还书
            LiUserBook liUserBook = item.setIsDeleted(1);
            int insert = this.baseMapper.updateById(liUserBook);
            if (insert!=1){
                return R.error().message("归还失败");
            }else {
                //书籍数量加一
                LiBook liBook = liBookMapper.selectById(bookBorrowVo.getBookId());
                liBook.setNumber(liBook.getNumber()+1);
                int result = liBookMapper.updateById(liBook);
                if (result!=1){
                    //如果修改失败，那么回滚
                    LiUserBook liUserBook1 = item.setIsDeleted(0);
                    int insert1 = this.baseMapper.updateById(liUserBook1);
                    return R.error().message("归还失败");
                }
                return R.ok().message("归还成功");
            }
        }else {
            return R.error().message("不存在该单");
        }
    }

    @Override
    public LiUserBook queryItem(borrowBookVo bookBorrowVo) {
        QueryWrapper<LiUserBook> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",bookBorrowVo.getUserId());
        wrapper.eq("bookId",bookBorrowVo.getBookId());
        wrapper.eq("is_deleted",0);
        LiUserBook liUserBook = this.baseMapper.selectOne(wrapper);
        if(liUserBook!=null){
            return liUserBook;
        }
        return null;
    }

    @Override
    public R queryAllItem(borrowBookVo borrowBookVo) {
        QueryWrapper<LiUserBook> wrapper = new QueryWrapper<>();
        //根据书名去查询所有记录
        if (borrowBookVo.getBookId()!=0){
            wrapper.eq("bookId",borrowBookVo.getBookId());
        }
        //根据用户去查
        if (borrowBookVo.getUserId()!=0){
            wrapper.eq("userId",borrowBookVo.getUserId());
        }
        wrapper.eq("is_deleted",0);
        List<LiUserBook> liUserBooks = this.baseMapper.selectList(wrapper);
        return R.ok().data("所有借阅记录",liUserBooks);
    }


}
