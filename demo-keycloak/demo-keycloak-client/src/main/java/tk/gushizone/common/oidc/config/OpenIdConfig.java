package tk.gushizone.common.oidc.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import tk.gushizone.common.oidc.api.OidcApi;
import tk.gushizone.common.oidc.dto.OpenidConfiguration;

/**
 * @author gushizone@gmail.com
 * @date 2022/4/18 00:35
 */
@Slf4j
@Configuration
@ConditionalOnProperty(name = "oidc.enabled", havingValue = "true")
public class OpenIdConfig implements InitializingBean {

    @Autowired
    private OpenIdProperties openIdProperties;
    @Autowired
    private OidcApi oidcApi;

    /**
     * 根据 oidcDiscoveryEndpoint 获取 endpoint
     */
    @Override
    public void afterPropertiesSet() throws Exception {

        OpenidConfiguration config = oidcApi.openidConfiguration(openIdProperties.getOauth2OidcDiscoveryEndpoint());

        openIdProperties.setAuthorizationEndpoint(config.getAuthorizationEndpoint());
        openIdProperties.setTokenEndpoint(config.getTokenEndpoint());
        openIdProperties.setEndSessionEndpoint(config.getEndSessionEndpoint());
        openIdProperties.setJwksUri(config.getJwksUri());
        openIdProperties.setUserinfoEndpoint(config.getUserinfoEndpoint());
        openIdProperties.setIssuer(config.getIssuer());

        log.info("openid properties init done : {}", openIdProperties);
    }

}
