package team.library.service;

import io.swagger.models.auth.In;
import team.library.common.R;
import team.library.entity.LiUser;
import com.baomidou.mybatisplus.extension.service.IService;
import team.library.vo.pageQuery;
import team.library.vo.user.blackUserVo;
import team.library.vo.user.deleteUserVo;
import team.library.vo.user.editUserVo;
import team.library.vo.user.registerVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-10-08
 */
public interface LiUserService extends IService<LiUser> {
    /**
     * 查询所有用户
     */
    R getAllUser(pageQuery page);

    /**
     *查询用户是否存在
     */
    LiUser queryUser(String nickName,String passWord);

    /**
     *注册用户
     */
    R registerUser(registerVo vo);

    /**
     *编辑用户信息
     */
    LiUser editUser(editUserVo vo);

    /**
     * 加入黑名单
     */
    R joinBlacklist(blackUserVo vo);

    /**
     * 查询所有黑名单里的用户
     */
    R getUserFromBlack(pageQuery pageQuery);

    /**
     * 注销用户
     */
    R deleteUser(deleteUserVo vo);


    /**
     * 根据用户id查询到名字
     */
    String getById(Integer id);



}
