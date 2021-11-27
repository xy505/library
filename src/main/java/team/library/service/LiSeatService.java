package team.library.service;

import team.library.common.R;
import team.library.entity.LiSeat;
import com.baomidou.mybatisplus.extension.service.IService;
import team.library.vo.seat.addSeatVo;
import team.library.vo.seat.deleteSeatVo;
import team.library.vo.seat.querySeatVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-11-16
 */
public interface LiSeatService extends IService<LiSeat> {

    R addSeat(addSeatVo vo);

    R deleteSeat(deleteSeatVo vo);

    R querySeat(querySeatVo vo);

    R queryUserSeat(querySeatVo vo);

    //定时任务
    void updateTime();

}
