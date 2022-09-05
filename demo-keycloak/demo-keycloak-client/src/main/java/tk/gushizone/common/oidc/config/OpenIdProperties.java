package tk.gushizone.common.oidc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix = "oidc")
public class OpenIdProperties {

    private Boolean enabled = false;

    /**
     * SSO 中的用户标识
     */
    private String ssoId = "preferred_username";
    /**
     * 是否互踢
     */
    private Boolean loginKick = false;


    /* ===========  OIDC  ========== */

    private String oauth2OidcDiscoveryEndpoint;

    private String clientId;

    private String clientSecret;

    private String scope;

    private String redirectUrl;

    private String logoutRedirectUrl;



    /* ========= 根据 oauth2OidcDiscoveryEndpoint 更新以下属性 ============ */

    private String authorizationEndpoint;

    private String tokenEndpoint;

    private String endSessionEndpoint;

    private String jwksUri;

    private String userinfoEndpoint;

    private String issuer;
}
