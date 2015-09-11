package seminfo.br.agenda.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import seminfo.br.agenda.R;
import seminfo.br.agenda.model.Contato;

public class AtualizarContatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_contatos);

        final Contato contato = new Contato();
        final Intent intent = getIntent();
        if (intent != null) {
            contato.setNome(intent.getStringExtra("NOME"));
            contato.setEmail(intent.getStringExtra("EMAIL"));
            contato.setTelefone(intent.getStringExtra("TELEFONE"));
            contato.setId(intent.getIntExtra("ID", -1));
        }

        final EditText cpNome = (EditText) findViewById(R.id.cpNome);
        final EditText cpTelefone = (EditText) findViewById(R.id.cpTelefone);
        final EditText cpEmail = (EditText) findViewById(R.id.cpEmail);

        cpNome.setText(contato.getNome());
        cpTelefone.setText(contato.getTelefone());
        cpEmail.setText(contato.getEmail());

        Button btnAtualizar = (Button) findViewById(R.id.btnAtualizar);
        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("NOME", cpNome.getText().toString());
                intent1.putExtra("EMAIL", cpEmail.getText().toString());
                intent1.putExtra("TELEFONE", cpTelefone.getText().toString());
                intent1.putExtra("ID", contato.getId());
                setResult(Activity.RESULT_OK, intent1);
                finish();
            }
        });
    }

}
