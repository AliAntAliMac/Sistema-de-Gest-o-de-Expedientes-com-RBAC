Sistema de Gestão de Expedientes - Versão Final
Este projeto implementa um Sistema de Gestão de Expedientes com controlo de acesso baseado em papéis (RBAC) e funcionalidade de registo de novos utilizadores.

Funcionalidades Principais
•	Login e Logout: Acesso seguro ao sistema.
•	Criar Conta: Novos utilizadores podem registar-se e recebem automaticamente o papel de "Funcionário".
•	RBAC (Controlo de Acesso): 
◦	Administrador/Gestor: Podem ver a lista, registar e tramitar expedientes.
◦	Funcionário: Pode ver a lista e registar, mas não tem permissão para tramitar.
•	Gestão de Expedientes: Registo persistente na base de dados PostgreSQL.

Configuração
1	Base de Dados: Crie a DB db_expedientes no PostgreSQL 16.
2	Configuração: Ajuste a senha em src/main/resources/application.properties.
3	Dados Iniciais: Execute o script SQL fornecido anteriormente para criar os papéis iniciais (Admin, Gestor, Funcionario).
4	Execução: Use mvn spring-boot:run na pasta raiz.
5	Acesso: Abra http://localhost:8080/index.html no seu navegador.

Credenciais de Teste
•	Admin: admin@isced.ac.mz / 123456
•	Gestor: maria@isced.ac.mz / 123456
•	Funcionário: jose@isced.ac.mz / 123456
