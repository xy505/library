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
import team.library.vo.pageQuery;
import team.library.vo.user.*;

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
    @PostMapping("/getAllUser")
    @ApiOperation(value = "查询所有用户", notes = "")
    public R getAllUser(@RequestBody pageQuery pageQuery){
        return liUserService.getAllUser(pageQuery);
    }

    @PostMapping("/commonLogin")
    @ApiOperation(value = "普通用户登陆", notes = "")
    public R commonLogin(@RequestBody loginVo loginVo){
        LiUser user = liUserService.queryUser(loginVo.getUsername(), loginVo.getPassword());
        if (user!=null){
            //生成token
            String jwtToken = JwtUtils.getJwtToken(user.getId().toString(), user.getNickName());
            //返回token
            return R.ok().data("token",jwtToken);
        }else {
            return R.error().data("message","用户不存在");
        }
    }

    /**
     * 用户登陆
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登陆", notes = "")
    public R login(@RequestBody loginVo loginVo){
        LiUser user = liUserService.queryUser(loginVo.getUsername(), loginVo.getPassword());
        //获取生成的验证码
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        System.out.println(request.getSession());
//        String verificationCodeIn = (String)request.getSession().getAttribute("picCode");
//        request.getSession().removeAttribute("picCode");
//        System.out.println("verificationCodeIn"+verificationCodeIn);
//        System.out.println("code"+loginVo.getCode());
//        System.out.println(loginVo.getCode().equals(verificationCodeIn));
//        if(!loginVo.getCode().equals(verificationCodeIn)){
//            return R.error().data("message","验证码出错");
//        }
        if(user!=null){
            if (user.getIdentity()!=1){
                return R.error().data("message","用户权限不够");
            }
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
        return liUserService.registerUser(vo);
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
        return liUserService.joinBlacklist(vo);
    }

    @PostMapping("/queryFromBlack")
    @ApiOperation(value = "查询所有黑名单里的用户", notes = "")
    public R queryFromBlack(@RequestBody pageQuery pageQuery){

        return liUserService.getUserFromBlack(pageQuery);
    }

    @PostMapping("/deleteUser")
    @ApiOperation(value = "注销用户", notes = "")
    public R deleteUser(@RequestBody deleteUserVo vo){
        return liUserService.deleteUser(vo);
    }
}

