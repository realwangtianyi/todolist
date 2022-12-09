package vip.wangtianyi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import vip.wangtianyi.domain.Todo;

import java.util.List;

@Mapper
public interface TodoDao extends BaseMapper<Todo> {

    @Select({"select * from todo where deleted = false order by id desc "})
    List<Todo> selectUndeleted();

    @Update({"update todo set deleted = true where id = #{id}"})
    int deleteOneById(Integer id);

    @Update({"update todo set done = #{done} where id =#{id};"})
    int updateDone(Integer id, Boolean done);



}
