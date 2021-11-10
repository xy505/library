package team.library.controller;


import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import team.library.common.R;
import team.library.service.LiCommentService;
import team.library.vo.book.editBookVo;
import team.library.vo.comment.addCommentVo;
import team.library.vo.comment.deleteCommentVo;
import team.library.vo.comment.queryCommentVo;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-11-09
 */
@RestController
@RequestMapping("/library/li-comment")
public class LiCommentController {
    private Logger logger = LoggerFactory.getLogger(LiCommentController.class);
    @Autowired
    LiCommentService liCommentService;

    @PostMapping("/addComment")
    @ApiOperation(value = "添加评论", notes = "")
    public R addComment(@RequestBody addCommentVo vo){
        try {
            R result = liCommentService.addComment(vo);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("添加评论失败");
            return R.error().message("添加评论失败");
        }
    }

    @PostMapping("/deleteComment")
    @ApiOperation(value = "删除评论", notes = "")
    public R deleteComment(@RequestBody deleteCommentVo vo){
        try {
            R result = liCommentService.deleteComment(vo);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("删除评论失败");
            return R.error().message("删除评论失败");
        }
    }

    @PostMapping("/queryComment")
    @ApiOperation(value = "查询评论", notes = "")
    public R queryComment(@RequestBody queryCommentVo vo){
        try {
            R result = liCommentService.queryComment(vo);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("查询评论失败");
            return R.error().message("查询评论失败");
        }
    }
}

