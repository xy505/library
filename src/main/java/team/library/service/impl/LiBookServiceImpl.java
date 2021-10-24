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
import team.library.vo.book.bookQueryVo;

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
    public R queryBook(bookQueryVo vo) {
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
                    s+=","+i;
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
        return R.ok().data("分页查询结果",result);
    }


}