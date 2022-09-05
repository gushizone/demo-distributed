package tk.gushizone.keycloak.spi.authentication.face;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Maps;
import org.jboss.resteasy.specimpl.MultivaluedMapImpl;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.AuthenticationFlowError;
import org.keycloak.authentication.authenticators.browser.UsernamePasswordForm;
import org.keycloak.events.Details;
import org.keycloak.events.Errors;
import org.keycloak.models.ModelDuplicateException;
import org.keycloak.models.UserModel;
import org.keycloak.services.ServicesLogger;
import org.keycloak.services.managers.AuthenticationManager;
import org.keycloak.services.messages.Messages;
import tk.gushizone.keycloak.spi.authentication.face.properties.IamFormFaceProperties;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.HashMap;

/**
 * @author gushizone@gmail.com
 * @date 2022/2/18 5:01 下午
 */
public class IamForm extends UsernamePasswordForm {

    protected static ServicesLogger log = ServicesLogger.LOGGER;

//    public static final String LOGIN_TYPE = "login_type";
//    public static final String LOGIN_TYPE_FACE = "face";
//    public static final String FACE_ID = "face_id";

    /**
     * todo 快速测试
     */
    public static final String LOGIN_TYPE = AuthenticationManager.FORM_USERNAME;
    public static final String LOGIN_TYPE_FACE = "face@qq.com";
    public static final String FACE_ID = AuthenticationManager.FORM_USERNAME;

    /**
     * 进入登录页面前，调用此方法
     */
    @Override
    public void authenticate(AuthenticationFlowContext context) {
        log.warn("================ authenticate");



        super.authenticate(context);
    }

    /**
     * 点击登录，执行此方法校验
     */
    @Override
    public void action(AuthenticationFlowContext context) {
        MultivaluedMap<String, String> formData = context.getHttpRequest().getDecodedFormParameters();
        if (formData.containsKey("cancel")) {
            context.cancelLogin();
            return;
        }
        log.info("formData: " + JSONUtil.toJsonStr(formData));
        if (!validateForm(context, formData)) {
            return;
        }
        context.success();
    }

    @Override
    protected boolean validateForm(AuthenticationFlowContext context, MultivaluedMap<String, String> formData) {
        if (!formData.containsKey(LOGIN_TYPE)) {
            return super.validateUserAndPassword(context, formData);
        }
        String loginType = formData.getFirst(LOGIN_TYPE);
        if (LOGIN_TYPE_FACE.equals(loginType)) {
            return validateFace(context, formData);
        }
        return super.validateUserAndPassword(context, formData);
    }


    private boolean validateFace(AuthenticationFlowContext context, MultivaluedMap<String, String> inputData) {
        context.clearUser();
        UserModel user = getUser(context, inputData);
        if (user != null) {
            JSONObject userJson = new JSONObject()
                    .set("id", user.getId())
                    .set("username", user.getUsername())
                    .set("email", user.getEmail());
            log.info("user: " + userJson);
        }
        return user != null && validateUser(context, user, inputData);
    }

    private String getFaceId(MultivaluedMap<String, String> inputData) {

//        String faceImage = inputData.getFirst("face_image");

        // todo
        String faceId = inputData.getFirst(FACE_ID);
        return faceId;
    }


    /**
     * todo
     */
    private UserModel getUser(AuthenticationFlowContext context, MultivaluedMap<String, String> inputData) {

        String faceId = getFaceId(inputData);

        IamFormFaceProperties iamFormFaceProperties = IamFormFaceProperties.init(context.getAuthenticatorConfig());
        log.info("iam form config: " + JSONUtil.toJsonStr(iamFormFaceProperties));

//        String username = inputData.getFirst(AuthenticationManager.FORM_USERNAME);
//        if (username == null) {
//            context.getEvent().error(Errors.USER_NOT_FOUND);
//            Response challengeResponse = challenge(context, getDefaultChallengeMessage(context), FIELD_USERNAME);
//            context.failureChallenge(AuthenticationFlowError.INVALID_USER, challengeResponse);
//            return null;
//        }
//
//        // remove leading and trailing whitespace
//        username = username.trim();
//
//        context.getEvent().detail(Details.USERNAME, username);
//        context.getAuthenticationSession().setAuthNote(AbstractUsernameFormAuthenticator.ATTEMPTED_USERNAME, username);
//
        UserModel user = null;
        try {
//            user = KeycloakModelUtils.findUserByNameOrEmail(context.getSession(), context.getRealm(), username);
            // todo 暂时将 faceId 放在 email 上，后期应该加个字段，用 admin rest api 查询
            user = context.getSession().users().getUserByEmail(faceId, context.getRealm());
        } catch (ModelDuplicateException mde) {
            ServicesLogger.LOGGER.modelDuplicateException(mde);

            // Could happen during federation import
            if (mde.getDuplicateFieldName() != null && mde.getDuplicateFieldName().equals(UserModel.EMAIL)) {
                setDuplicateUserChallenge(context, Errors.EMAIL_IN_USE, Messages.EMAIL_EXISTS, AuthenticationFlowError.INVALID_USER);
            } else {
                setDuplicateUserChallenge(context, Errors.USERNAME_IN_USE, Messages.USERNAME_EXISTS, AuthenticationFlowError.INVALID_USER);
            }
            return user;
        }

        testInvalidUser(context, user);
        return user;
    }

    private boolean validateUser(AuthenticationFlowContext context, UserModel user, MultivaluedMap<String, String> inputData) {
        if (!enabledUser(context, user)) {
            return false;
        }
        String rememberMe = inputData.getFirst("rememberMe");
        boolean remember = rememberMe != null && rememberMe.equalsIgnoreCase("on");
        if (remember) {
            context.getAuthenticationSession().setAuthNote(Details.REMEMBER_ME, "true");
            context.getEvent().detail(Details.REMEMBER_ME, "true");
        } else {
            context.getAuthenticationSession().removeAuthNote(Details.REMEMBER_ME);
        }
        context.setUser(user);
        return true;
    }


}
