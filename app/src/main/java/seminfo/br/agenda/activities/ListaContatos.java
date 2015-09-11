package seminfo.br.agenda.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import seminfo.br.agenda.db.DB;
import seminfo.br.agenda.model.Contato;
import seminfo.br.agenda.R;

public class ListaContatos extends AppCompatActivity {

    private ListView listView;
    private List<Contato> list;
    private ArrayAdapter<Contato> adapter;
    private DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);

        listView = (ListView) findViewById(R.id.list);

        db = new DB(this);

        list = db.listarContatos();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contatos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Contato contato = list.get(info.position);
        switch (item.getItemId()) {

            case R.id.editar:
                Intent intent = new Intent(this, AtualizarContatos.class);
                intent.putExtra("NOME", contato.getNome());
                intent.putExtra("EMAIL", contato.getEmail());
                intent.putExtra("TELEFONE", contato.getTelefone());
                intent.putExtra("ID", contato.getId());
                startActivityForResult(intent, 1);
                return true;
            case R.id.excluir:
                db.delete(contato.getId());
                list.remove(contato);
                adapter.notifyDataSetChanged();
                return true;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {

            if (data != null) {
                Contato contato = new Contato();

                contato.setNome(data.getStringExtra("NOME"));
                contato.setEmail(data.getStringExtra("EMAIL"));
                contato.setTelefone(data.getStringExtra("TELEFONE"));
                contato.setId(data.getIntExtra("ID", -1));

                db.update(contato);
                list = db.listarContatos();
                adapter.notifyDataSetChanged();
                recreate();
            }
        }

    }


}
