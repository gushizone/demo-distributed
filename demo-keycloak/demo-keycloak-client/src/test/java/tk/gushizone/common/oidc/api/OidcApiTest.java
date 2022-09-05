package tk.gushizone.common.oidc.api;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.gushizone.ClientApplication;
import tk.gushizone.common.oidc.config.OpenIdProperties;
import tk.gushizone.common.oidc.dto.AccessTokenInfo;
import tk.gushizone.common.oidc.dto.OpenidConfiguration;
import tk.gushizone.common.oidc.dto.Userinfo;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
public class OidcApiTest {

    @Autowired
    private OidcApi oidcApi;
    @Autowired
    private OpenIdProperties openIdProperties;


    @Test
    public void openidConfiguration() {

        OpenidConfiguration openidConfiguration = oidcApi.openidConfiguration(openIdProperties.getOauth2OidcDiscoveryEndpoint());
        log.info(JSONUtil.toJsonPrettyStr(openidConfiguration));
    }

    @Test
    public void loginUrl() {
        String loginUrl = oidcApi.loginUrl();
        log.info(loginUrl);
    }

    /**
         {
             "id_token": "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJna3BwOHVYTnZUbmlwc1ZyZHZQUGtxVm5BY2VZNTFCT0JtVXc4UXM1b1VZIn0.eyJleHAiOjE2NTAyMTYyODIsImlhdCI6MTY1MDIxNTk4MiwiYXV0aF90aW1lIjoxNjUwMjE1OTU2LCJqdGkiOiI2MTM2ZjZkMi0yOTU0LTQ4NjEtYWEwMi1iMjY2N2JmZTAwYmQiLCJpc3MiOiJodHRwOi8vMTI3LjAuMC4xOjE4MDgwL2F1dGgvcmVhbG1zL2RlbW8iLCJhdWQiOiJkZW1vLWNsaWVudDEiLCJzdWIiOiIwMzdiMTJiYi1kODUzLTQ3NTUtYTBmZi04NDFiYjVjZDM5MmYiLCJ0eXAiOiJJRCIsImF6cCI6ImRlbW8tY2xpZW50MSIsInNlc3Npb25fc3RhdGUiOiJiY2VlZjA5OC00NGYzLTQxZjgtOGJmNS1mZTUxOTA3N2QwYjUiLCJhdF9oYXNoIjoicVJqcEVfNkhxVFFsdXRHY2VtakFnUSIsImFjciI6IjEiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6ImRlbW8ifQ.b8YA2Zudb-tghWHS3ocakPC8ByXsDuig6AwtFqADATOMj1YRPhuqPyz6bXOsQcwLR5uDvtF_6vZtNaazmwcvMS5bb9KPeK0YajIT7IXrLZ7h_t0GKDan_eq7JsY8P6-reB7ZgrSIAEnYhl5cBwuKy1kxWce8kHKmd-DnwjWcorQf4lPjpBee4LiszTZWnuQSIhyy_mXomFQlmOelH_b43JVNkaUG2cUYzSDlkDRdhjAIMLNsoUPOUAIECiFjpapOPaI9EH_Uh_LJWj11zPdSTxAmeYhdDJa3-yW_d5qR14XSkk81js8LAxBRJ5cci7vdIOwleBYJjvWzQhtkVNXl9Q",
                 "token_type": "Bearer",
                 "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJna3BwOHVYTnZUbmlwc1ZyZHZQUGtxVm5BY2VZNTFCT0JtVXc4UXM1b1VZIn0.eyJleHAiOjE2NTAyMTYyODIsImlhdCI6MTY1MDIxNTk4MiwiYXV0aF90aW1lIjoxNjUwMjE1OTU2LCJqdGkiOiJjNDE2OGE5NC0zNTU3LTRlMTgtYmU5MS1lZWFiY2FkYmFhZDQiLCJpc3MiOiJodHRwOi8vMTI3LjAuMC4xOjE4MDgwL2F1dGgvcmVhbG1zL2RlbW8iLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiMDM3YjEyYmItZDg1My00NzU1LWEwZmYtODQxYmI1Y2QzOTJmIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiZGVtby1jbGllbnQxIiwic2Vzc2lvbl9zdGF0ZSI6ImJjZWVmMDk4LTQ0ZjMtNDFmOC04YmY1LWZlNTE5MDc3ZDBiNSIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6ImRlbW8ifQ.XYA3tFzBRwiHQE29YBRAp4VQ9AT_BCIe4lQt1jzJJsS4r8oj_POvW2fttjAVQBTZgTUPZe9-FAQyH65flMmAGSwUOGnu3XFY8yL8Oiu38GeS6zDrSx1dpVtmXP8hxapDAxj-BngR-ZxF-aga63SZUmPMBMQ00Kpm0NOybBwO2ft2VhehbT1zcGZ5FyK9yU3AeNjLfK-jIQX3Pkp0aYKH1aGKZtBMZKzF_0_jE-c20rWNRRu3UsnH-hKA93gCl2vewyuNWT-JXSFFffJPM-zoV0W3fuBK9kAhMxnC6qBPlPrI0pt-NKPHWcZA3jAKq_irC-F8VaLITjIfUXOyxNNOmw",
                 "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI3ZjY2OWZhZi1iNDc4LTQ1ODMtOGNlZC02ZTQzZjQwN2E4MzQifQ.eyJleHAiOjE2NTAyMTc3ODIsImlhdCI6MTY1MDIxNTk4MiwianRpIjoiMTA0YWFmYWUtM2QyYi00MDNiLWEwNDItYzZlZGVmYTU5ZGE5IiwiaXNzIjoiaHR0cDovLzEyNy4wLjAuMToxODA4MC9hdXRoL3JlYWxtcy9kZW1vIiwiYXVkIjoiaHR0cDovLzEyNy4wLjAuMToxODA4MC9hdXRoL3JlYWxtcy9kZW1vIiwic3ViIjoiMDM3YjEyYmItZDg1My00NzU1LWEwZmYtODQxYmI1Y2QzOTJmIiwidHlwIjoiUmVmcmVzaCIsImF6cCI6ImRlbW8tY2xpZW50MSIsInNlc3Npb25fc3RhdGUiOiJiY2VlZjA5OC00NGYzLTQxZjgtOGJmNS1mZTUxOTA3N2QwYjUiLCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIn0.SY5lLZMC1MyxrMRaJabfj6eRgAdrZFcg4BIKbRb4MYQ",
                 "refresh_expires_in": 1800,
                 "not-before-policy": 0,
                 "scope": "openid profile email",
                 "session_state": "bceef098-44f3-41f8-8bf5-fe519077d0b5",
                 "expires_in": 300
         }
     */
    @Test
    public void accessToken() {

        String code = "181c426a-6139-4b8e-938c-be97e008cd1a.8cb6f9a0-d782-4613-8631-047eb8f405e3.827684cb-d416-46fb-b858-8b9c79224661";
        AccessTokenInfo accessTokenInfo = oidcApi.accessToken(code);
        log.info(JSONUtil.toJsonPrettyStr(accessTokenInfo));
    }


    /**
     * {
     *     "ssoId": "demo",
     *     "sub": "037b12bb-d853-4755-a0ff-841bb5cd392f",
     *     "email_verified": false,
     *     "preferred_username": "demo"
     * }
     */
    @Test
    public void userinfo() {

        String accessToken = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJna3BwOHVYTnZUbmlwc1ZyZHZQUGtxVm5BY2VZNTFCT0JtVXc4UXM1b1VZIn0.eyJleHAiOjE2NTAyNTAyNDUsImlhdCI6MTY1MDI0OTk0NSwiYXV0aF90aW1lIjoxNjUwMjQ5OTQ1LCJqdGkiOiJjY2NjNWEwOS0yOGE1LTQ3NjctYTE0Mi1hMzM4NzU0NmI0Y2EiLCJpc3MiOiJodHRwOi8vMTI3LjAuMC4xOjE4MDgwL2F1dGgvcmVhbG1zL2RlbW8iLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiMDM3YjEyYmItZDg1My00NzU1LWEwZmYtODQxYmI1Y2QzOTJmIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiZGVtby1jbGllbnQxIiwic2Vzc2lvbl9zdGF0ZSI6ImU0YmVjM2JhLWQwN2UtNDg4Ni04ZjA1LTRiOGJjZGZhZTBiZSIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6ImRlbW8ifQ.ZLow_Dj1LM4RrOxCZCnwHR30FKKcZtqa1bTwKdTfIXEprdn48vdg0ZvtESQL8G_2-BPKcAFEhXzCztSpAKFM8aY1Qm9RssSCu0G7SjJJ6NllQGRHsjuR-yF-OAjSEU_9Mo9KgY6B1coge1fkifSTO42w8ZT-hz0f1lZ6TwGL4HPvEsHV3DSQBbOXK117z9TbPH4_qKEKwsX9s1_2ZWz7oB3iyMxvvP6WXgU5DnhNk3tj6B2RzP8C8iejuVo3C4_3Y4khhevPkXpd7YLqNcCj0idUgEUX9xH2cAR90WIyG0ixgdxI8v6CTbUYIOjkS23KyKTSsfUIsFiTo-5XlYep5Q";
        Userinfo userinfo = oidcApi.userinfo(accessToken);
        log.info(JSONUtil.toJsonPrettyStr(userinfo));
    }



    @Test
    public void logoutUrl() {

        String idToken = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJna3BwOHVYTnZUbmlwc1ZyZHZQUGtxVm5BY2VZNTFCT0JtVXc4UXM1b1VZIn0.eyJleHAiOjE2NTAyNTEyNDcsImlhdCI6MTY1MDI1MDk0NywiYXV0aF90aW1lIjoxNjUwMjUwOTQ3LCJqdGkiOiJjYzEwMjQ5Ny0xZDcyLTQxZjgtYTdiNy0yYmQ4YmQyNzkyNzYiLCJpc3MiOiJodHRwOi8vMTI3LjAuMC4xOjE4MDgwL2F1dGgvcmVhbG1zL2RlbW8iLCJhdWQiOiJkZW1vLWNsaWVudDEiLCJzdWIiOiIwMzdiMTJiYi1kODUzLTQ3NTUtYTBmZi04NDFiYjVjZDM5MmYiLCJ0eXAiOiJJRCIsImF6cCI6ImRlbW8tY2xpZW50MSIsInNlc3Npb25fc3RhdGUiOiJmNGI0ZDg3OS05YzAzLTQzNGEtOTlkYS04ZmUwYzBjN2MzZTMiLCJhdF9oYXNoIjoic2JLSV9pcTJDVnFxb0VfR05TWFM2dyIsImFjciI6IjEiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6ImRlbW8iLCJzc28iOlsiMDM3YjEyYmItZDg1My00NzU1LWEwZmYtODQxYmI1Y2QzOTJmIl19.SjpwmpxE_hM1DLB56basVMATJ-sDDBSrM6Keijttis-wI5GJpv_MULCpAfSnOTdDCKicHA7_-J-XmmR4CWAjM-l6VxYsK7lH8GO2jmv0wpI9goXhdBSWAENULF4smZKxN1aieeow-1pn_XC6sN_IyrCxyGNyyXlMAwSCTGDmfleS72Qyu58OmS3-mgVe3T7xYE27s7aPg6Cc0g2A8bUNh0IPjbcijKkqGB_AUuekagGO0zQAfOVwdtwseWKKDSIOs5dcpiz1gZsxERIYa7lZOgFjU7S5d_vu4ABF4Bx_GJOr19UGOH2yXgfMfZc1gmNRpggKY9STLRKeEpSAJejj-w";
        String logoutUrl = oidcApi.logoutUrl(idToken);
        log.info(logoutUrl);
    }

    @Test
    public void logout() {

        String refreshToken = "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI3ZjY2OWZhZi1iNDc4LTQ1ODMtOGNlZC02ZTQzZjQwN2E4MzQifQ.eyJleHAiOjE2NTAyMTkyNzksImlhdCI6MTY1MDIxNzQ3OSwianRpIjoiNjE5N2ZiNmEtNTU5MC00ZjBkLTliZGItZGNhNGE1NjUyNzBjIiwiaXNzIjoiaHR0cDovLzEyNy4wLjAuMToxODA4MC9hdXRoL3JlYWxtcy9kZW1vIiwiYXVkIjoiaHR0cDovLzEyNy4wLjAuMToxODA4MC9hdXRoL3JlYWxtcy9kZW1vIiwic3ViIjoiMDM3YjEyYmItZDg1My00NzU1LWEwZmYtODQxYmI1Y2QzOTJmIiwidHlwIjoiUmVmcmVzaCIsImF6cCI6ImRlbW8tY2xpZW50MSIsInNlc3Npb25fc3RhdGUiOiI2ZjU3MzcxMi05MzViLTRmODQtYWE2MS02ZjhlZGUyYTY1OTEiLCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIn0.hD2LBotsfOJ3kjEgVsNaxUTruH5u1bZ3qZmuRitksjE";
        Integer status = oidcApi.logout(refreshToken);
        log.info(String.valueOf(status));
    }
}