package team.library.service;

import team.library.common.R;
import team.library.entity.LiComment;
import com.baomidou.mybatisplus.extension.service.IService;
import team.library.vo.comment.addCommentVo;
import team.library.vo.comment.deleteCommentVo;
import team.library.vo.comment.queryCommentVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-11-09
 */
public interface LiCommentService extends IService<LiComment> {

    /**
     * 添加评论
     * @param vo
     * @return
     */
    public R addComment(addCommentVo vo);

    /**
     * 删除评论
     * @param vo
     * @return
     */
    public R deleteComment(deleteCommentVo vo);

    /**
     * 查询评论
     * @param vo
     * @return
     */
    public R queryComment(queryCommentVo vo);

}
