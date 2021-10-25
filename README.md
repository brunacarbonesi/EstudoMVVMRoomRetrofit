# EstudoMVVMRoomRetrofit

**Este projeto é para o estudo de aplicação construída com arquitetura MVVM e 
seguindo as diretrizes de uma clean archtecture.**

## Background
Lista de filmes consumidas da Api do Imdb, mostrando os filmes mais populares.
São três telas:
1. Lista de filmes
2. Detalhes do filme selecionado 
3. Sobre o App

## O projeto

Na lista de filmes, há o tratamento para três status da lista.
1. Success: lista preenchida com os filmes obtidos da api ou do cache (caso o device esteja sem 
   conexão à internet)
2. Error: se houver algum erro com a obtenção desses filmes
3. No value: se a lista de filmes estiver vazia. Este caso ocorrerá com a falta de conexão 
com rede de internet e se a lista não tiver sido preenchida previamente (quando o device estava 
   conectado à internet).
   

Neste projeto, utilizei a arquitetura MVVM para separar as camadas de dados, de apresentação e 
de lógica de negócio. 

Nas **Views**, recebi os valores através do LiveData e gerenciei a exibição dos elementos da tela de 
acordo com o status enviado pelo ViewModel. 

No **ViewModel**, chamei o método no repositório e salvei o seu resultado (status e valor) no MutableLiveData.
Com o objetivo de não expor os dados que podem ser alterados, o retorno para a view é feito através de um LiveData. O ViewModel, por ser lifecycle aware, auxilia no gerenciamento do ciclo de vida da aplicação, evitando problemas, por exemplo, na rotação da tela.

No **Repositório**, é feito o controle sobre qual "chamada" será feita. Se estiver com conexão à rede, a
api será consumida.
Se não, os dados armazenados em cache serão disponibilizados. 

Para o consumo da Api, foi utilizada a biblioteca **Retrofit**. É uma biblioteca Rest que simplifica a comunicação com um webservice RESTful.
Para o armazenamento e consumo dos dados em base local (persistência dos dados), a biblioteca **Room** foi implementada. Com ela, há uma simplificação na aplicação do SQLite.

Sobre os dados, utilizei o **Single** para observar o dado ou o erro do resultado obtido através do repositório.
O Single faz parte do RXJava e com isso pude aplicar o map para transformação dos dados obtidos no banco de dados e na Api.
O compositeDisposable foi incluído para "agrupar" os disposables, que fazem a ligação entre o dado observado e o observador.
Para gerenciar a navegação pelo aplicativo e suas transições, apliquei a biblioteca Navigation do jetpack. 

Para referenciar os elementos das Views, utilizei o view binding e para preencher as dados nas views, utilizei o data binding.
O **Data Binding** colabora com a diminuição de linhas de código e também no tratamento de "nulidade". Ele o faz automaticamente.

### Resumo do design pattern:
- Model e Repositório: lógica de negócio e regras de como os dados podem ser manipulados
- ViewModel: expõe os dados necessários para a View
- View: somente observa as mudanças sinalizadas pelo ViewModel e reage

## Melhorias a serem feitas:
- Teste unitário: foi escrito um teste unitário simples. Pretendo elaborar testes para maior cobertura do código.
- Transição entre fragments: deixar as transições mais suaves.

## A fazer
- Salvar o estado da tela do recyclerView (SaveState)
- Construir os layouts através do Composable do Jetpack.
- Uso da biblioteca Pagination3 do JetPack para consumir a Api como uma lista "infinita".
- Implementação de biblioteca para injeção de dependência (provavelmente Hilt ou Koin)
- Aplicar testes instrumentados
