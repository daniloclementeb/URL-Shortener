Readme.md

Esse projeto faz parte de um projeto mais amplo, onde o usuário deve inserir uma url e a mesma é reduzida utilizando criptografia MD5.
Nessa atividade temos 2 endpoint no backend utilizando Spring Boot e a Linguagem Kotlin.
O Projeto possui integração com o Banco de Dados Postgree
Os dados para acesso ao banco de dados estão no arquivo application.properties.


Como Configurar o Servidor

Para configurar o servidor, é necessário a instalar o Postgree rodando junto com a maquina local.
os dados para acesso ao postgree estao dentro da aplicação main>resources>application.properties

Executando

Foram criados três endpoints para consumo:

GET: /path_reduzido
Esse endpoint recebe o path reduzido e direciona o cliente para a rota principal
Além disso, o endpoint tambem incrementa a quantidade de acessos que a URL possui

PUT: /
Esse endpoint insere uma rota no banco de dados e recebe a mesma reduzida
Body:
{ 
	"url":"http://pudim.com.br/"
}

Response:
{
    "code": "SERVICE:OK:0001",
    "message": "success",
    "url": [
        {
            "url": "http://pudiam.com.br/",
            "shortUrl": "http://localhost:8080/b50deb",
            "creationDate": "2021-05-01T22:02:35.684+00:00"
        }
    ]
}

GET: /
Esse endpoint lista todos as urls inseridas no banco para consultar a quantidade de acessos cada url possue

