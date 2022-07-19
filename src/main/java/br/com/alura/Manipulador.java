package br.com.alura;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// Classe que vai manipular o token para que não fique visível no repositório publico
public class Manipulador {

//    Retorna o arquivo de propriedades contendo o token do usuário
    public static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("./src/main/resources/dados.properties");
        props.load(file);
        return props;
    }
}
