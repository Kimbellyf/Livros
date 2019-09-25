# Livros

<b>Objetivo do aplicativo:</b> Buscar os dados sobre os livros numa API que retorna um json parecido com a estrutura json (apresentada aqui abaixo do preview) e transformar ela em lista de livros, de autores, de categorias e seus respectivos detalhes em um app Android.
<br>
<br>
<b>Preview do app construído a partir desse objetivo:</b>
<br>
<br>
<a href="https://imgflip.com/gif/3bkals"><img src="https://i.imgflip.com/3bkals.gif" title="made at imgflip.com"/></a>
<br>
<br>
<b>Exemplo de Json consumido:</b>
<br>
```javascript
[{ 
      "title":"XML Programming with VB and ASP",
      "isbn":"1884777872",
      "pageCount":320,
      "publishedDate":{ 
         "$date":"1999-12-01T00:00:00.000-0800"
      },
      "thumbnailUrl":"https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/wilson.jpg",
      "longDescription":"Here's a book that shows you when and how to use XML from both a programming and business perspective. Laden with source code, XML Programming with VB and ASP helps you build solutions that are flexible, future-proof, and self-describing. It will help you apply XML concepts between the client and the server and the server and data objects or data services.    Finally, you have everything a VB and ASP developer needs to keep up with the explosive growth of XML.",
      "status":"PUBLISH",
      "authors":[ 
         "Mark Wilson",
         "Tracey Wilson"
      ],
      "categories":[ 
         "XML",
         "Internet"
      ]
   },
   { 
      "title":"Oracle8i Database Administration",
      "isbn":"1884777783",
      "pageCount":543,
      "publishedDate":{ 
         "$date":"1999-11-01T00:00:00.000-0800"
      },
      "thumbnailUrl":"https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/yuhanna.jpg",
      "longDescription":"Databases are growing larger, and the use of distributed databases is on the rise. Oracle8i Database Administration addresses some of the most common yet complex issues that are faced by DBAs around the world.",
      "status":"PUBLISH",
      "authors":[ 
         "Noel Yuhanna"
      ],
      "categories":[ 
         "Client-Server",
         "Networking"
      ]
   }]
```
<b> Biblioteca utilizada para consumir a API:</b> Retrofit

<br>
<br>

Como este aplicativo Android foi realizado em Java aqui está o diagrama de classes gerado a partir do json(com algumas modificações).
<br>
BookDiagramClass
*Obs: Classe Edition mudou para PublishedDate* e o seu atributo mudou para date*
Uso do retrofit no consumo da API* bagunça no repositório devido aos testes
<br>
![BookDiagramClass](https://github.com/Kimbellyf/Livros/blob/master/bookclassdiagram.jpg)
<br>
