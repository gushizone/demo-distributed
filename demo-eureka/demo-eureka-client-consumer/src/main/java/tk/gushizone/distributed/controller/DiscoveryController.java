package tk.gushizone.distributed.controller;

import cn.hutool.json.JSONObject;
import com.google.common.collect.Lists;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/25 10:41 上午
 */
@RestController
@RequestMapping("/discovery")
public class DiscoveryController {

    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 查看发现的所有服务
     */
    @GetMapping("/service/list")
    public List<String> list() {

        return discoveryClient.getServices();
    }

    /**
     * 获取服务详情
     */
    @GetMapping("/service/detail")
    public String detail(@RequestParam(value = "name") String name) {
        List<JSONObject> results = Lists.newArrayListWithExpectedSize(discoveryClient.getInstances(name).size());

        for (ServiceInstance instance : discoveryClient.getInstances(name)) {
            JSONObject json = new JSONObject();
            json.set("instanceId", instance.getInstanceId());
            json.set("host", instance.getHost());
            json.set("port", instance.getPort());
            results.add(json);
        }
        return results.toString();
    }

}
