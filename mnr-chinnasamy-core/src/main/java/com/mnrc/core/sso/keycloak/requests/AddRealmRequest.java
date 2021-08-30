package com.mnrc.core.sso.keycloak.requests;

public class AddRealmRequest extends KeyCloakRequest{

    private String realmName;

    public AddRealmRequest(String url, String method, String realmName) {
        super(url, method);
        this.realmName = realmName;
    }

    @Override
    public String getBody() {
        return String.format("{\n    \"id\": \"%s\",\n    \"realm\": \"%s\",\n    \"notBefore\": 0,\n    \"revokeRefreshToken\": false,\n    \"refreshTokenMaxReuse\": 0,\n    \"accessTokenLifespan\": 300,\n    \"accessTokenLifespanForImplicitFlow\": 900,\n    \"ssoSessionIdleTimeout\": 1800,\n    \"ssoSessionMaxLifespan\": 36000,\n    \"ssoSessionIdleTimeoutRememberMe\": 0,\n    \"ssoSessionMaxLifespanRememberMe\": 0,\n    \"offlineSessionIdleTimeout\": 2592000,\n    \"offlineSessionMaxLifespanEnabled\": false,\n    \"offlineSessionMaxLifespan\": 5184000,\n    \"clientSessionIdleTimeout\": 0,\n    \"clientSessionMaxLifespan\": 0,\n    \"accessCodeLifespan\": 60,\n    \"accessCodeLifespanUserAction\": 300,\n    \"accessCodeLifespanLogin\": 1800,\n    \"actionTokenGeneratedByAdminLifespan\": 43200,\n    \"actionTokenGeneratedByUserLifespan\": 300,\n    \"enabled\": true,\n    \"sslRequired\": \"external\",\n    \"registrationAllowed\": false,\n    \"registrationEmailAsUsername\": false,\n    \"rememberMe\": false,\n    \"verifyEmail\": false,\n    \"loginWithEmailAllowed\": true,\n    \"duplicateEmailsAllowed\": false,\n    \"resetPasswordAllowed\": false,\n    \"editUsernameAllowed\": false,\n    \"bruteForceProtected\": false,\n    \"permanentLockout\": false,\n    \"maxFailureWaitSeconds\": 900,\n    \"minimumQuickLoginWaitSeconds\": 60,\n    \"waitIncrementSeconds\": 60,\n    \"quickLoginCheckMilliSeconds\": 1000,\n    \"maxDeltaTimeSeconds\": 43200,\n    \"failureFactor\": 30,\n    \"defaultRoles\": [\n        \"offline_access\",\n        \"uma_authorization\"\n    ],\n    \"requiredCredentials\": [\n        \"password\"\n    ],\n    \"otpPolicyType\": \"totp\",\n    \"otpPolicyAlgorithm\": \"HmacSHA1\",\n    \"otpPolicyInitialCounter\": 0,\n    \"otpPolicyDigits\": 6,\n    \"otpPolicyLookAheadWindow\": 1,\n    \"otpPolicyPeriod\": 30,\n    \"otpSupportedApplications\": [\n        \"FreeOTP\",\n        \"Google Authenticator\"\n    ],\n    \"webAuthnPolicyRpEntityName\": \"keycloak\",\n    \"webAuthnPolicySignatureAlgorithms\": [\n        \"ES256\"\n    ],\n    \"webAuthnPolicyRpId\": \"\",\n    \"webAuthnPolicyAttestationConveyancePreference\": \"not specified\",\n    \"webAuthnPolicyAuthenticatorAttachment\": \"not specified\",\n    \"webAuthnPolicyRequireResidentKey\": \"not specified\",\n    \"webAuthnPolicyUserVerificationRequirement\": \"not specified\",\n    \"webAuthnPolicyCreateTimeout\": 0,\n    \"webAuthnPolicyAvoidSameAuthenticatorRegister\": false,\n    \"webAuthnPolicyAcceptableAaguids\": [],\n    \"webAuthnPolicyPasswordlessRpEntityName\": \"keycloak\",\n    \"webAuthnPolicyPasswordlessSignatureAlgorithms\": [\n        \"ES256\"\n    ],\n    \"webAuthnPolicyPasswordlessRpId\": \"\",\n    \"webAuthnPolicyPasswordlessAttestationConveyancePreference\": \"not specified\",\n    \"webAuthnPolicyPasswordlessAuthenticatorAttachment\": \"not specified\",\n    \"webAuthnPolicyPasswordlessRequireResidentKey\": \"not specified\",\n    \"webAuthnPolicyPasswordlessUserVerificationRequirement\": \"not specified\",\n    \"webAuthnPolicyPasswordlessCreateTimeout\": 0,\n    \"webAuthnPolicyPasswordlessAvoidSameAuthenticatorRegister\": false,\n    \"webAuthnPolicyPasswordlessAcceptableAaguids\": [],\n    \"browserSecurityHeaders\": {\n        \"contentSecurityPolicyReportOnly\": \"\",\n        \"xContentTypeOptions\": \"nosniff\",\n        \"xRobotsTag\": \"none\",\n        \"xFrameOptions\": \"SAMEORIGIN\",\n        \"contentSecurityPolicy\": \"frame-src 'self'; frame-ancestors 'self'; object-src 'none';\",\n        \"xXSSProtection\": \"1; mode=block\",\n        \"strictTransportSecurity\": \"max-age=31536000; includeSubDomains\"\n    },\n    \"smtpServer\": {},\n    \"eventsEnabled\": false,\n    \"eventsListeners\": [\n        \"jboss-logging\"\n    ],\n    \"enabledEventTypes\": [],\n    \"adminEventsEnabled\": false,\n    \"adminEventsDetailsEnabled\": false,\n    \"identityProviders\": [\n        {\n            \"alias\": \"keycloak-oidc\",\n            \"internalId\": \"d79d0d65-8ee1-47f0-8611-f9e6eea71f20\",\n            \"providerId\": \"keycloak-oidc\",\n            \"enabled\": true,\n            \"updateProfileFirstLoginMode\": \"on\",\n            \"trustEmail\": false,\n            \"storeToken\": false,\n            \"addReadTokenRoleOnCreate\": false,\n            \"authenticateByDefault\": false,\n            \"linkOnly\": false,\n            \"firstBrokerLoginFlowAlias\": \"first broker login\",\n            \"config\": {\n                \"clientId\": \"ssss\",\n                \"tokenUrl\": \"http://localhost\",\n                \"authorizationUrl\": \"http://localhost\",\n                \"clientAuthMethod\": \"client_secret_basic\",\n                \"syncMode\": \"IMPORT\",\n                \"clientSecret\": \"assaasa\",\n                \"useJwksUrl\": \"true\"\n            }\n        },\n        {\n            \"alias\": \"keycloak-oidc-2\",\n            \"internalId\": \"7cf3fd74-8d3a-4c8d-b651-fcc885df8a31\",\n            \"providerId\": \"keycloak-oidc\",\n            \"enabled\": true,\n            \"updateProfileFirstLoginMode\": \"on\",\n            \"trustEmail\": false,\n            \"storeToken\": false,\n            \"addReadTokenRoleOnCreate\": false,\n            \"authenticateByDefault\": false,\n            \"linkOnly\": false,\n            \"firstBrokerLoginFlowAlias\": \"first broker login\",\n            \"config\": {}\n        }\n    ],\n    \"identityProviderMappers\": [\n        {\n            \"id\": \"42c7b62d-4383-42c9-a8a0-65519e2c2543\",\n            \"name\": \"test-mapper\",\n            \"identityProviderAlias\": \"keycloak-oidc2\",\n            \"identityProviderMapper\": \"keycloak-oidc\",\n            \"config\": {}\n        },\n        {\n            \"id\": \"ea65c956-24c7-4587-8fe7-c07222e3485d\",\n            \"name\": \"test\",\n            \"identityProviderAlias\": \"keycloak-oidc-2\",\n            \"identityProviderMapper\": \"hardcoded-user-session-attribute-idp-mapper\",\n            \"config\": {\n                \"syncMode\": \"INHERIT\"\n            }\n        }\n    ],\n    \"internationalizationEnabled\": false,\n    \"supportedLocales\": [],\n    \"browserFlow\": \"browser\",\n    \"registrationFlow\": \"registration\",\n    \"directGrantFlow\": \"direct grant\",\n    \"resetCredentialsFlow\": \"reset credentials\",\n    \"clientAuthenticationFlow\": \"clients\",\n    \"dockerAuthenticationFlow\": \"docker auth\",\n    \"attributes\": {},\n    \"userManagedAccessAllowed\": false\n}", this.realmName, this.realmName);
    }
}
