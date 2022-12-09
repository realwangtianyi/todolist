package vip.wangtianyi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.wangtianyi.dao.TodoDao;
import vip.wangtianyi.domain.Todo;
import vip.wangtianyi.service.TodoService;

import java.util.List;

@Service
public class TodoServiceImpl extends ServiceImpl<TodoDao, Todo> implements TodoService {

    @Autowired
    private TodoDao todoDao;


    @Override
    public List<Todo> selectUndeleted() {
        return todoDao.selectUndeleted();
    }

    @Override
    public int deleteOneById(Integer id) {
        return todoDao.deleteOneById(id);
    }

    @Override
    public int updateDone(Integer id) {
        return todoDao.updateDone(id, !todoDao.selectById(id).isDone());
    }
}
