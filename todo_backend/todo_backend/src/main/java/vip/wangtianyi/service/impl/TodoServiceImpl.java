package vip.wangtianyi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.wangtianyi.dao.TodoDao;
import vip.wangtianyi.domain.Todo;
import vip.wangtianyi.service.TodoService;

@Service
public class TodoServiceImpl extends ServiceImpl<TodoDao, Todo> implements TodoService {

}
