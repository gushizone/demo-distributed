package tk.gushizone.common.oidc.dto;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

/**
 * @author gushizone@gmail.com
 * @date 2021/8/30 3:17 下午
 */
@Data
public class Userinfo {

    private String sub;
    @Alias("email_verified")
    private Boolean emailVerified;
    @Alias("preferred_username")
    private String preferredUsername;


    /* ========= 以下字段需要自行配置 ========= */
    /**
     * sso user 映射 msp-admin user
     */
    private String ssoId;
}
