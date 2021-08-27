package com.mnrc.core.sso.keycloak.requests;

public class UserAccessTokenRequest extends KeyCloakRequest{

    private String username;

    private String password;

    public UserAccessTokenRequest(String url, String method, String username, String password) {
        super(url, method);
        this.username = username;
        this.password = password;
        this.setContentType("application/x-www-form-urlencoded");
    }

    @Override
    public String getBody() {
        return String.format("client_id=admin-cli&username=%s&password=%s&grant_type=password", this.username, this.password);
    }
}
