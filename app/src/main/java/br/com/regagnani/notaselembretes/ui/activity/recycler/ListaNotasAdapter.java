package br.com.regagnani.notaselembretes.ui.activity.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.regagnani.notaselembretes.R;
import br.com.regagnani.notaselembretes.ui.activity.model.Nota;

public class ListaNotasAdapter extends RecyclerView.Adapter<ListaNotasAdapter.ListaNotaHolder> {

    private final List<Nota> lista;
    private final Context context;

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


    class ListaNotaHolder extends RecyclerView.ViewHolder {

        private final TextView titulo;
        private final TextView descricao;


        public ListaNotaHolder(@NonNull View itemView) {
            super(itemView);
            this.titulo = itemView.findViewById(R.id.item_nota_titulo);
            this.descricao = itemView.findViewById(R.id.item_nota_descricao);
        }

        public void vincula(Nota nota) {
            this.titulo.setText(nota.getTitulo());
            this.descricao.setText(nota.getDescricao());

        }
    }
}
