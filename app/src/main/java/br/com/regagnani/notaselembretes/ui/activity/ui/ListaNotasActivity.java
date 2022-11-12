package br.com.regagnani.notaselembretes.ui.activity.ui;

import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);
        setTitle(TITULO_APPBAR);


        List<Nota> todasAsNotas = GeraNotasDeExemplo();

        configuraORecyclerView(todasAsNotas);


    }


    private List<Nota> GeraNotasDeExemplo() {

        NotaDAO dao = new NotaDAO();

        for (int i = 0; i <= 100000; i++) {
            Nota notaTeste = new Nota("Primeira nota" + i, "descrição" + i);
            dao.insere(notaTeste);
        }

        List<Nota> todasAsNotas = dao.todos();
        return todasAsNotas;
    }

    private void configuraORecyclerView(List<Nota> todasAsNotas) {
        RecyclerView listaNotas = findViewById(R.id.listaNotasRecyclerView);

        ConfiguraAdapter(todasAsNotas, listaNotas);
        ConfiguraManager(listaNotas);
    }

    private void ConfiguraAdapter(List<Nota> todasAsNotas, RecyclerView listaNotas) {
        ListaNotasAdapter adapter = new ListaNotasAdapter(this, todasAsNotas);
        listaNotas.setAdapter(adapter);
    }

    private void ConfiguraManager(RecyclerView listaNotas) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        listaNotas.setLayoutManager(manager);
    }
}