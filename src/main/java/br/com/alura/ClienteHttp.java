package br.com.alura;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// Classe que realiza a conexão HTTP com a API do IMDB
public class ClienteHttp {

//    Método que retorna um JSON contendo a resposta da requisição ao endpoint da API
    public String getDadosComToken(String url, String token) {
        try {
            URI endereco = URI.create(url + token);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        }catch (IOException | InterruptedException ex){
            throw new RuntimeException(ex);
        }

    }

    public String getDadosSemToken(String url) {
        try{
            URI endereco = URI.create(url);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }catch(IOException | InterruptedException ex){
            throw new RuntimeException(ex);
        }

    }
}