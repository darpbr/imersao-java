package br.com.alura;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
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
        String urlTopFilmes = "https://imdb-api.com/en/API/Top250Movies/";
        String urlFilmesPopulares = "https://imdb-api.com/en/API/MostPopularMovies/";
        String urlMockito = "https://api.mocki.io/v2/549a5d8b/Top250Movies";

//         Fazer conexão HTTP para buscar os TOP 250 Filmes (JSON)
        Conexao con = new Conexao();
//        String json = con.getConexao(urlTopFilmes, tokenIMDB);
        String json = con.getConexao2(urlMockito);

//        fazer o parser do JSON com os dados que nos interessam - Título, poster (imagem) e
//        classificação (rating)

//        Criamos uma lista para receber os valores separados de cada filme
//        Utilizamos um Map para vincular chave-valor
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaFilmes = parser.parse(json);
//        System.out.println("Tamanho da lista de filmes: " + listaFilmes.size());
//        System.out.println("Primeiro filme da lista: " + listaFilmes.get(0));

        GeradoraDeSticker geradora = new GeradoraDeSticker();
        String mensagemPoster = "";
//        Exibir e manipular os dados
        for (Map<String, String> filme: listaFilmes ) {
            String titulo = filme.get("title").trim();
            String urlImagem = filme.get("image");
            Double notaFilme = Double.parseDouble(filme.get("imDbRating"));
            InputStream inputStream = new URL(urlImagem).openStream();
            System.out.println("\u001b[1m \u001b[41m Titulo: \u001b[m " +
                    titulo);
            System.out.println("\u001b[1m \u001b[44m Poster: \u001b[m " +
                    urlImagem);
            if(notaFilme >= 9){
                System.out.println("\u001b[1m \u001b[42m Nota: \u001b[m " +
                        notaFilme);
                mensagemPoster = "Filme Nota 10!";
            }else{
                System.out.println("\u001b[1m \u001b[43m Nota: \u001b[m " +
                        notaFilme);
                mensagemPoster = "Bom Filme!";
            }
            geradora.cria(inputStream, titulo+".png", mensagemPoster);
        }
        System.out.println("Tamanho do arquivo: " + listaFilmes.size());
    }
}
