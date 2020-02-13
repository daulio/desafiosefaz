-- INSERINDO CARGOS
INSERT INTO cargo (CargoId, Descricao) values (1, 'Analista de sistemas')
/
INSERT INTO cargo (CargoId, Descricao) values (2, 'Desenvolvedor')
/
INSERT INTO cargo (CargoId, Descricao) values (3, 'Analista de requisitos')
/
INSERT INTO cargo (CargoId, Descricao) values (4, 'Arquiteto de software')
/

-- INSERINDO ORGAOS
INSERT INTO orgao (OrgaoId, Nome) values (1, 'Secretaria A')
/
INSERT INTO orgao (OrgaoId, Nome) values (2, 'Secretaria B')
/

-- INSERINDO USUARIO
INSERT INTO Usuario (UsuarioId, Cpf, CargoId, OrgaoId, Nome) values (1, '05033093037',1,1,'James Bond')
/
INSERT INTO Usuario (UsuarioId, Cpf, CargoId, OrgaoId, Nome) values (2, '22917787066',2,1,'Mickey Mouse')
/
INSERT INTO Usuario (UsuarioId, Cpf, CargoId, OrgaoId, Nome) values (3, '38635986016',4,2,'Peter Parker')
/

-- INSERINDO SISTEMA
INSERT INTO sistema (SistemaId, Nome) values (1, 'Sistema A')
/
INSERT INTO sistema (SistemaId, Nome) values (2, 'Sistema B')
/

-- INSERINDO USUARIOSISTEMA
INSERT INTO usuariosistema (SistemaId, UsuarioId) values (1, 1)
/
INSERT INTO usuariosistema (SistemaId, UsuarioId) values (1, 2)
/
INSERT INTO usuariosistema (SistemaId, UsuarioId) values (2, 1)
/
INSERT INTO usuariosistema (SistemaId, UsuarioId) values (3, 1)
/
INSERT INTO usuariosistema (SistemaId, UsuarioId) values (2, 2)
/



