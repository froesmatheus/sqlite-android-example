package seminfo.br.agenda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import seminfo.br.agenda.model.Contato;


/**
 * Created by matheus on 11/09/15.
 */
public class DB {
    private static final String NOME_TABELA = "Contatos";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_EMAIL = "email";
    private static final String COLUNA_TELEFONE = "telefone";

    private DBCore dbCore;
    private SQLiteDatabase db;

    private Context ctx;

    public DB(Context ctx) {
        this.ctx = ctx;
        dbCore = new DBCore(this.ctx);
        db = dbCore.getWritableDatabase();
    }


    public void insert(Contato contato) {
        ContentValues ctValues = new ContentValues();

        ctValues.put(COLUNA_NOME, contato.getNome());
        ctValues.put(COLUNA_TELEFONE, contato.getTelefone());
        ctValues.put(COLUNA_EMAIL, contato.getEmail());

        db.insert(NOME_TABELA, null, ctValues);
    }

    public void update(Contato contato) {
        ContentValues ctValues = new ContentValues();

        ctValues.put(COLUNA_NOME, contato.getNome());
        ctValues.put(COLUNA_TELEFONE, contato.getTelefone());
        ctValues.put(COLUNA_EMAIL, contato.getEmail());

        db.update(NOME_TABELA, ctValues, "_id = ?", new String[]{"" + contato.getId()});
    }

    public void delete(int _id) {
        db.delete(NOME_TABELA, "_id = ?", new String[]{"" + _id});
    }

    public List<Contato> listarContatos() {
        List<Contato> list = new ArrayList<>();
        String[] colunas = {"_id", COLUNA_NOME, COLUNA_TELEFONE, COLUNA_EMAIL};

        Cursor cursor = db.query(NOME_TABELA, colunas, null, null, null, null, null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            do {
                Contato contato = new Contato();

                contato.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id"))));
                contato.setNome(cursor.getString(cursor.getColumnIndex(COLUNA_NOME)));
                contato.setTelefone(cursor.getString(cursor.getColumnIndex(COLUNA_TELEFONE)));
                contato.setEmail(cursor.getString(cursor.getColumnIndex(COLUNA_EMAIL)));

                list.add(contato);
            } while (cursor.moveToNext());
        }

        return list;
    }


}
