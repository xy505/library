package team.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import team.library.common.utils.MD5;
import team.library.entity.LiUser;
import team.library.mapper.LiUserMapper;
import team.library.service.LiUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.library.vo.user.blackUserVo;
import team.library.vo.user.editUserVo;
import team.library.vo.user.registerVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-10-08
 */
@Service
public class LiUserServiceImpl extends ServiceImpl<LiUserMapper, LiUser> implements LiUserService {

    /**
     * 查询所有用户
     */
    @Override
    public List<LiUser> getAllUser() {
        QueryWrapper<LiUser> userWrapper = new QueryWrapper<>();
        return baseMapper.selectList(userWrapper);
    }

    /**
     * 登陆
     * @param nickName
     * @param passWord
     * @return
     */
    @Override
    public LiUser queryUser(String nickName, String passWord) {
        QueryWrapper<LiUser> query = new QueryWrapper<>();
        query.eq("nickName",nickName);
        query.eq("passWord", MD5.encrypt(passWord));
        LiUser liUser = baseMapper.selectOne(query);
        return liUser;
    }

    /**
     * 注册用户
     * @param vo
     * @return
     */
    @Override
    public LiUser registerUser(registerVo vo) {
        LiUser liUser = new LiUser();
        liUser.setEmail(vo.getEmail());
        liUser.setPassWord(MD5.encrypt(vo.getPassword()));
        liUser.setNickName(vo.getUsername());
        int insert = baseMapper.insert(liUser);
        if (insert!=1){
            return null;
        }
        return liUser;
    }

    @Override
    public LiUser editUser(editUserVo vo) {
        LiUser liUser = baseMapper.selectById(vo.getId());
        if (StringUtils.isNotBlank(vo.getNickName())){
            liUser.setNickName(vo.getNickName());
        }
        if (StringUtils.isNotBlank(vo.getAvatar())){
            liUser.setAvatar(vo.getAvatar());
        }
        if (StringUtils.isNotBlank(vo.getPassWord())){
            //邮箱验证
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (!vo.getCode().equals(request.getSession().getAttribute("picCode"))){
                System.out.println("验证码错误");
                return null;
            }else {
                liUser.setPassWord(MD5.encrypt(vo.getPassWord()));
            }
        }
        int result = baseMapper.updateById(liUser);
        if (result!=1){
            return liUser;
        }else{
            return null;
        }
    }

    /**
     * 加入黑名单
     * @param vo
     */
    @Override
    public void joinBlacklist(blackUserVo vo) {
        LiUser liUser = baseMapper.selectById(vo.getId());
        if (liUser!=null){
            liUser.setIsDisabled(true);
        }
        baseMapper.updateById(liUser);
    }

    /**
     * 从黑名单里拿出所有用户
     * @return
     */
    @Override
    public List<LiUser> getUserFromBlack() {
        QueryWrapper<LiUser> wrapper = new QueryWrapper<>();
        wrapper.eq("is_disabled",1);
        List<LiUser> liUsers = baseMapper.selectList(wrapper);
        return liUsers;
    }


}
