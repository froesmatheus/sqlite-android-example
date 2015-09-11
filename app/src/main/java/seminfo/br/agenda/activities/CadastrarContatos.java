package seminfo.br.agenda.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import seminfo.br.agenda.db.DB;
import seminfo.br.agenda.R;
import seminfo.br.agenda.model.Contato;

public class CadastrarContatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_contatos);


        Button button = (Button) findViewById(R.id.btnSalvar);

        final EditText cpNome = (EditText) findViewById(R.id.cpNome);
        final EditText cpTelefone = (EditText) findViewById(R.id.cpTelefone);
        final EditText cpEmail = (EditText) findViewById(R.id.cpEmail);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contato contato = new Contato();

                contato.setTelefone(cpTelefone.getText().toString());
                contato.setNome(cpNome.getText().toString());
                contato.setEmail(cpEmail.getText().toString());

                DB db = new DB(CadastrarContatos.this);

                db.insert(contato);
                finish();
            }
        });
    }
}
