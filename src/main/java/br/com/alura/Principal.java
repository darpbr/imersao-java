package br.com.alura;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static br.com.alura.Manipulador.getProp;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {

//        Propriedades do token
        Properties prop = getProp();
        String tokenIMDB = prop.getProperty("prop.token");
        String url = "https://imdb-api.com/en/API/Top250Movies/";
        String urlFilmesPopulares = "https://imdb-api.com/en/API/MostPopularMovies/";

//         Fazer conexão HTTP para buscar os TOP 250 Filmes (JSON)
        URI endereco = URI.create(url+tokenIMDB);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

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
            System.out.println("\u001b[1m \u001b[41m Titulo: \u001b[m " +
                    filme.get("title"));
            System.out.println("\u001b[1m \u001b[41m Poster: \u001b[m " +
                    filme.get("image"));
            System.out.println("\u001b[1m \u001b[41m Nota: \u001b[m " +
                    filme.get("imDbRating"));
        }
    }
}
