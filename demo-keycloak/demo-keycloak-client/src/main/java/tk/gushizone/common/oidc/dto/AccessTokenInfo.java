package tk.gushizone.common.oidc.dto;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

/**
 * @author gushizone@gmail.com
 * @date 2021/8/30 2:48 下午
 */
@Data
public class AccessTokenInfo {

    @Alias("id_token")
    private String idToken;
    @Alias("token_type")
    private String tokenType;
    @Alias("access_token")
    private String accessToken;
    @Alias("refresh_token")
    private String refreshToken;
    @Alias("refresh_expires_in")
    private Integer refreshExpiresIn;
    @Alias("not-before-policy")
    private Integer notBeforePolicy;
    @Alias("scope")
    private String scope;
    @Alias("session_state")
    private String sessionState;
    @Alias("expires_in")
    private Integer expiresIn;


    /*===========   非OIDC相关   =============*/
    /**
     * msp-admin 在线用户ID
     */
    private String activeUserId;
}
