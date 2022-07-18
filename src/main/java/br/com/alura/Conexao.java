package br.com.alura;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// Classe que realiza a conexão HTTP com a API do IMDB
public class Conexao {

//    Classe que retorna um JSON com o retorno da requisição ao endpoint da API
    public String getConexao(String url, String token) throws IOException, InterruptedException {
        URI endereco = URI.create(url+token);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
