package br.com.regagnani.notaselembretes.ui.activity.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.com.regagnani.notaselembretes.R;
import br.com.regagnani.notaselembretes.ui.activity.dao.NotaDAO;
import br.com.regagnani.notaselembretes.ui.activity.model.Nota;

public class InsereNotasActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Insere nota";
    public static final String CHAVE_NOTA = "nota";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere_notas);
        setTitle(TITULO_APPBAR);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_salva_nota,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_formulario_salva_nota_botao){
            Nota notaCriada = pegaOConteudoPreenchidoNasEditTextECriaUmaNota();
            enviaANotaCriadaDeVoltaPelaIntent(notaCriada);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void enviaANotaCriadaDeVoltaPelaIntent(Nota notaCriada) {
        Intent resultadoInsercao = new Intent();
        resultadoInsercao.putExtra(CHAVE_NOTA, notaCriada);
        setResult(2,resultadoInsercao);
    }

    @NonNull
    private Nota pegaOConteudoPreenchidoNasEditTextECriaUmaNota() {
        EditText tituloPreenchido = findViewById(R.id.formulario_nota_titulo);
        EditText descricaoPreenchida = findViewById(R.id.formulario_nota_descricao);
        Nota notaCriada = new Nota(tituloPreenchido.getText().toString(), descricaoPreenchida.getText().toString());
        return notaCriada;
    }
}