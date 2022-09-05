package tk.gushizone.controller;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.gushizone.common.oidc.api.OidcApi;
import tk.gushizone.common.oidc.config.OpenIdProperties;
import tk.gushizone.common.oidc.dto.AccessTokenInfo;
import tk.gushizone.common.util.RequestUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/17 2:11 下午
 */
@Slf4j
@RestController
@RequestMapping("/oidc")
public class OidcController {

    @Autowired
    private OidcApi oidcApi;

    @Autowired
    private OpenIdProperties openIdProperties;


    @GetMapping("/login")
    public String login() {
        return oidcApi.loginUrl();
    }

    @GetMapping("/codeToToken")
    public String codeToToken(HttpServletRequest request) {
        JSONObject result = RequestUtil.readRequest(request);
        log.warn("auth rec is {}", result);

        AccessTokenInfo accessTokenInfo = oidcApi.accessToken(request.getParameter("code"));
        result.set("accessTokenInfo", accessTokenInfo);
        return result.toString();
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {

        JSONObject result = RequestUtil.readRequest(request);
        log.warn("logout rec is {}", result);

//        oidcApi.logout();

        return result.toString();
    }

    @GetMapping("/home")
    public String home(HttpServletRequest request) {

        JSONObject result = RequestUtil.readRequest(request);
        log.warn("home rec is {}", result);
        return result.toString();
    }


    @PostMapping("/rec")
    public String rec(HttpServletRequest request) {

        JSONObject result = RequestUtil.readRequest(request);
        log.warn("post rec is {}", result);

        return result.toString();
    }

}
