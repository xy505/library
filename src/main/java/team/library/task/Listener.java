package team.library.task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import team.library.controller.LiAnnouncementController;
import team.library.entity.LiSeat;
import team.library.entity.LiUserBook;
import team.library.service.LiSeatService;
import team.library.service.LiUserBookService;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class Listener {

    private Logger logger = LoggerFactory.getLogger(LiAnnouncementController.class);

    @Autowired
    LiUserBookService liUserBookService;

    @Autowired
    LiSeatService liSeatService;
    //0 0 0 1/1 * ?
    //每天中午的十二点触发
    @Scheduled(cron = "0 0 12 * * ?")
    public void overTime(){
        logger.info("定时任务1:"+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
        System.out.println("过一天了");
        liUserBookService.updateBorrowIngTime();
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void seatoverTime(){
        logger.info("定时任务2:"+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
        System.out.println("过一小时了");
        liSeatService.updateTime();
    }

}
