package team.library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import team.library.common.R;
import team.library.controller.KaptchaController;
import team.library.controller.LiUserController;
import team.library.entity.LiUser;
import team.library.service.EmailService;
import team.library.service.LiUserService;
import team.library.vo.user.blackUserVo;
import team.library.vo.user.deleteUserVo;
import team.library.vo.user.editUserVo;
import team.library.vo.user.registerVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {LibraryApplication.class})
public class LiUserTest {

    @Autowired
    KaptchaController kaptchaController;

    @Autowired
    LiUserController liUserController;

    @Autowired
    LiUserService liUserService;

    @Autowired
    EmailService emailService;
    /**
     * 验证码
     */
    @Test
    public void kaptcha(){

    }

    /**
     * 注册
     */
    @Test
    public void register(){
//        String code = emailService.randomCode();
//        System.out.println("邮箱code"+code);
//        emailService.sendRegisterEmail("2708845964@qq.com",code);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        request.getSession().setAttribute("picCode", code);
        emailService.vertifyMail("2708845964@qq.com");
        registerVo registerVo = new registerVo();
        registerVo.setCode("1234");
        registerVo.setEmail("2708845964@qq.com");
        registerVo.setPassword("123456");
        registerVo.setUsername("谁啊");
        if (registerVo.getCode().equals(request.getSession().getAttribute("picCode"))){
            R regist = liUserController.regist(registerVo);
            System.out.println(regist.getCode());
        }else {
            System.out.println("验证码出错");
        }
    }

    /**
     * 加入黑名单操作
     */
    @Test
    public void hei(){
        blackUserVo vo = new blackUserVo();
        vo.setId(1);
        liUserService.joinBlacklist(vo);
    }

    /**
     * 所有黑名单里的用户
     */
    @Test
    public void getFromBlack(){
//        List<LiUser> userFromBlack = liUserService.getUserFromBlack();
//        System.out.println(userFromBlack);
    }

    /**
     * 编辑用户信息
     */
    @Test
    public void edit(){
        editUserVo vo = new editUserVo();
        vo.setId(1);
        vo.setNickName("用户一");
        vo.setPassWord("1234");
        vo.setCode("1234");
        //这里发送邮箱
        emailService.vertifyMail("2708845964@qq.com");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (vo.getCode().equals(request.getSession().getAttribute("picCode"))){
            LiUser liUser = liUserService.editUser(vo);
            System.out.println(liUser);
        }

    }

    /**
     * 注销用户
     */
    @Test
    public void delete(){
        deleteUserVo vo = new deleteUserVo();
        vo.setId(1);
        liUserService.deleteUser(vo);
    }

    /**
     * 查询所有用户
     */
    @Test
    public void query(){
//        List<LiUser> allUser = liUserService.getAllUser();
//        System.out.println(allUser);
    }
}
