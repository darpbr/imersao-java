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
    public static void main(String[] args) throws IOException, FontFormatException {

//        Propriedades do token
        Properties prop = getProp();
        String tokenIMDB = prop.getProperty("prop.token");
        String tokenNasa = prop.getProperty("prop.tokenNasa");
        String urlNasa = "https://api.nasa.gov/planetary/apod?api_key=";
        String urlTopFilmes = "https://imdb-api.com/en/API/Top250Movies/";
        String urlFilmesPopulares = "https://imdb-api.com/en/API/MostPopularMovies/";
        String urlMockito = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        String urlTeste = "https://api.nasa.gov/planetary/apod?api_key=wfEP3Sup3sUNZKOWuZfIZ9xy98nQkrtbtSddgbq4&start_date=2022-07-19&end_date=2022-07-20";

        String json = new ClienteHttp().getDadosSemToken(urlMockito);
        List<Conteudo> listaConteudos = new ExtratorConteudoImdb().extraiConteudo(json);

//        String json = new ClienteHttp().getDadosSemToken(urlTeste);
//        List<Conteudo> listaConteudos = new ExtratorConteudoNasa().extraiConteudo(json);

        GeradoraDeSticker geradora = new GeradoraDeSticker();

//        Exibir e manipular os dados
        for (Conteudo conteudo: listaConteudos ) {
            Double notaFilme = 10.0;
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            System.out.println("\u001b[1m \u001b[41m Titulo: \u001b[m " +
                    conteudo.getTitulo());
            System.out.println("\u001b[1m \u001b[44m Poster: \u001b[m " +
                    conteudo.getUrlImagem());

            geradora.cria(inputStream, conteudo.getTitulo()+".png", notaFilme);
        }
        System.out.println("Tamanho do arquivo: " + listaConteudos.size());
    }
}
