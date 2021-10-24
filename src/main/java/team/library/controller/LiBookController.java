package team.library.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import team.library.common.R;
import team.library.service.LiBookService;
import team.library.vo.book.bookQueryVo;

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
    public R queryBook(@RequestBody bookQueryVo vo){
        try{
            R result = liBookService.queryBook(vo);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("查询书籍失败");
            return R.error().message("查询失败");
        }

    }
}

