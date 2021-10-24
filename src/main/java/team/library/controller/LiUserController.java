package team.library.controller;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import team.library.common.R;
import team.library.common.utils.JwtUtils;
import team.library.entity.LiUser;
import team.library.service.EmailService;
import team.library.service.LiUserService;
import team.library.vo.user.blackUserVo;
import team.library.vo.user.editUserVo;
import team.library.vo.user.loginVo;
import team.library.vo.user.registerVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-10-08
 */
//@CrossOrigin
@RestController
@RequestMapping("/library/li-user")
public class LiUserController {

    @Autowired
    LiUserService liUserService;

    @Autowired
    DefaultKaptcha captchaProducer;

    @Autowired
    EmailService emailService;

    /**
     * 查询所有用户
     */
    @GetMapping("/getAllUser")
    @ApiOperation(value = "查询所有用户", notes = "")
    public R getAllUser(){
        List<LiUser> allUser = liUserService.getAllUser();
        if(!StringUtils.isEmpty(allUser)){
            return R.ok().data("allUser",allUser);
        }
        return R.error().message("查询失败");
    }

    /**
     * 用户登陆
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登陆", notes = "")
    public R login(@RequestBody loginVo loginVo){
        LiUser user = liUserService.queryUser(loginVo.getUsername(), loginVo.getPassword());
        //获取生成的验证码
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        System.out.println(request.getSession());
        String verificationCodeIn = (String)request.getSession().getAttribute("picCode");
        request.getSession().removeAttribute("picCode");
        System.out.println("verificationCodeIn"+verificationCodeIn);
        System.out.println("code"+loginVo.getCode());
        System.out.println(loginVo.getCode().equals(verificationCodeIn));
        if(!loginVo.getCode().equals(verificationCodeIn)){
            return R.error().data("message","验证码出错");
        }
        if(user!=null){
            //生成token
            String jwtToken = JwtUtils.getJwtToken(user.getId().toString(), user.getNickName());
            //返回token
            return R.ok().data("token",jwtToken);
        }else{
            return R.error().data("message","用户不存在");
        }
//        return R.ok().data("user",user);
    }


    /**
     * 发送注册邮箱
     * @param email
     * @param httpServletRequest
     */
    @GetMapping("/sendEmail")
    @ApiOperation(value = "发送注册邮箱", notes = "")
    public void sendEmail(String email, HttpServletRequest httpServletRequest){
        String code = emailService.randomCode();
        httpServletRequest.getSession().setAttribute("picCode", code);
        //发送邮箱
        emailService.sendRegisterEmail(email,code);
    }

    @PostMapping("/regist")
    @ApiOperation(value = "注册", notes = "")
    public R regist(@RequestBody registerVo vo){
        //获取生成的验证码
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String verificationCodeIn = (String)request.getSession().getAttribute("picCode");
        if (!vo.getCode().equals(verificationCodeIn)){
            return R.error().data("message","验证码出错");
        }
        LiUser liUser = liUserService.registerUser(vo);
        if (liUser==null){
            return R.error().message("注册失败");
        }
        return R.ok().message("注册成功");
    }

    @PostMapping("/editUser")
    @ApiOperation(value = "编辑用户信息", notes = "")
    public R edit(@RequestBody editUserVo vo){
        LiUser liUser = liUserService.editUser(vo);
        return R.ok().data("修改后的用户信息",liUser);
    }

    @PostMapping("/joinBlacklist")
    @ApiOperation(value = "加入黑名单", notes = "")
    public R black(@RequestBody blackUserVo vo){
        liUserService.joinBlacklist(vo);
        return R.ok().message("成功加入黑名单");
    }

    @GetMapping("/queryFromBlack")
    @ApiOperation(value = "查询所有黑名单里的用户", notes = "")
    public R queryFromBlack(){
        List<LiUser> userFromBlack = liUserService.getUserFromBlack();
        return R.ok().data("黑名单里的所有用户",userFromBlack);
    }
}

