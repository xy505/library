package team.library.service;

import team.library.entity.LiUser;
import com.baomidou.mybatisplus.extension.service.IService;
import team.library.vo.user.blackUserVo;
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
    List<LiUser> getAllUser();

    /**
     *查询用户是否存在
     */
    LiUser queryUser(String nickName,String passWord);

    /**
     *注册用户
     */
    LiUser registerUser(registerVo vo);

    /**
     *编辑用户信息
     */
    LiUser editUser(editUserVo vo);

    /**
     * 加入黑名单
     */
    void joinBlacklist(blackUserVo vo);

    /**
     * 查询所有黑名单里的用户
     */
    List<LiUser> getUserFromBlack();




}
