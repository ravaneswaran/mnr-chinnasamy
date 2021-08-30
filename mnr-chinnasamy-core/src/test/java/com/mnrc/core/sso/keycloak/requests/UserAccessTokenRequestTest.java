package com.mnrc.core.sso.keycloak.requests;

import com.mnrc.core.enums.HttpRequestMethods;
import com.mnrc.core.sso.keycloak.httpclient.KeyCloakHttpClient;
import okhttp3.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAccessTokenRequestTest {

    @Value("${sso.server.url.prefix}")
    private String keyCloakUrlPrefix;

    @Test
    public void testUserAccessToken() throws IOException {
        String url = String .format("%s/%s", this.keyCloakUrlPrefix, "realms/mnr-chinnasamy/protocol/openid-connect/token");
        UserAccessTokenRequest userAccessTokenRequest = new UserAccessTokenRequest(url, HttpRequestMethods.POST.name(), "ravaneswaran", "welcome");
        Response response = KeyCloakHttpClient.executeRequest(userAccessTokenRequest);

        Assert.assertNotNull(response);
    }
}
