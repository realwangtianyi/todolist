package vip.wangtianyi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vip.wangtianyi.domain.Todo;

import java.util.List;

public interface TodoService extends IService<Todo> {

    List<Todo> selectUndeleted();

    int deleteOneById(Integer id);

    int updateDone(Integer id);

}
