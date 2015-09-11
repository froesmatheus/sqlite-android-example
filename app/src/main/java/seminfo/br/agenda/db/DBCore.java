package seminfo.br.agenda.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by matheus on 10/09/15.
 */
public class DBCore extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "AGENDA";
    private static final int VERSAO_BANCO = 1;

    public DBCore(Context ctx) {
        super(ctx, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Contatos (_id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, " +
                "telefone TEXT NOT NULL, email TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE Contatos;");
        onCreate(db);
    }
}
