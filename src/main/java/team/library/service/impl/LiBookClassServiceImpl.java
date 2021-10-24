package team.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import team.library.entity.LiBookClass;
import team.library.mapper.LiBookClassMapper;
import team.library.service.LiBookClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-10-11
 */
@Service
public class LiBookClassServiceImpl extends ServiceImpl<LiBookClassMapper, LiBookClass> implements LiBookClassService {

    /**
     * 获取所有分类
     * @return
     */
    @Override
    public ArrayList<LiBookClass> getAllClass() {
        QueryWrapper<LiBookClass> classes = new QueryWrapper<>();
        ArrayList<LiBookClass> liBookClasses = (ArrayList<LiBookClass>) this.baseMapper.selectList(classes);
        return liBookClasses;
    }
}
