--Criar uma tabela em memória dos sistemas do usuário, que agrupe por cpf todos
--os sistemas do determinado usuário;

CREATE VIEW V_USUARIOSISTEMA AS
SELECT U.CPF, S.NOME 
FROM USUARIO U 
INNER JOIN USUARIOSISTEMA US ON U.USUARIOID = US.USUARIOID
INNER JOIN SISTEMA S ON S.SISTEMAID = US.SISTEMAID
GROUP BY U.CPF;

--Retornar todos os usuários do sistema com o CPF (com máscara), o nome do usuário 
--em caixa alta, bem como os cargos, orgãos e sistemas associados (se existir);

SELECT substr(U.CPF,1,3)||'.'||substr(U.CPF,4,3)||'.'||substr(U.CPF,7,3)||'-'||substr(U.CPF,10,2) AS CPF,
       UPPER(U.NOME) AS NOME_USUARIO,
       C.DESCRICAO AS CARGO,
       O.NOME AS ORGAO,
       S.NOME AS SISTEMA
FROM USUARIO U
INNER JOIN CARGO C ON U.CARGOID = C.CARGOID
INNER JOIN ORGAO O ON U.ORGAOID = O.ORGAOID
LEFT JOIN USUARIOSISTEMA US ON U.USUARIOID = US.USUARIOID
LEFT JOIN SISTEMA S ON S.SISTEMAID = US.SISTEMAID;

SELECT UPPER(NM_PACIENTE) FROM PACIENTE WHERE CD_PACIENTE = 2

