package br.com.alura;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoNasa implements ExtratorConteudo{

    public List<Conteudo> extraiConteudo(String json){

        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaJson = parser.parse(json);

        List<Conteudo> listaConteudo = new ArrayList<>();
        for (Map<String, String> conteudoJson: listaJson ) {
            String titulo = conteudoJson.get("title");
            String urlImagem = conteudoJson.get("url");
            listaConteudo.add(new Conteudo(titulo, urlImagem));
        }
        return listaConteudo;
    }
}
