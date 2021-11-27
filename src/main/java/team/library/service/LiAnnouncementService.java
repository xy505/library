package team.library.service;

import team.library.common.R;
import team.library.entity.LiAnnouncement;
import com.baomidou.mybatisplus.extension.service.IService;
import team.library.vo.announcement.addAnnouncementVo;
import team.library.vo.announcement.deleteAnnouncementVo;
import team.library.vo.announcement.queryAnnouncementVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-11-10
 */
public interface LiAnnouncementService extends IService<LiAnnouncement> {

    /**
     * 添加公告
     */
    R addAnnouncement(addAnnouncementVo vo);

    /**
     * 删除公告
     */
    R deleteAnnouncement(deleteAnnouncementVo vo);

    /**
     * 查询公告
     */
    R queryAnnouncement(queryAnnouncementVo vo);

}
