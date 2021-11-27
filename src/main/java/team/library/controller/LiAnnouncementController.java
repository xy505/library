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
import team.library.service.LiAnnouncementService;
import team.library.vo.announcement.addAnnouncementVo;
import team.library.vo.announcement.deleteAnnouncementVo;
import team.library.vo.announcement.queryAnnouncementVo;
import team.library.vo.book.addBookVo;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-11-10
 */
@RestController
@RequestMapping("/library/li-announcement")
public class LiAnnouncementController {
    private Logger logger = LoggerFactory.getLogger(LiAnnouncementController.class);
    @Autowired
    LiAnnouncementService liAnnouncementService;

    /**
     * 添加公告
     */
    @PostMapping("/addAnnouncement")
    @ApiOperation(value = "添加公告", notes = "")
    public R AddAnnouncement(@RequestBody addAnnouncementVo vo){
        try {
            R result = liAnnouncementService.addAnnouncement(vo);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("添加公告失败");
            return R.error().message("添加失败");
        }
    }

    /**
     * 删除公告
     */
    @PostMapping("/deleteAnnouncement")
    @ApiOperation(value = "删除公告", notes = "")
    public R deleteAnnouncement(@RequestBody deleteAnnouncementVo vo){
        try {
            R result = liAnnouncementService.deleteAnnouncement(vo);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("删除公告失败");
            return R.error().message("删除失败");
        }
    }

    /**
     * 查询公告
     */
    @PostMapping("/queryAnnouncement")
    @ApiOperation(value = "查询公告", notes = "")
    public R queryAnnouncement(@RequestBody queryAnnouncementVo vo){
        try {
            R result = liAnnouncementService.queryAnnouncement(vo);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("查询公告失败");
            return R.error().message("查询失败");
        }
    }

}

