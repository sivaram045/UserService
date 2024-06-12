ALTER TABLE authorization_consent
    ADD principal_name VARCHAR(255) NULL;

ALTER TABLE authorization_consent
    ADD registered_client_id VARCHAR(255) NULL;

ALTER TABLE client
    ADD redirect_uris TEXT NULL;

ALTER TABLE client
    ADD token_settings TEXT NULL;

ALTER TABLE authorization_consent
DROP
COLUMN principalName;

ALTER TABLE authorization_consent
DROP
COLUMN registeredClientId;

ALTER TABLE client
DROP
COLUMN authorizationGrantTypes;

ALTER TABLE client
DROP
COLUMN clientAuthenticationMethods;

ALTER TABLE client
DROP
COLUMN clientId;

ALTER TABLE client
DROP
COLUMN clientIdIssuedAt;

ALTER TABLE client
DROP
COLUMN clientName;

ALTER TABLE client
DROP
COLUMN clientSecret;

ALTER TABLE client
DROP
COLUMN clientSecretExpiresAt;

ALTER TABLE client
DROP
COLUMN clientSettings;

ALTER TABLE client
DROP
COLUMN postLogoutRedirectUris;

ALTER TABLE client
DROP
COLUMN redirectUris;

ALTER TABLE client
DROP
COLUMN tokenSettings;

ALTER TABLE authorization
    MODIFY access_token_metadata TEXT;

ALTER TABLE authorization
    MODIFY access_token_scopes TEXT;

ALTER TABLE authorization
    MODIFY access_token_value TEXT;

ALTER TABLE authorization
    MODIFY attributes TEXT;

ALTER TABLE authorization_consent
    MODIFY authorities VARCHAR (1000) NULL;

ALTER TABLE authorization
    MODIFY authorization_code_value TEXT;

ALTER TABLE client
    MODIFY authorization_grant_types TEXT;

ALTER TABLE authorization
    MODIFY authorized_scopes TEXT;

ALTER TABLE client
    MODIFY client_authentication_methods TEXT;

ALTER TABLE client
    MODIFY client_settings TEXT;

ALTER TABLE authorization
    MODIFY device_code_metadata TEXT;

ALTER TABLE authorization
    MODIFY device_code_value TEXT;

ALTER TABLE authorization
    MODIFY oidc_id_token_claims TEXT;

ALTER TABLE authorization
    MODIFY oidc_id_token_metadata TEXT;

ALTER TABLE authorization
    MODIFY oidc_id_token_value TEXT;

ALTER TABLE client
    MODIFY post_logout_redirect_uris TEXT;

ALTER TABLE authorization
    MODIFY refresh_token_metadata TEXT;

ALTER TABLE authorization
    MODIFY refresh_token_value TEXT;

ALTER TABLE client
    MODIFY scopes TEXT;

ALTER TABLE client
    MODIFY scopes TEXT NULL;

ALTER TABLE authorization
    MODIFY state TEXT;

ALTER TABLE authorization
    MODIFY user_code_metadata TEXT;

ALTER TABLE authorization
    MODIFY user_code_value TEXT;

ALTER TABLE authorization_consent
    ADD PRIMARY KEY (registered_client_id, principal_name);