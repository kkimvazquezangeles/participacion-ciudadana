CREATE TABLE IF NOT EXISTS USUARIO (
    USERNAME VARCHAR(25) PRIMARY KEY,
    PASSWORD VARCHAR(70) NOT NULL,
    ENABLED BIT NOT NULL
);

CREATE TABLE IF NOT EXISTS USER_ROLE (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    USERNAME VARCHAR(25) NOT NULL,
    ROLE VARCHAR(25) NOT NULL
);