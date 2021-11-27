package team.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import team.library.common.R;
import team.library.entity.LiBook;
import team.library.mapper.LiBookMapper;
import team.library.service.LiBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.library.vo.book.*;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-10-11
 */
@Service
public class LiBookServiceImpl extends ServiceImpl<LiBookMapper, LiBook> implements LiBookService {

    @Autowired
    LiBookMapper liBookMapper;

    /**
     * 根据条件获取图书
     * @return
     */
    @Override
    public R queryBook(queryBookVo vo) {
        //条件查询
        Page<LiBook> page = new Page<>(vo.getPage(), vo.getLimit());
        System.out.println(page);

//        Page<LiBook> liBookPage = liBookMapper.queryBook(bookQueryVo,page);
        QueryWrapper<LiBook> wrapper = new QueryWrapper<>();
        ArrayList<Integer> sorts = vo.getSorts();
        if(!sorts.isEmpty()){
            sorts.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1-o2;
                }
            });
            String s="";
            for (Integer i: sorts) {
                if(s.equals("")){
                    s+=i+"";
                }else{
                    s+=",%"+i;
                }

            }
            wrapper.like("sortId","%"+s+"%");

        }
        if (StringUtils.isNotBlank(vo.getAuthor())) {
            wrapper.like("author","%"+vo.getAuthor()+"%");
        }
        if (StringUtils.isNotBlank(vo.getBookName())) {
            wrapper.like("bookName","%"+vo.getBookName()+"%");
        }
        IPage<LiBook> result = this.page(page, wrapper);
        System.out.println(result);
        return R.ok().data("result",result);
    }

    @Override
    public R editBook(editBookVo vo) {
        LiBook liBook = baseMapper.selectById(vo.getId());
        if (StringUtils.isNotBlank(vo.getAuthor())){
            liBook.setAuthor(vo.getAuthor());
        }
        if (StringUtils.isNotBlank(vo.getBookName())){
            liBook.setBookName(vo.getBookName());
        }
        if (StringUtils.isNotBlank(vo.getCover())){
            liBook.setCover(vo.getCover());
        }
        if (StringUtils.isNotBlank(vo.getDescription())){
            liBook.setDescription(vo.getDescription());
        }
        if (StringUtils.isNotBlank(vo.getDonor())){
            liBook.setDonor(vo.getDonor());
        }
        if (vo.getIsDisabled()!=null){
            liBook.setIsDisabled(vo.getIsDisabled());
        }
        if (vo.getNumber()!=null){
            liBook.setNumber(vo.getNumber());
        }
        //分类
        ArrayList<Integer> sorts = vo.getSorts();
        if(!sorts.isEmpty()){
            sorts.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1-o2;
                }
            });
            String s="";
            for (Integer i: sorts) {
                if(s.equals("")){
                    s+=i+"";
                }else{
                    s+=","+i;
                }

            }
            liBook.setSortId(s);
        }

        int result = baseMapper.updateById(liBook);
        if (result!=1){
            return R.error().message("编辑失败");
        }
        return R.ok().message("编辑成功");
    }

    @Override
    public R addBook(addBookVo vo) {
        LiBook liBook = new LiBook();
        if (StringUtils.isNotBlank(vo.getAuthor())){
            liBook.setAuthor(vo.getAuthor());
        }
        if (StringUtils.isNotBlank(vo.getBookName())){
            liBook.setBookName(vo.getBookName());
        }
        if (StringUtils.isNotBlank(vo.getCover())){
            liBook.setCover(vo.getCover());
        }
        if (StringUtils.isNotBlank(vo.getDescription())){
            liBook.setDescription(vo.getDescription());
        }
        if (StringUtils.isNotBlank(vo.getDonor())){
            liBook.setDonor(vo.getDonor());
        }
        if (vo.getIsDisabled()!=null){
            liBook.setIsDisabled(vo.getIsDisabled());
        }
        if (vo.getNumber()!=null){
            liBook.setNumber(vo.getNumber());
        }
        //分类
        ArrayList<Integer> sorts = vo.getSorts();
        if(!sorts.isEmpty()){
            sorts.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1-o2;
                }
            });
            String s="";
            for (Integer i: sorts) {
                if(s.equals("")){
                    s+=i+"";
                }else{
                    s+=","+i;
                }

            }
            liBook.setSortId(s);
        }
        int result = baseMapper.insert(liBook);
        if (result!=1){
            return R.error().message("添加失败");
        }
        return R.ok().message("添加成功");
    }

    @Override
    public R deleteBook(deleteBookVo vo) {
        int result = baseMapper.deleteById(vo.getId());
        if (result!=1){
            return R.error().message("删除失败");
        }
        return R.ok().message("删除成功");
    }

    @Override
    public R queryUserBook(donateBookVo vo) {
        String userName = vo.getUserName();
        QueryWrapper<LiBook> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(vo.getUserName())){
            //前台查询用的
            wrapper.eq("donor",userName);
        }else {
            //后台查询用的
            wrapper.ne("donor","");
            wrapper.last("order by gmt_create desc");
        }
        Page<LiBook> liBookPage = new Page<>(vo.getPage(), vo.getLimit());
        IPage<LiBook> page = this.page(liBookPage, wrapper);
        return R.ok().data("book",page);
    }

    //根据id查询书名
    public String getBookNameById(Integer id){
        LiBook liBook = this.baseMapper.selectById(id);
        return liBook.getBookName();
    }


}
