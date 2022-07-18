package br.com.alura;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
//        System.out.println("Hello World!");

        String tokenIMDB = "k_frojmf22";
        String url = "https://imdb-api.com/en/API/Top250Movies/";

//         Fazer conexão HTTP para buscar os TOP 250 Filmes (JSON)
        URI endereco = URI.create(url+tokenIMDB);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
//        System.out.println(json);

//        fazer o parser do JSON com os dados que nos interessam - Título, poster (imagem) e
//        classificação (rating)

//        Criamos uma lista para receber os valores separados de cada filme
//        Utilizamos um Map para vincular chave-valor
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaFilmes = parser.parse(json);
//        System.out.println("Tamanho da lista de filmes: " + listaFilmes.size());
//        System.out.println("Primeiro filme da lista: " + listaFilmes.get(0));

//        Exibir e manipular os dados
        for (Map<String, String> filme: listaFilmes ) {
            System.out.println(filme.get("title"));
        }
    }
}
