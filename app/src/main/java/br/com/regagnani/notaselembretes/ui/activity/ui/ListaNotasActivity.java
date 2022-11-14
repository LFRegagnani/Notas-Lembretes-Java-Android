package br.com.regagnani.notaselembretes.ui.activity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import br.com.regagnani.notaselembretes.R;
import br.com.regagnani.notaselembretes.ui.activity.dao.NotaDAO;
import br.com.regagnani.notaselembretes.ui.activity.model.Nota;
import br.com.regagnani.notaselembretes.ui.activity.recycler.ListaNotasAdapter;

public class ListaNotasActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Notas & Lembretes";
    private ListaNotasAdapter adapter;
    private List<Nota> todasAsNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);
        setTitle(TITULO_APPBAR);

        pegaAListaDeNotas();
        configuraORecyclerView(todasAsNotas);
        configuraCliqueParaInserirNota();

    }

    private void pegaAListaDeNotas() {
        NotaDAO dao = new NotaDAO();
        todasAsNotas = dao.todos();
    }

    @Override
    protected void onResume() {
        NotaDAO dao = new NotaDAO();
        todasAsNotas.clear();
        todasAsNotas.addAll(dao.todos());
        adapter.notifyDataSetChanged();
        super.onResume();

    }

    private void configuraCliqueParaInserirNota() {
        TextView inserirNota = findViewById(R.id.lista_notas_insere_nota);
        inserirNota.setOnClickListener(clica -> {
            Intent mudaTelaParaInserirNota = new Intent(ListaNotasActivity.this, InsereNotasActivity.class);
            startActivityForResult(mudaTelaParaInserirNota, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == 2 && data.hasExtra("nota")) {
            Nota notaRecebida = (Nota) data.getSerializableExtra("nota");
            new NotaDAO().insere(notaRecebida);
            adapter.adiciona(notaRecebida);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void configuraORecyclerView(List<Nota> todasAsNotas) {
        RecyclerView listaNotas = findViewById(R.id.listaNotasRecyclerView);

        ConfiguraAdapter(todasAsNotas, listaNotas);
        ConfiguraManager(listaNotas);
    }

    private void ConfiguraAdapter(List<Nota> todasAsNotas, RecyclerView listaNotas) {
        adapter = new ListaNotasAdapter(this, todasAsNotas);
        listaNotas.setAdapter(adapter);
    }

    private void ConfiguraManager(RecyclerView listaNotas) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        listaNotas.setLayoutManager(manager);
    }
}