package br.com.regagnani.notaselembretes.ui.activity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.regagnani.notaselembretes.R;
import br.com.regagnani.notaselembretes.ui.activity.model.Nota;

public class InsereNotasActivity extends AppCompatActivity {

    public static final String ALTERA_NOTA = "Altera nota";
    public static final String TITULO_APPBAR = "Insere nova nota";
    public static final String CHAVE_NOTA = "nota";

    private int posicaoDoItemClicado = -1;// o -1 é pra deixar um valor invalido como padrão ja que 0 é uma posição

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere_notas);
        setTitle(TITULO_APPBAR);

        Intent verificaSeRecebeuDados = getIntent();
        if (verificaSeRecebeuDados.hasExtra("nota") && verificaSeRecebeuDados.hasExtra("posicao")) {
            setTitle(ALTERA_NOTA);
            Nota notaRecebida = (Nota) verificaSeRecebeuDados.getSerializableExtra("nota");
            posicaoDoItemClicado = verificaSeRecebeuDados.getIntExtra("posicao", -1);
            preencheOsCamposComOsDadosDaNotaQueSeraEditada(notaRecebida);
        }
    }

    private void preencheOsCamposComOsDadosDaNotaQueSeraEditada(Nota notaRecebida) {
        TextView titulo = findViewById(R.id.formulario_nota_titulo);
        titulo.setText(notaRecebida.getTitulo());
        TextView descricao = findViewById(R.id.formulario_nota_descricao);
        descricao.setText(notaRecebida.getDescricao());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_salva_nota, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_formulario_salva_nota_botao) {
            Nota notaCriada = pegaOConteudoPreenchidoNasEditTextECriaUmaNota();
            enviaANotaCriadaDeVoltaPelaIntent(notaCriada);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void enviaANotaCriadaDeVoltaPelaIntent(Nota notaCriada) {
        Intent resultadoInsercao = new Intent();
        resultadoInsercao.putExtra(CHAVE_NOTA, notaCriada);
        resultadoInsercao.putExtra("posicao", posicaoDoItemClicado);
        setResult(2, resultadoInsercao);
    }

    @NonNull
    private Nota pegaOConteudoPreenchidoNasEditTextECriaUmaNota() {
        EditText tituloPreenchido = findViewById(R.id.formulario_nota_titulo);
        EditText descricaoPreenchida = findViewById(R.id.formulario_nota_descricao);
        Nota notaCriada = new Nota(tituloPreenchido.getText().toString(), descricaoPreenchida.getText().toString());
        return notaCriada;
    }
}