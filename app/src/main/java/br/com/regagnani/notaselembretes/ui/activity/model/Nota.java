package br.com.regagnani.notaselembretes.ui.activity.model;

public class Nota {

private final String titulo;
private final String descricao;

    public Nota(String titulo, String descricao){
        this.titulo = titulo;
        this.descricao = descricao;

    }

    public String getTitulo(){
        return this.titulo;
    }

    public String getDescricao(){
        return this.descricao;
    }

}
