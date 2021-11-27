package team.library.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import team.library.common.R;
import team.library.service.LiSeatService;
import team.library.vo.book.borrowBookVo;
import team.library.vo.seat.addSeatVo;
import team.library.vo.seat.deleteSeatVo;
import team.library.vo.seat.querySeatVo;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-11-16
 */
@RestController
@RequestMapping("/library/li-seat")
public class LiSeatController {

    @Autowired
    LiSeatService liSeatService;

    /**
     * 用户预定功能
     * @param vo
     * @return
     */
    @PostMapping("/reserveSeat")
    @ApiOperation(value = "用户预定", notes = "")
    public R reserveSeat(@RequestBody addSeatVo vo){
        try {
            R result = liSeatService.addSeat(vo);
            return result;
        }catch (Exception e){
            return R.error().message("系统出错");
        }
    }

    /**
     * 取消预定功能
     * @param vo
     * @return
     */
    @PostMapping("/deleteSeat")
    @ApiOperation(value = "取消预定", notes = "")
    public R deleteSeat(@RequestBody deleteSeatVo vo){
        try {
            R result = liSeatService.deleteSeat(vo);
            return result;
        }catch (Exception e){
            return R.error().message("系统出错");
        }
    }

    /**
     * 常看当前楼层情况
     * @param vo
     * @return
     */
    @PostMapping("/querySeat")
    @ApiOperation(value = "常看当前楼层情况", notes = "")
    public R querySeat(@RequestBody querySeatVo vo){
        try {
            R result = liSeatService.querySeat(vo);
            return result;
        }catch (Exception e){
            return R.error().message("系统出错");
        }
    }

    /**
     * 常看自己预定情况
     * @param vo
     * @return
     */
    @PostMapping("/queryUserSeat")
    @ApiOperation(value = "常看自己预定情况", notes = "")
    public R queryUserSeat(@RequestBody querySeatVo vo){
        try {
            R result = liSeatService.queryUserSeat(vo);
            return result;
        }catch (Exception e){
            return R.error().message("系统出错");
        }
    }
}

