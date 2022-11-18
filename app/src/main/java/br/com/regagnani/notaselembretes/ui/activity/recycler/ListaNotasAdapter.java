package br.com.regagnani.notaselembretes.ui.activity.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import br.com.regagnani.notaselembretes.R;
import br.com.regagnani.notaselembretes.ui.activity.model.Nota;

public class ListaNotasAdapter extends RecyclerView.Adapter<ListaNotasAdapter.ListaNotaHolder> {



    private final List<Nota> lista;
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public ListaNotasAdapter(Context context, List<Nota> lista) {
        this.lista = lista;
        this.context = context;
    }


    @NonNull
    @Override
    public ListaNotaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_nota, parent, false);
        return new ListaNotaHolder(viewCriada);

    }

    @Override
    public void onBindViewHolder(ListaNotaHolder holder, int position) {
        Nota nota = lista.get(position);
        holder.vincula(nota);

    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

    public void remove(int posicaoDoItemDeslizado) {
        this.lista.remove(posicaoDoItemDeslizado);
        notifyItemRemoved(posicaoDoItemDeslizado);
    }

    public void altera(int posicaoRecebida, Nota notaAlteradaRecebida) {
        lista.add(posicaoRecebida,notaAlteradaRecebida);
        notifyItemChanged(posicaoRecebida,notaAlteradaRecebida);
    }

    public void troca(int posicaoInicial,int posiçãoFinal) {
        Collections.swap(lista,posicaoInicial,posiçãoFinal);
        notifyItemMoved(posicaoInicial,posiçãoFinal);
    }

    //OUTRA CLASSE
    class ListaNotaHolder extends RecyclerView.ViewHolder {

        private final TextView titulo;
        private final TextView descricao;
        private Nota nota;

        public ListaNotaHolder(@NonNull View itemView) {
            super(itemView);
            this.titulo = itemView.findViewById(R.id.item_nota_titulo);
            this.descricao = itemView.findViewById(R.id.item_nota_descricao);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.OnItemClick(nota,getAdapterPosition());
                }
            });

        }

        public void vincula(Nota nota) {
            this.titulo.setText(nota.getTitulo());
            this.descricao.setText(nota.getDescricao());
            this.nota = nota;
        }
    }

    public void adiciona(Nota nota) {
        lista.add(nota);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
