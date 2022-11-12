package br.com.regagnani.notaselembretes.ui.activity.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.regagnani.notaselembretes.ui.activity.model.Nota;

public class NotaDAO {

    private final static ArrayList<Nota> notas = new ArrayList<Nota>();


    public List<Nota> todos() {
        return (List<Nota>) NotaDAO.notas.clone();
    }

    public void insere(Nota... notas) {
        NotaDAO.notas.addAll(Arrays.asList(notas));
    }

    public void altera(int posicao, Nota nota) {
        NotaDAO.notas.set(posicao, nota);
    }

    public void remove(int posicao) {
        notas.remove(posicao);
    }

    public void troca(int posicaoInicial, int posicaoFinal) {
        Collections.swap(notas, posicaoInicial, posicaoFinal);
    }

    public void removeTodos() {
        notas.clear();
    }

}
