package team.library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import team.library.common.utils.MD5;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {LibraryApplication.class})
public class encodeTest {
    @Test
    public void contextLoads() {
        MD5 Md5Crypt = new MD5();

        //$1$YdkJTmB1$jsWeFyCOFNJ1jXRp3rHJe1
        String s = Md5Crypt.encrypt("123456");

        //第一次：$1$88888888$.04CpISZfzlbsDnC6Fjr11
        //第二次：$1$88888888$.04CpISZfzlbsDnC6Fjr11
//        String s = Md5Crypt.md5Crypt("123456".getBytes(),"$1$88888888");

        System.out.println(s);
    }
}
