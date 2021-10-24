package team.library.service;

import team.library.entity.LiBookClass;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-10-11
 */
public interface LiBookClassService extends IService<LiBookClass> {

    public ArrayList<LiBookClass> getAllClass();

}
