package tk.gushizone.common.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONObject;
import com.google.common.collect.Maps;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author gushizone@gmail.com
 * @date 2022/4/18 00:44
 */
public class RequestUtil {

    public static JSONObject readRequest(HttpServletRequest request) {
        JSONObject result = new JSONObject();

        Map<String, String> headerMap = ServletUtil.getHeaderMap(request);
        result.put("header", headerMap);

        Map<String, Cookie> cookieMap = ServletUtil.readCookieMap(request);
        if (MapUtil.isNotEmpty(cookieMap)) {
            Map<String, Object> cookieStrMap = Maps.newHashMap();
            cookieMap.forEach((s, cookie) -> cookieStrMap.put(s, BeanUtil.beanToMap(cookie)));
            result.put("cookie", cookieStrMap);
        }

        Map<String, String> paramMap = ServletUtil.getParamMap(request);
        result.put("param", paramMap);

        String body = ServletUtil.getBody(request);
        result.put("body", body);
        return result;
    }
}
