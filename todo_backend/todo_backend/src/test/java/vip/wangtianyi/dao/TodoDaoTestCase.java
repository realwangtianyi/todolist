package vip.wangtianyi.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TodoDaoTestCase {

    @Autowired
    private TodoDao todoDao;

    @Test
    void testGetById(){
        System.out.println(todoDao.selectById(1));
        System.out.println("哈哈");
    }

}
