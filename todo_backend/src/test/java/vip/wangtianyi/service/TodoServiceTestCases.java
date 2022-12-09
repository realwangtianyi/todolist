package vip.wangtianyi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vip.wangtianyi.domain.Todo;

@SpringBootTest
public class TodoServiceTestCases {

    @Autowired
    private TodoService todoService;

    @Test
    void testGetById(){
        System.out.println(todoService.getById(1));
        System.out.println(todoService.list());
    }

    @Test
    void testSave(){
        Todo todo = new Todo();
        todo.setName("烫头");
//        todo.setDone(false);
//        todo.setDeleted(false);
        todoService.save(todo);
    }

    @Test
    void testUpdate(){
        Todo todo = new Todo();
        todo.setId(1);
        todo.setDone(true);
        todo.setName("吃晚饭");
        todo.setDeleted(true);
        todoService.updateById(todo);
    }

}
