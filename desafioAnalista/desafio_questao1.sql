CREATE TABLE Cargo (
    CargoId int NOT NULL,
    Descricao varchar(255),
    CONSTRAINT PK_Cargo PRIMARY KEY (CargoId)
);

CREATE TABLE Orgao (
    OrgaoId int NOT NULL,
    Nome varchar(255),
    CONSTRAINT PK_Orgao PRIMARY KEY (OrgaoId)
);

CREATE TABLE Usuario (
    UsuarioId int NOT NULL,
    Cpf varchar(11) NOT NULL,
    CargoId int,
    OrgaoId int, 
    Nome varchar(255),
    CONSTRAINT PK_Usuario PRIMARY KEY (UsuarioId),
    CONSTRAINT UC_Usuario UNIQUE (Cpf),
    CONSTRAINT FK_UsuarioCargo FOREIGN KEY (CargoId)  REFERENCES Cargo(CargoId),
    CONSTRAINT FK_UsuarioOrgao FOREIGN KEY (OrgaoId)  REFERENCES Orgao(OrgaoId)
);

CREATE TABLE Sistema (
    SistemaId int NOT NULL,
    Nome varchar(255),
    CONSTRAINT PK_Sistema PRIMARY KEY (SistemaId)
);

CREATE TABLE UsuarioSistema (
    SistemaId int NOT NULL,
    UsuarioId int NOT NULL,
    CONSTRAINT PK_UsuarioSistema PRIMARY KEY (SistemaId, UsuarioId),
    CONSTRAINT FK_Usuario FOREIGN KEY (UsuarioId)  REFERENCES Usuario(UsuarioId),
    CONSTRAINT FK_Sistema FOREIGN KEY (SistemaId)  REFERENCES Sistema(SistemaId)
);