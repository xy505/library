package team.library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import team.library.entity.LiSeat;
import team.library.mapper.LiSeatMapper;
import team.library.service.LiSeatService;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {LibraryApplication.class})
public class LiSeatTest {
    @Autowired
    LiSeatService liSeatService;
    @Autowired
    LiSeatMapper liSeatMapper;
    @Test
    public void test(){
        LiSeat liSeat = new LiSeat();
        liSeat.setUserId(1);
        liSeat.setRo(1);
        liSeat.setCo(1);
        liSeat.setFl(1);
        liSeatMapper.insert(liSeat);
    }
}
