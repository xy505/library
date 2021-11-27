package team.library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import team.library.service.LiUserBookService;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {LibraryApplication.class})
public class LiUserBookTest {

    @Autowired
    LiUserBookService liUserBookService;

    @Test
    public void updatetime(){
        liUserBookService.updateBorrowIngTime();
    }
}
