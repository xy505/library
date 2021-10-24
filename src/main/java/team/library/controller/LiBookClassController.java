package team.library.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import team.library.common.R;
import team.library.entity.LiBookClass;
import team.library.service.LiBookClassService;

import java.util.ArrayList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-10-11
 */
@RestController
@RequestMapping("/library/li-book-class")
public class LiBookClassController {
    @Autowired
    LiBookClassService liBookClassService;

    /**
     * 获取图书分类
     */
    @GetMapping("/getAllClass")
    public R getAllClass(){
        ArrayList<LiBookClass> allClass = liBookClassService.getAllClass();
        return R.ok().data("所有分类信息",allClass);
    }
}

