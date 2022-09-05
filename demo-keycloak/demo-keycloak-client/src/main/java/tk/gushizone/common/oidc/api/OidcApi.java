package tk.gushizone.common.oidc.api;

import tk.gushizone.common.oidc.dto.AccessTokenInfo;
import tk.gushizone.common.oidc.dto.OpenidConfiguration;
import tk.gushizone.common.oidc.dto.Userinfo;

/**
 * OpenID Connect API
 *
 * @author gushizone@gmail.com
 * @date 2021/8/26 2:07 下午
 */
public interface OidcApi {

    /**
     * 获取 oidc 配置信息
     */
    OpenidConfiguration openidConfiguration(String oauth2OidcDiscoveryEndpoint);

    /**
     * code 换 accessToken 信息
     */
    AccessTokenInfo accessToken(String code);

    /**
     * accessToken 换 用户信息
     */
    Userinfo userinfo(String accessToken);

    /**
     * 登录地址
     */
    String loginUrl();

    /**
     * 登出地址
     */
    String logoutUrl(String idToken);


    Integer logout(String refreshToken);
}
