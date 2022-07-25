package br.com.alura;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GeradoraDeSticker {

    public void cria(InputStream inputStream, String nomeArquivo) throws IOException, FontFormatException {

        BufferedImage imagemOriginal = ImageIO.read(inputStream);
        InputStream iconeFigurinha = new FileInputStream(new File("images/icone-filme.png"));
        BufferedImage icone = ImageIO.read(iconeFigurinha);

//        criar uma imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200; // Tamanho medido em pixels
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

//        copiar a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal,0,0, null);

//        Configurar fonte para gravar o texto na imaggem
        Font impact = Font.createFont(Font.TRUETYPE_FONT,
                        new File("fonte/impact.ttf"))
                .deriveFont(Font.PLAIN, largura/15);

        //        Veririficando a nota para personalização da mensagem e inclusão do icone no poster
        String mensagem = "Teste Saída";
//
//        if(nota >= 9){
//            System.out.println("\u001b[1m \u001b[42m Nota: \u001b[m " +
//                    nota);
////            System.out.println("U+2606");
//            mensagem = "Filme TOP";
//            graphics.drawImage(icone, (largura/2)+largura/4, novaAltura - 100, null);
//        }else if(nota >= 7){
//            System.out.println("\u001b[1m \u001b[43m Nota: \u001b[m " +
//                    nota);
//            mensagem = "Bom Filme!";
//        }else if(nota >= 4){
//            System.out.println("\u001b[1m \u001b[43m Nota: \u001b[m " +
//                    nota);
//            mensagem = "Filme classe C";
//        }else if(nota >= 0) {
//            System.out.println("\u001b[1m \u001b[43m Nota: \u001b[m " +
//                    nota);
//            mensagem = "Nem perca tempo assistindo";
//        }
//        else{
//            System.out.println("Não é um filme!");
//            mensagem = "Bela Imagem!";
//        }

        graphics.setColor(Color.YELLOW);
        graphics.setFont(impact);
        int tamanhoFrase = graphics.getFontMetrics().stringWidth(mensagem);
        int localFrase = (novaImagem.getWidth() - tamanhoFrase)/2;

//        Escrever uma frase na nova imagem
        graphics.drawString(mensagem, localFrase, novaAltura-100);


//        Escrever a nova imagem em um arquivo
//        Se a pasta não existir, será criada uma nova pasta com nome saída
        Path path = Paths.get("saida");
        if(!Files.exists(path))
            Files.createDirectory(path);

        ImageIO.write(novaImagem,"png",new File("saida/"+nomeArquivo));
    }
}
