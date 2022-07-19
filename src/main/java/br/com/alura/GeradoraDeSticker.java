package br.com.alura;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GeradoraDeSticker {

    public void cria(InputStream inputStream, String nomeArquivo, String frase) throws IOException {
//        Ler uma imagem
//        InputStream inputStream = new FileInputStream(new File("Entrada/Filme.jpg"));
//        InputStream inputStream1 = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_10.jpg").
//                openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);
//        criar uma imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200; // Tamanho medido em pixels
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

//        copiar a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal,0,0, null);

//        Configurar fonte para gravar o texto na imaggem
        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, (int)largura/15);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

//        Escrever uma frase na nova imagem
        graphics.drawString(frase, (int)largura/4, altura+100);

//        Escrever a nova imagem em um arquivo
//        Se a pasta não existir, será criada uma nova pasta com nome saída
        Path path = Paths.get("saida");
        if(!Files.exists(path))
            Files.createDirectory(path);

        ImageIO.write(novaImagem,"png",new File("saida/"+nomeArquivo));
    }
}
