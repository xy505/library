package team.library;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import team.library.entity.LiBookClass;
import team.library.service.LiBookClassService;

import java.util.ArrayList;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {LibraryApplication.class})
class LibraryApplicationTest {

    @Autowired
    LiBookClassService liBookClassService;

    @Test
    void getAllClass() {
        ArrayList<LiBookClass> allClass = liBookClassService.getAllClass();
        System.out.println(allClass);
    }

}
