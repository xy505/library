package team.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import team.library.common.R;
import team.library.entity.LiComment;
import team.library.mapper.LiCommentMapper;
import team.library.service.LiBookService;
import team.library.service.LiCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.library.service.LiUserService;
import team.library.vo.comment.addCommentVo;
import team.library.vo.comment.deleteCommentVo;
import team.library.vo.comment.queryCommentVo;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-11-09
 */
@Service
public class LiCommentServiceImpl extends ServiceImpl<LiCommentMapper, LiComment> implements LiCommentService {

    @Autowired
    LiUserService liUserService;


    @Override
    public R addComment(addCommentVo vo) {
        //不可以重复评论同样的评论
        QueryWrapper<LiComment> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",vo.getUserId());
        wrapper.eq("bookId",vo.getBookId());
        wrapper.eq("comment",vo.getComment());
        LiComment isExist = this.baseMapper.selectOne(wrapper);
        if (isExist!=null){
            return R.error().message("您已经重复评论了相同的内容");
        }
        LiComment liComment = new LiComment();
        liComment.setBookId(vo.getBookId());
        liComment.setComment(vo.getComment());
        liComment.setUserId(vo.getUserId());
        int insert = this.baseMapper.insert(liComment);
        if (insert!=1){
            return R.error().message("评论失败");
        }else {
            return R.ok().message("评论成功");
        }
    }

    @Override
    public R deleteComment(deleteCommentVo vo) {
        QueryWrapper<LiComment> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",vo.getUserId());
        wrapper.eq("bookId",vo.getBookId());
        wrapper.eq("comment",vo.getComment());
        LiComment isExist = this.baseMapper.selectOne(wrapper);
        if (isExist!=null){
            int result = this.baseMapper.deleteById(isExist.getId());
            if (result!=1){
                return R.error().message("删除失败");
            }else {
                return R.ok().message("删除成功");
            }
        }else {
            return R.error().message("不存在该评论");
        }
    }

    @Override
    public R queryComment(queryCommentVo vo) {
        Page<LiComment> liCommentPage = new Page<>(vo.getPage(), vo.getLimit());
        QueryWrapper<LiComment> wrapper = new QueryWrapper<>();
        if (vo.getBookId()!=0){
            wrapper.eq("bookId",vo.getBookId());
        }
        if (vo.getUserId()!=0){
            wrapper.eq("userId", vo.getUserId());
        }
        IPage<LiComment> page = this.page(liCommentPage,wrapper);
        //填充数据
        for (LiComment comment:page.getRecords()) {
            //根据用户id查询到名字
            String name = liUserService.getById(comment.getUserId());
            comment.setComment(name);
        }

        return R.ok().data("comment",page);
    }
}
