package tk.gushizone.common.oidc.api.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import tk.gushizone.common.oidc.api.OidcApi;
import tk.gushizone.common.oidc.config.OpenIdProperties;
import tk.gushizone.common.oidc.dto.AccessTokenInfo;
import tk.gushizone.common.oidc.dto.OpenidConfiguration;
import tk.gushizone.common.oidc.dto.Userinfo;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * OpenID Connect API
 *
 * @author gushizone@gmail.com
 * @date 2021/8/26 2:08 下午
 */
@Slf4j
@Service
public class OidcApiImpl implements OidcApi {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OpenIdProperties openIdProperties;

    @Override
    public OpenidConfiguration openidConfiguration(String oauth2OidcDiscoveryEndpoint) {

        String str = restTemplate.getForObject(oauth2OidcDiscoveryEndpoint, String.class);
        return JSONUtil.toBean(str, OpenidConfiguration.class);
    }

    @Override
    public AccessTokenInfo accessToken(String code) {

        MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<>();
        queryMap.add("grant_type", "authorization_code");
        queryMap.add("code", code);
        queryMap.add("redirect_uri", openIdProperties.getRedirectUrl());

        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<>();
        headerMap.add("authorization", "Basic " + Base64.encode(openIdProperties.getClientId() + ":" + openIdProperties.getClientSecret()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.addAll(headerMap);

        HttpEntity<?> requestEntity = new HttpEntity<>(queryMap, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(openIdProperties.getTokenEndpoint(), HttpMethod.POST, requestEntity, String.class);

        return JSONUtil.toBean(responseEntity.getBody(), AccessTokenInfo.class);
    }

    @Override
    public Userinfo userinfo(String accessToken) {

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("authorization", "Bearer " + accessToken);

        // 通过UriComponentsBuilder创建URI对象，这样RestTemplate不会自动进行urlencode
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(openIdProperties.getUserinfoEndpoint());
        URI uri = uriComponentsBuilder.build(true).toUri();

        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

        Userinfo userinfo = JSONUtil.toBean(responseEntity.getBody(), Userinfo.class);
        userinfo.setSsoId(JSONUtil.parseObj(responseEntity.getBody()).getStr(openIdProperties.getSsoId()));
        return userinfo;
    }

    @Override
    public String loginUrl() {

        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("client_id", openIdProperties.getClientId());
        // paramMap.put("state", "");
        // paramMap.put("nonce", "BMWSSO");
        paramMap.put("scope", openIdProperties.getScope());
        paramMap.put("response_type", "code");
        paramMap.put("redirect_uri", openIdProperties.getRedirectUrl());

        String redirectUrl = openIdProperties.getAuthorizationEndpoint() +
                "?" + HttpUtil.toParams(paramMap);

        log.info("重定向的登录的地址：{}", redirectUrl);
        return redirectUrl;
    }

    @Override
    public String logoutUrl(String idToken) {

//        try {
//            log.info("检测下该idToken是否还能用");
//            JwtDecoder oidcDecoder = JwtDecoders.fromIssuerLocation(openIdProperties.getIssuer());
//            Jwt jwt = oidcDecoder.decode(idToken);
//            String subject = jwt.getSubject();
//            log.info("id token对应的 subject 为 {}", subject);
//        } catch (Exception e) {
//            log.warn("检测idToken失败", e);
//            log.info("重定向的登出地址：{}", openIdProperties.getLogoutRedirectUrl());
//            return openIdProperties.getLogoutRedirectUrl();
//        }

        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("id_token_hint", idToken);
        paramMap.put("post_logout_redirect_uri", openIdProperties.getLogoutRedirectUrl());

        String logoutUrl = openIdProperties.getEndSessionEndpoint() +
                "?" + HttpUtil.toParams(paramMap);
        log.info("重定向的登出地址：{}", logoutUrl);
        return logoutUrl;
    }

    @Override
    public Integer logout(String refreshToken) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap. put("refresh_token", refreshToken);
        paramMap. put("client_id",openIdProperties.getClientId());
        paramMap. put("client_secret",openIdProperties.getClientSecret());
        Integer statusCode = HttpRequest.post(openIdProperties.getEndSessionEndpoint())
                .contentType("application/x-www-form-urlencoded")
                .form(paramMap)
                .timeout(2000)
                .execute().getStatus();
        return statusCode;
    }
}
