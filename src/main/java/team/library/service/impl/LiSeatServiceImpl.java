package team.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import team.library.common.R;
import team.library.entity.LiSeat;
import team.library.mapper.LiSeatMapper;
import team.library.service.LiSeatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.library.service.LiUserService;
import team.library.vo.seat.addSeatVo;
import team.library.vo.seat.deleteSeatVo;
import team.library.vo.seat.querySeatVo;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-11-16
 */
@Service
public class LiSeatServiceImpl extends ServiceImpl<LiSeatMapper, LiSeat> implements LiSeatService {

    @Autowired
    LiUserService liUserService;
    /**
     * 预定位置
     * @param vo
     * @return
     */
    @Override
    public R addSeat(addSeatVo vo) {
        System.out.println(vo);
        /**
         * 看当前位置是否已经被预定了
         */
        LiSeat liSeat = this.isExist(vo.getFloor(), vo.getColumn(), vo.getRow());
        if (liSeat!=null){
            return R.error().message("座位已经被预定了");
        }

        /**
         * 一个用户只能预定一个位置
         */
        if(!this.ifHas(vo.getUserId())){
            return R.error().message("您已经预定过了");
        }
        //添加预定
        LiSeat seat = new LiSeat();
        seat.setCo(vo.getColumn());
        seat.setFl(vo.getFloor());
        seat.setRo(vo.getRow());
        seat.setHas(vo.getHas());
        seat.setUserId(vo.getUserId());
        int insert = this.baseMapper.insert(seat);
        System.out.println(insert);
        if (insert!=1){
            return R.error().message("预定失败");
        }
        return R .ok().message("预定成功");
    }

    /**
     * 位置预定取消
     * @param vo
     * @return
     */
    @Override
    public R deleteSeat(deleteSeatVo vo) {
        LiSeat liSeat = this.isExist(vo.getFloor(), vo.getColumn(), vo.getRow());
        if (liSeat==null){
            return R.error().message("这座位没有被预定");
        }
        int result = this.baseMapper.deleteById(liSeat.getId());
        if (result!=1){
            return R.error().message("取消失败");
        }
        return R .ok().message("取消成功");
    }

    /**
     * 查询当前楼层哪些位置预定
     * @param vo
     * @return
     */
    @Override
    public R querySeat(querySeatVo vo) {
        QueryWrapper<LiSeat> wrapper = new QueryWrapper<>();
        wrapper.eq("fl",vo.getFloor());
        List<LiSeat> liSeats = this.baseMapper.selectList(wrapper);
        for (LiSeat seat :
                liSeats) {
            Integer userId = seat.getUserId();
            String nickname = liUserService.getById(userId);
            seat.setNickName(nickname);
        }
        return R.ok().data("has",liSeats);
    }

    @Override
    public R queryUserSeat(querySeatVo vo) {
        QueryWrapper<LiSeat> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",vo.getUserId());
        LiSeat liSeat = this.baseMapper.selectOne(wrapper);
        return R.ok().data("seat",liSeat);
    }

    @Override
    public void updateTime() {
        List<LiSeat> liSeats = this.baseMapper.selectList(new QueryWrapper<LiSeat>());
        for (LiSeat seat :
                liSeats) {
            if ((seat.getHas()-1)<0){
                this.baseMapper.deleteById(seat.getId());
            }
            seat.setHas(seat.getHas()-1);
            this.baseMapper.updateById(seat);
        }
    }

    public LiSeat isExist(Integer floor, Integer column,Integer row){
        QueryWrapper<LiSeat> wrapper = new QueryWrapper<>();
        wrapper.eq("fl",floor);
        wrapper.eq("co",column);
        wrapper.eq("ro",row);
        LiSeat liSeat = this.baseMapper.selectOne(wrapper);
        return liSeat;
    }

    public boolean ifHas(Integer id){
        QueryWrapper<LiSeat> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",id);
        LiSeat liSeat = this.baseMapper.selectOne(wrapper);
        if (liSeat!=null){
            return false;
        }
        return true;
    }
}
