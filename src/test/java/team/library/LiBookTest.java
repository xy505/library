package team.library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import team.library.common.R;
import team.library.service.LiBookService;
import team.library.vo.book.addBookVo;
import team.library.vo.book.deleteBookVo;
import team.library.vo.book.editBookVo;
import team.library.vo.book.queryBookVo;

import java.util.ArrayList;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {LibraryApplication.class})
public class LiBookTest {

    @Autowired
    LiBookService liBookService;

    @Test
    public void queryBook(){
        queryBookVo vo = new queryBookVo();
        vo.setPage(1);
        vo.setLimit(5);
        vo.setAuthor("");
        vo.setBookName("");
        ArrayList<Integer> sorts = new ArrayList<>();
        sorts.add(1);
        sorts.add(2);
        vo.setSorts(sorts);
        R r = liBookService.queryBook(vo);
        System.out.println(r.getData());
    }

    @Test
    public void addBook(){
        addBookVo vo = new addBookVo();
        vo.setAuthor("无名");
        vo.setBookName("路人甲");
        vo.setCover("");
        vo.setDescription("");
        vo.setDonor("");
        vo.setIsDisabled(false);
        vo.setNumber(5);
        ArrayList<Integer> sorts = new ArrayList<>();
        sorts.add(2);
        sorts.add(3);
        vo.setSorts(sorts);
        liBookService.addBook(vo);
    }

    @Test
    public void editBook(){
        editBookVo vo = new editBookVo();
        vo.setId(2);
        vo.setAuthor("无名");
        vo.setBookName("路人甲");
        vo.setCover("");
        vo.setDescription("");
        vo.setDonor("");
        vo.setIsDisabled(false);
        vo.setNumber(5);
        ArrayList<Integer> sorts = new ArrayList<>();
        sorts.add(2);
        sorts.add(5);
        vo.setSorts(sorts);
        liBookService.editBook(vo);
    }

    @Test
    public void deleteBook(){
        deleteBookVo vo = new deleteBookVo();
        vo.setId(2);
        liBookService.deleteBook(vo);
    }
}
