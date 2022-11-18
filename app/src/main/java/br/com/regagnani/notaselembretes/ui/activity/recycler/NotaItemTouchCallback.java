package br.com.regagnani.notaselembretes.ui.activity.recycler;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import br.com.regagnani.notaselembretes.ui.activity.dao.NotaDAO;

public class NotaItemTouchCallback extends ItemTouchHelper.Callback {//essa classe serve para criar a animação de deslize

    private final ListaNotasAdapter adapter;

    public NotaItemTouchCallback(ListaNotasAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int marcacoesDeDeslize = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int marcacoesDeArrastar = ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(marcacoesDeArrastar, marcacoesDeDeslize);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int posicaoInicial = viewHolder.getAdapterPosition();
        int posiçãoFinal = target.getAdapterPosition();
        trocaAPosicaoDasNotasArrastando(posicaoInicial, posiçãoFinal);
        return true;
    }

    private void trocaAPosicaoDasNotasArrastando(int posicaoInicial, int posiçãoFinal) {
        new NotaDAO().troca(posicaoInicial, posiçãoFinal);
        adapter.troca(posicaoInicial, posiçãoFinal);
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int posicaoDoItemDeslizado = viewHolder.getAdapterPosition();
        removeNotaArrastando(posicaoDoItemDeslizado);

    }

    private void removeNotaArrastando(int posicaoDoItemDeslizado) {
        new NotaDAO().remove(posicaoDoItemDeslizado);
        adapter.remove(posicaoDoItemDeslizado);
    }

}
