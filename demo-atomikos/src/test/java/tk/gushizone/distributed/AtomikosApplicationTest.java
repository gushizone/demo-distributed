package tk.gushizone.distributed;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.gushizone.distributed.ds1.domain.ItemA;
import tk.gushizone.distributed.ds1.service.ItemAService;
import tk.gushizone.distributed.ds2.domain.ItemB;
import tk.gushizone.distributed.ds2.service.ItemBService;
import tk.gushizone.distributed.service.XaService;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AtomikosApplicationTest {

    @Autowired
    private ItemAService itemAService;
    @Autowired
    private ItemBService itemBService;
    @Autowired
    private XaService xaService;

    @Test
    @Transactional
    public void check() {

        List<ItemA> listA = itemAService.list();
        log.info("listA: {}", listA);

        List<ItemB> listB = itemBService.list();
        log.info("listB: {}", listB);
    }

    @Test
    public void save() {

        xaService.save();
    }



}