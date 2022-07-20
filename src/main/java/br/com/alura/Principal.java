package br.com.alura;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static br.com.alura.Manipulador.getProp;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException, FontFormatException {

//        Propriedades do token
        Properties prop = getProp();
        String tokenIMDB = prop.getProperty("prop.token");
        String tokenNasa = prop.getProperty("prop.tokenNasa");
        String urlNasa = "https://api.nasa.gov/planetary/apod?api_key=";
        String urlTopFilmes = "https://imdb-api.com/en/API/Top250Movies/";
        String urlFilmesPopulares = "https://imdb-api.com/en/API/MostPopularMovies/";
        String urlMockito = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        String urlTeste = "https://api.nasa.gov/planetary/apod?api_key=wfEP3Sup3sUNZKOWuZfIZ9xy98nQkrtbtSddgbq4&start_date=2022-07-19&end_date=2022-07-20";
//         Fazer conexão HTTP para buscar os TOP 250 Filmes (JSON)
        String json = new ClienteHttp().getDadosSemToken(urlTeste);
//        System.out.println("JSON: "+json);

        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaConteudos = parser.parse(json);

        GeradoraDeSticker geradora = new GeradoraDeSticker();

//        Exibir e manipular os dados
        for (Map<String, String> conteudo: listaConteudos ) {
            String titulo = conteudo.get("title").trim();
            String urlImagem = conteudo.get("url");
//            String urlImagem = conteudo.get("image");
//            Double notaFilme = Double.parseDouble(conteudo.get("imDbRating"));
            Double notaFilme = 10.0;
            InputStream inputStream = new URL(urlImagem).openStream();
            System.out.println("\u001b[1m \u001b[41m Titulo: \u001b[m " +
                    titulo);
            System.out.println("\u001b[1m \u001b[44m Poster: \u001b[m " +
                    urlImagem);

//            if(notaFilme >= 9){
//                System.out.println("\u001b[1m \u001b[42m Nota: \u001b[m " +
//                        notaFilme);
//                mensagemPoster = "Filme TOP";
//            }else if(notaFilme >= 7){
//                System.out.println("\u001b[1m \u001b[43m Nota: \u001b[m " +
//                        notaFilme);
//                mensagemPoster = "Bom Filme!";
//            }else if(notaFilme >= 4){
//                System.out.println("\u001b[1m \u001b[43m Nota: \u001b[m " +
//                        notaFilme);
//                mensagemPoster = "Filme classe C";
//            }else{
//                System.out.println("\u001b[1m \u001b[43m Nota: \u001b[m " +
//                        notaFilme);
//                mensagemPoster = "Nem perca tempo assistindo";
//            }
            geradora.cria(inputStream, titulo+".png", notaFilme);
        }
        System.out.println("Tamanho do arquivo: " + listaConteudos.size());
    }
}
