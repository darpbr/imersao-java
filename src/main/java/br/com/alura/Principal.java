package br.com.alura;

import com.diogonunes.jcolor.AnsiFormat;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import static br.com.alura.Manipulador.getProp;
import static com.diogonunes.jcolor.Attribute.*;

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
        String urlLinguagens = "https://darp-linguagens-api.herokuapp.com/ranking-linguagens";


        String json = new ClienteHttp().getDados(urlMockito);
        List<Conteudo> listaConteudos = new ExtratorConteudoImdb().extraiConteudo(json);

        GeradoraDeSticker geradora = new GeradoraDeSticker();

        AnsiFormat fPoster = new AnsiFormat(BRIGHT_BLUE_TEXT(), BLACK_BACK(), BOLD());
        AnsiFormat fTitulo = new AnsiFormat(BLACK_TEXT(), BRIGHT_RED_BACK(), BOLD());


//        Exibir e manipular os dados
        for (Conteudo conteudo: listaConteudos ) {
            Double notaFilme = 10.0;
            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            System.out.println(fTitulo.format(conteudo.titulo()));
            System.out.println(fPoster.format(conteudo.urlImagem()));
            System.out.println("\uD83C\uDF1F \uD83C\uDF1F");
            System.out.println("Nota do Filme: " + conteudo.nota());

            geradora.cria(inputStream, conteudo.titulo()+".png");
        }
        System.out.println("Tamanho do arquivo: " + listaConteudos.size());
    }
}
