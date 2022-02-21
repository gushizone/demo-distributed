package tk.gushizone.keycloak.spi.authentication.face;

import com.google.common.collect.Lists;
import org.keycloak.Config;
import org.keycloak.OAuth2Constants;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.authentication.DisplayTypeAuthenticatorFactory;
import org.keycloak.authentication.authenticators.console.ConsoleUsernamePasswordAuthenticator;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.provider.ProviderConfigProperty;
import tk.gushizone.keycloak.spi.authentication.face.constants.IamFormConstants;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gushizone@gmail.com
 * @date 2022/2/18 5:04 下午
 */
public class IamFromFactory implements AuthenticatorFactory, DisplayTypeAuthenticatorFactory {

    public static final String PROVIDER_ID = "auth-iam-form";

    public static final AuthenticationExecutionModel.Requirement[] REQUIREMENT_CHOICES = {
            AuthenticationExecutionModel.Requirement.REQUIRED
    };

    private static final List<ProviderConfigProperty> CONFIG_PROPERTIES = new ArrayList<>();

    public static final IamForm SINGLETON = new IamForm();

    @Override
    public Authenticator create(KeycloakSession session) {
        return SINGLETON;
    }

    @Override
    public Authenticator createDisplay(KeycloakSession session, String displayType) {
        if (displayType == null) {
            return SINGLETON;
        }
        if (!OAuth2Constants.DISPLAY_CONSOLE.equalsIgnoreCase(displayType)) {
            return null;
        }
        // todo
        return ConsoleUsernamePasswordAuthenticator.SINGLETON;
    }

    @Override
    public void init(Config.Scope config) {

    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {

    }

    @Override
    public void close() {

    }

    /**
     * 唯一标识
     */
    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public String getReferenceCategory() {
        return PasswordCredentialModel.TYPE;
    }

    /**
     * 可配置的
     */
    @Override
    public boolean isConfigurable() {
        return true;
    }

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        return REQUIREMENT_CHOICES;
    }

    /**
     * 下拉框的显示
     */
    @Override
    public String getDisplayType() {
        return "IAM Form";
    }

    @Override
    public String getHelpText() {
        return "Validates multiple options from login form.";
    }

    static {
        ProviderConfigProperty property;

        property = new ProviderConfigProperty();
        property.setName(IamFormConstants.CONF_FACE_ENABLED);
        property.setLabel("Face Enabled");
        property.setType(ProviderConfigProperty.BOOLEAN_TYPE);
        property.setHelpText("Whether face login is enabled?");
        CONFIG_PROPERTIES.add(property);

        property = new ProviderConfigProperty();
        property.setName(IamFormConstants.CONF_FACE_PROVIDER);
        property.setLabel("Face Provider");
        property.setType(ProviderConfigProperty.LIST_TYPE);
        property.setOptions(Lists.newArrayList(IamFormConstants.FACE_PROVIDER_HUAWEI));
        property.setDefaultValue(IamFormConstants.FACE_PROVIDER_HUAWEI);
        property.setHelpText("Face provider: huawei and others.");
        CONFIG_PROPERTIES.add(property);

        property = new ProviderConfigProperty();
        property.setName(IamFormConstants.CONF_FACE_HUAWEI_REGION_ID);
        property.setLabel("Huawei Region Id");
        property.setType(ProviderConfigProperty.STRING_TYPE);
        property.setHelpText("Huawei Region Id.");
        CONFIG_PROPERTIES.add(property);

        property = new ProviderConfigProperty();
        property.setName(IamFormConstants.CONF_FACE_HUAWEI_ACCESS_KEY);
        property.setLabel("Huawei Access Key");
        property.setType(ProviderConfigProperty.STRING_TYPE);
        property.setHelpText("Huawei Access Key.");
        CONFIG_PROPERTIES.add(property);

        property = new ProviderConfigProperty();
        property.setName(IamFormConstants.CONF_FACE_HUAWEI_SECRET_ACCESS_KEY);
        property.setLabel("Huawei Secret Access Key");
        property.setType(ProviderConfigProperty.PASSWORD);
        property.setHelpText("Face data repository.");
        CONFIG_PROPERTIES.add(property);

        property = new ProviderConfigProperty();
        property.setName(IamFormConstants.CONF_FACE_HUAWEI_FACE_SET);
        property.setLabel("Huawei Face Set");
        property.setType(ProviderConfigProperty.STRING_TYPE);
        property.setHelpText("Face data repository.");
        CONFIG_PROPERTIES.add(property);
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return CONFIG_PROPERTIES;
    }

    /**
     * todo 有什么用？
     */
    @Override
    public boolean isUserSetupAllowed() {
        return true;
    }

}

