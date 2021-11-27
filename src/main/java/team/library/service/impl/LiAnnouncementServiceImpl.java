package team.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import team.library.common.R;
import team.library.entity.LiAnnouncement;
import team.library.mapper.LiAnnouncementMapper;
import team.library.service.LiAnnouncementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.library.vo.announcement.addAnnouncementVo;
import team.library.vo.announcement.deleteAnnouncementVo;
import team.library.vo.announcement.queryAnnouncementVo;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-11-10
 */
@Service
public class LiAnnouncementServiceImpl extends ServiceImpl<LiAnnouncementMapper, LiAnnouncement> implements LiAnnouncementService {

    @Override
    public R addAnnouncement(addAnnouncementVo vo) {
        LiAnnouncement liAnnouncement = new LiAnnouncement();
        liAnnouncement.setTitle(vo.getTitle());
        liAnnouncement.setDetail(vo.getDetail());
        int insert = this.baseMapper.insert(liAnnouncement);
        if (insert!=1){
            return R.error().message("添加失败");
        }else {
            return R.ok().message("添加成功");
        }
    }

    @Override
    public R deleteAnnouncement(deleteAnnouncementVo vo) {
        LiAnnouncement liAnnouncement = this.baseMapper.selectById(vo.getId());
        if (liAnnouncement==null){
            return R.error().message("不存在该公告");
        }
        int i = this.baseMapper.deleteById(vo.getId());
        if (i!=1){
            return R.error().message("删除失败");
        }else {
            return R.ok().message("删除成功");
        }
    }

    @Override
    public R queryAnnouncement(queryAnnouncementVo vo) {
        Page<LiAnnouncement> page = new Page<>(vo.getPage(),vo.getLimit());
        QueryWrapper<LiAnnouncement> wrapper = new QueryWrapper<>();
        wrapper.last("order by gmt_create desc");
        IPage<LiAnnouncement> page1 = this.page(page, wrapper);
        return R.ok().data("Announcement",page1);
    }
}
