package com.mnrc.core.sso.keycloak.requests;

public abstract class KeyCloakRequest {

    private String url;

    private String method;

    private String body;

    private String contentType;

    public KeyCloakRequest(String url, String method){
        this.url = url;
        this.method = method;
        this.contentType = "application/json";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod(){
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public abstract String getBody();

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
