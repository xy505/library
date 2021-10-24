package team.library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {LibraryApplication.class})
public class EmailTest {

    @Autowired
    JavaMailSender javaMailSender;
    @Test
    public void sendEmail(){
        SimpleMailMessage msg = new SimpleMailMessage();    //构建一个邮件对象
        msg.setSubject("这是一封测试邮件"); // 设置邮件主题
        msg.setFrom("253097162@qq.com"); // 设置邮箱发送者
        msg.setTo("2708845964@qq.com"); // 设置邮件接收者，可以有多个接收者
        msg.setSentDate(new Date());    // 设置邮件发送日期
        msg.setText("这是测试邮件的正文");   // 设置邮件的正文
        javaMailSender.send(msg);
    }
}
