package team.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import team.library.common.R;
import team.library.common.utils.MD5;
import team.library.entity.LiBook;
import team.library.entity.LiUser;
import team.library.mapper.LiUserMapper;
import team.library.service.LiUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.library.vo.pageQuery;
import team.library.vo.user.blackUserVo;
import team.library.vo.user.deleteUserVo;
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
    public R getAllUser(pageQuery vo) {
        Page<LiUser> liUserPage = new Page<LiUser>(vo.getPage(), vo.getLimit());
        QueryWrapper<LiUser> userWrapper = new QueryWrapper<>();
        IPage<LiUser> liUserIPage = this.page(liUserPage, userWrapper);
        return R.ok().data("user",liUserIPage);
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
    public R registerUser(registerVo vo) {
        LiUser liUser = new LiUser();
        //判断是否已经存在
        List<LiUser> liUsers = baseMapper.selectList(new QueryWrapper<>());
        for (LiUser user:liUsers) {
            if (user.getEmail().equals(vo.getEmail())){
                return R.error().message("邮箱已注册过");
            }
            if (user.getNickName().equals(vo.getUsername())){
                return R.error().message("用户名重复");
            }
        }
        //获取生成的验证码
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String verificationCodeIn = (String)request.getSession().getAttribute("picCode");
        if (!vo.getCode().equals(verificationCodeIn)){
            return R.error().data("message","验证码出错");
        }
        liUser.setEmail(vo.getEmail());
        liUser.setPassWord(MD5.encrypt(vo.getPassword()));
        liUser.setNickName(vo.getUsername());
        int insert = baseMapper.insert(liUser);
        if (insert!=1){
            return R.error().message("注册失败");
        }
        return R.ok().message("注册成功");
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
    public R joinBlacklist(blackUserVo vo) {
        LiUser liUser = baseMapper.selectById(vo.getId());
        if (liUser!=null){
            liUser.setIsDisabled(true);
        }
        int result = baseMapper.updateById(liUser);
        if (result!=1){
            return R.error().message("加入失败");
        }else {
            return R.ok().message("加入成功");
        }
    }

    /**
     * 从黑名单里拿出所有用户
     * @return
     */
    @Override
    public R getUserFromBlack(pageQuery vo) {
        Page<LiUser> liUserPage = new Page<LiUser>(vo.getPage(), vo.getLimit());
        QueryWrapper<LiUser> wrapper = new QueryWrapper<>();
        wrapper.eq("is_disabled",1);
        IPage<LiUser> page = this.page(liUserPage, wrapper);
        return R.ok().data("black",page);
    }

    @Override
    public R deleteUser(deleteUserVo vo) {
        int result = baseMapper.deleteById(vo.getId());
        if (result!=1){
            return R.error().message("注销失败");
        }else {
            return R.ok().message("注销成功");
        }
    }


}
