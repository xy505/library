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
import team.library.service.LiBookService;
import team.library.vo.book.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-10-11
 */
@RestController
@RequestMapping("/library/li-book")
public class LiBookController {

    private Logger logger = LoggerFactory.getLogger(LiBookController.class);

    @Autowired
    LiBookService liBookService;

    /**
     * 条件查询书籍
     */
    @PostMapping("/queryBook")
    @ApiOperation(value = "查询书籍", notes = "")
    public R queryBook(@RequestBody queryBookVo vo){
        try{
            R result = liBookService.queryBook(vo);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("查询书籍失败");
            return R.error().message("查询失败");
        }

    }

    /**
     * 添加书籍
     */
    @PostMapping("/AddBook")
    @ApiOperation(value = "添加书籍", notes = "")
    public R addBook(@RequestBody addBookVo vo){
        try {
            R result = liBookService.addBook(vo);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("添加书籍失败");
            return R.error().message("添加失败");
        }
    }

    /**
     * 编辑书籍
     */
    @PostMapping("/EditBook")
    @ApiOperation(value = "编辑书籍", notes = "")
    public R addBook(@RequestBody editBookVo vo){
        try {
            R result = liBookService.editBook(vo);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("编辑书籍失败");
            return R.error().message("编辑失败");
        }
    }

    /**
     * 删除书籍
     */
    @PostMapping("/DeleteBook")
    @ApiOperation(value = "删除书籍", notes = "")
    public R deleteBook(@RequestBody deleteBookVo vo){
        try {
            R result = liBookService.deleteBook(vo);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("删除书籍失败");
            return R.error().message("删除失败");
        }
    }

    /**
     * 查询用户捐赠书籍信息
     */
    @PostMapping("/queryUserBook")
    @ApiOperation(value = "查询用户捐赠的书籍", notes = "")
    public R queryUserBook(@RequestBody donateBookVo vo){
        try {
            R result = liBookService.queryUserBook(vo);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("删除书籍失败");
            return R.error().message("删除失败");
        }
    }
}

