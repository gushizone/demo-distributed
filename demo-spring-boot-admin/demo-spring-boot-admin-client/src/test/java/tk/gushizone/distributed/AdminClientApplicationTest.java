package tk.gushizone.distributed;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.gushizone.distributed.domain.Item;
import tk.gushizone.distributed.service.ItemService;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminClientApplication.class)
public class AdminClientApplicationTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void test() {

        List<Item> list = itemService.list();
        System.out.println(list);

    }


}