package vip.wangtianyi.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vip.wangtianyi.controller.utils.R;
import vip.wangtianyi.domain.Todo;
import vip.wangtianyi.service.TodoService;

import java.awt.print.Book;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/index")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("{id}")
    public R getById(@PathVariable Integer id){
        return new R(true, todoService.getById(id));
    }

    @GetMapping
    public R getAll(){
        return new R(true, todoService.list());
    }

    @PostMapping
    public R save(@RequestBody Todo todo){
        return new R(todoService.save(todo));
    }


    @PutMapping
    public R update(@RequestBody Todo todo){
        return new R(todoService.updateById(todo));
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        return new R(todoService.removeById(id));
    }
}
