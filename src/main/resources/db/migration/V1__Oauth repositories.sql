ALTER TABLE user_roles
DROP
FOREIGN KEY FK7ecyobaa59vxkxckg6t355l86;

ALTER TABLE user_token
DROP
FOREIGN KEY FKc7aib954uhug0hu7viun20gw;

ALTER TABLE user_roles
DROP
FOREIGN KEY FKj9553ass9uctjrmh0gkqsmv0d;

ALTER TABLE user_token
DROP
FOREIGN KEY FKraru0qloxyh7wacxneiam2hi7;

CREATE TABLE authorization
(
    id                            VARCHAR(255) NOT NULL,
    registered_client_id          VARCHAR(255) NULL,
    principal_name                VARCHAR(255) NULL,
    authorization_grant_type      VARCHAR(255) NULL,
    authorized_scopes             TEXT NULL,
    attributes                    TEXT NULL,
    state                         TEXT NULL,
    authorization_code_value      TEXT NULL,
    authorization_code_issued_at  datetime NULL,
    authorization_code_expires_at datetime NULL,
    authorization_code_metadata   VARCHAR(255) NULL,
    access_token_value            TEXT NULL,
    access_token_issued_at        datetime NULL,
    access_token_expires_at       datetime NULL,
    access_token_metadata         TEXT NULL,
    access_token_type             VARCHAR(255) NULL,
    access_token_scopes           TEXT NULL,
    refresh_token_value           TEXT NULL,
    refresh_token_issued_at       datetime NULL,
    refresh_token_expires_at      datetime NULL,
    refresh_token_metadata        TEXT NULL,
    oidc_id_token_value           TEXT NULL,
    oidc_id_token_issued_at       datetime NULL,
    oidc_id_token_expires_at      datetime NULL,
    oidc_id_token_metadata        TEXT NULL,
    oidc_id_token_claims          TEXT NULL,
    user_code_value               TEXT NULL,
    user_code_issued_at           datetime NULL,
    user_code_expires_at          datetime NULL,
    user_code_metadata            TEXT NULL,
    device_code_value             TEXT NULL,
    device_code_issued_at         datetime NULL,
    device_code_expires_at        datetime NULL,
    device_code_metadata          TEXT NULL,
    CONSTRAINT pk_authorization PRIMARY KEY (id)
);

DROP TABLE user_roles;

DROP TABLE user_token;

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

ALTER TABLE authorizationconsent
DROP
COLUMN principalName;

ALTER TABLE authorizationconsent
DROP
COLUMN registeredClientId;