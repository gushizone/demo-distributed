package tk.gushizone.common.oidc.dto;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

import java.util.List;

/**
 * @author gushizone@gmail.com
 * @date 2021/8/30 3:24 下午
 */
@Data
public class OpenidConfiguration {

    @Alias("request_parameter_supported")
    private Boolean requestParameterSupported;
    @Alias("introspection_endpoint")
    private String introspectionEndpoint;
    @Alias("claims_parameter_supported")
    private Boolean claimsParameterSupported;
    @Alias("check_session_iframe")
    private String checkSessionIframe;
    @Alias("scopes_supported")
    private List<String> scopesSupported;
    @Alias("backchannel_logout_supported")
    private Boolean backchannelLogoutSupported;
    @Alias("issuer")
    private String issuer;
    @Alias("id_token_encryption_enc_values_supported")
    private List<String> idTokenEncryptionEncValuesSupported;
    @Alias("authorization_endpoint")
    private String authorizationEndpoint;
    @Alias("userinfo_signing_alg_values_supported")
    private List<String> userinfoSigningAlgValuesSupported;
    @Alias("claims_supported")
    private List<String> claimsSupported;
    @Alias("claim_types_supported")
    private List<String> claimTypesSupported;
    @Alias("token_endpoint_auth_methods_supported")
    private List<String> tokenEndpointAuthMethodsSupported;
    @Alias("tls_client_certificate_bound_access_tokens")
    private Boolean tlsClientCertificateBoundAccessTokens;
    @Alias("response_modes_supported")
    private List<String> responseModesSupported;
    @Alias("backchannel_logout_session_supported")
    private Boolean backchannelLogoutSessionSupported;
    @Alias("token_endpoint")
    private String tokenEndpoint;
    @Alias("response_types_supported")
    private List<String> responseTypesSupported;
    @Alias("revocation_endpoint_auth_signing_alg_values_supported")
    private List<String> revocationEndpointAuthSigningAlgValuesSupported;
    @Alias("revocation_endpoint_auth_methods_supported")
    private List<String> revocationEndpointAuthMethodsSupported;
    @Alias("request_uri_parameter_supported")
    private Boolean requestUriParameterSupported;
    @Alias("grant_types_supported")
    private List<String> grantTypesSupported;
    @Alias("end_session_endpoint")
    private String endSessionEndpoint;
    @Alias("revocation_endpoint")
    private String revocationEndpoint;
    @Alias("userinfo_endpoint")
    private String userinfoEndpoint;
    @Alias("token_endpoint_auth_signing_alg_values_supported")
    private List<String> tokenEndpointAuthSigningAlgValuesSupported;
    @Alias("require_request_uri_registration")
    private Boolean requireRequestUriRegistration;
    @Alias("code_challenge_methods_supported")
    private List<String> codeChallengeMethodsSupported;
    @Alias("id_token_encryption_alg_values_supported")
    private List<String> idTokenEncryptionAlgValuesSupported;
    @Alias("jwks_uri")
    private String jwksUri;
    @Alias("subject_types_supported")
    private List<String> subjectTypesSupported;
    @Alias("id_token_signing_alg_values_supported")
    private List<String> idTokenSigningAlgValuesSupported;
    @Alias("registration_endpoint")
    private String registrationEndpoint;
    @Alias("request_object_signing_alg_values_supported")
    private List<String> requestObjectSigningAlgValuesSupported;
}
