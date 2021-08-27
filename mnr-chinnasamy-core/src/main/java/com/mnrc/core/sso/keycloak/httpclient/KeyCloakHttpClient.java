package com.mnrc.core.sso.keycloak.httpclient;

import com.mnrc.core.enums.HttpRequestHeaders;
import com.mnrc.core.sso.keycloak.requests.KeyCloakRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class KeyCloakHttpClient {

    public static Response executeRequest(KeyCloakRequest keyCloakRequest) throws IOException {
        System.out.println(keyCloakRequest.getBody());
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(keyCloakRequest.getUrl())
                .method(keyCloakRequest.getMethod(), RequestBody.create(keyCloakRequest.getBody().getBytes(StandardCharsets.UTF_8)))
                .addHeader(HttpRequestHeaders.CONTENT_TYPE.getValue(), keyCloakRequest.getContentType())
                .build();
        return client.newCall(request).execute();
    }
}
