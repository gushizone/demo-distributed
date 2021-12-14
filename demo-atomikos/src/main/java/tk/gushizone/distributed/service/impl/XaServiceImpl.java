package tk.gushizone.distributed.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.gushizone.distributed.ds1.domain.ItemA;
import tk.gushizone.distributed.ds1.service.ItemAService;
import tk.gushizone.distributed.ds2.domain.ItemB;
import tk.gushizone.distributed.ds2.service.ItemBService;
import tk.gushizone.distributed.service.XaService;

import javax.annotation.Resource;

/**
 * @author gushizone@gmail.com
 * @date 2021/12/7 5:55 下午
 */
@Service
public class XaServiceImpl implements XaService {

    @Resource
    private ItemAService itemAService;
    @Resource
    private ItemBService itemBService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save() {

        itemAService.save(new ItemA(null, "foo..."));

//        int i = 1 / 0;

        itemBService.save(new ItemB(null, "bar..."));

        int i = 1 / 0;
    }
}
