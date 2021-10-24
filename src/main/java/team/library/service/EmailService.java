package team.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Random;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    private String regist="You have successfully registered your account";

    /**
     * 发送邮件
     */
    public void sendRegisterEmail(String toWho,String code){
        SimpleMailMessage msg = new SimpleMailMessage();    //构建一个邮件对象
        msg.setSubject("This is a registration notification email"); // 设置邮件主题
        msg.setFrom("253097162@qq.com"); // 设置邮箱发送者
        msg.setTo(toWho); // 设置邮件接收者，可以有多个接收者
        msg.setSentDate(new Date());    // 设置邮件发送日期
        msg.setText(code);   // 设置邮件的正文
        javaMailSender.send(msg);
    }

    /**
     * 随机生成6位数的验证码
     * @return String code
     */
    public String randomCode(){
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }


    /**
     * 发送检验邮箱
     * @param toWho
     */
    public void vertifyMail(String toWho){
        String code = randomCode();
        System.out.println("邮箱code"+code);
        sendRegisterEmail(toWho,code);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().setAttribute("picCode", code);
    }


}
