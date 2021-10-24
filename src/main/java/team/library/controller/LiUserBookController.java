package team.library.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import team.library.common.R;
import team.library.service.LiUserBookService;
import team.library.vo.book.bookBorrowVo;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-10-13
 */
@RestController
@RequestMapping("/library/li-user-book")
public class LiUserBookController {

    @Autowired
    LiUserBookService liUserBookService;

    /**
     * 用户借书功能
     * @param vo
     * @return
     */
    @PostMapping("/borrowBook")
    public R borrowBook(@RequestBody bookBorrowVo vo){
        try {
            R result = liUserBookService.borrowBook(vo);
            return result;
        }catch (Exception e){
            return R.error().message("系统出错");
        }
    }

    @PostMapping("/returnBook")
    public R returnBook(@RequestBody bookBorrowVo vo){
        try {
            R result = liUserBookService.returnBook(vo);
            return result;
        }catch (Exception e){
            return R.error().message("系统出错");
        }
    }

}

