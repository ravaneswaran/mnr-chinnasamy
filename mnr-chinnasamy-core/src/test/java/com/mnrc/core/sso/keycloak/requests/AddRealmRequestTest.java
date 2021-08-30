package com.mnrc.core.sso.keycloak.requests;

import com.mnrc.core.enums.HttpRequestMethods;
import com.mnrc.core.sso.keycloak.httpclient.KeyCloakHttpClient;
import net.bytebuddy.utility.RandomString;
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
public class AddRealmRequestTest {

    @Value("${sso.server.url.prefix}")
    private String keyCloakUrlPrefix;

    @Test
    public void testAddRealmRequest() throws IOException {
        String url = String .format("%s/%s", this.keyCloakUrlPrefix, "admin/realms");
        String realmName = RandomString.make();
        AddRealmRequest addRealmRequest = new AddRealmRequest(url, HttpRequestMethods.POST.name(), realmName);
        Response response = KeyCloakHttpClient.executeRequest(addRealmRequest);

        Assert.assertNotNull(response);
    }
}
