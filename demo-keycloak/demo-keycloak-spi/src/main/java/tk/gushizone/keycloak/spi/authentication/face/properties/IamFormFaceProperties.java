package tk.gushizone.keycloak.spi.authentication.face.properties;

import cn.hutool.core.map.MapUtil;
import org.keycloak.models.AuthenticatorConfigModel;
import tk.gushizone.keycloak.spi.authentication.face.constants.IamFormConstants;

import java.util.Map;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/21 2:21 下午
 */
public class IamFormFaceProperties {

    private Boolean enabled = false;

    private String provider;

    private IamFormHuaweiFaceProperties huaweiConfig = new IamFormHuaweiFaceProperties();

    public static IamFormFaceProperties init(AuthenticatorConfigModel authenticatorConfig) {

        IamFormFaceProperties result = new IamFormFaceProperties();
        if (authenticatorConfig == null) {
            return result;
        }
        Map<String, String> config = authenticatorConfig.getConfig();

        result.setEnabled(MapUtil.getBool(config, IamFormConstants.CONF_FACE_ENABLED));
        result.setProvider(MapUtil.getStr(config, IamFormConstants.CONF_FACE_PROVIDER));

        IamFormHuaweiFaceProperties huaweiConfig = result.getHuaweiConfig();
        huaweiConfig.setRegionId(MapUtil.getStr(config, IamFormConstants.CONF_FACE_HUAWEI_REGION_ID));
        huaweiConfig.setAccessKey(MapUtil.getStr(config, IamFormConstants.CONF_FACE_HUAWEI_ACCESS_KEY));
        huaweiConfig.setSecretAccessKey(MapUtil.getStr(config, IamFormConstants.CONF_FACE_HUAWEI_SECRET_ACCESS_KEY));
        huaweiConfig.setFaceSet(MapUtil.getStr(config, IamFormConstants.CONF_FACE_HUAWEI_FACE_SET));
        result.setHuaweiConfig(huaweiConfig);

        return result;
    }


    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public IamFormHuaweiFaceProperties getHuaweiConfig() {
        return huaweiConfig;
    }

    public void setHuaweiConfig(IamFormHuaweiFaceProperties huaweiConfig) {
        this.huaweiConfig = huaweiConfig;
    }
}
