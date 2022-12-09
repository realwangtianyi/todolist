package vip.wangtianyi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vip.wangtianyi.controller.utils.R;
import vip.wangtianyi.domain.Todo;
import vip.wangtianyi.service.TodoService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodoService todoService;

//    @GetMapping("{id}")
//    public R getById(@PathVariable Integer id){
//        return new R(true, todoService.getById(id));
//    }

    @GetMapping
    public R getAll(){
        return new R(true, todoService.selectUndeleted());
    }

    @PostMapping
    public R save(@RequestBody Todo todo){
        System.out.println("收到save请求");
        return new R(todoService.save(todo));
    }


    @PutMapping
    public R update(@RequestBody Todo todo){
        return new R(todoService.updateById(todo));
    }

    @GetMapping("{id}")
    public R updateDoneById(@PathVariable Integer id){
        return new R(todoService.updateDone(id)>=1);
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        return new R(todoService.deleteOneById(id)>=1);
    }

    @DeleteMapping
    public R deleteCompleted(){
        int lines = 0;
        List<Todo> todos = todoService.selectUndeleted();
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).isDone()) {
                lines+=todoService.deleteOneById(todos.get(i).getId());
            }
        }
        return new R(lines>0);
    }
}
