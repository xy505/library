package team.library.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.library.entity.LiBook;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-10-11
 */
@Mapper
@Repository
public interface LiBookMapper extends BaseMapper<LiBook> {

    /**
     * 条件查询书籍
     */
//    @SelectProvider(type = LiBookProvider.class, method = "queryBook")
//    Page<LiBook> queryBook(@Param("vo") bookQueryVo vo,Page<LiBook> queryBookModelPage);

}
