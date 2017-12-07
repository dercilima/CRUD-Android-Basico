package br.com.dercilima.myfirstapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetalhesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalhes);

        final TextView nomeText = findViewById(R.id.nome_text);
        final TextView idadeText = findViewById(R.id.idade_text);
        final TextView cursoText = findViewById(R.id.curso_text);

        // Extrair o nome do EditText
//        String nome = getIntent().getStringExtra("nome");
        // Setar o nome para o TextView
//        nomeText.setText(nome);

        // Forma abreviada
//        idadeText.setText(getIntent().getStringExtra("idade"));
//        cursoText.setText(getIntent().getStringExtra("curso"));

        long id = getIntent().getLongExtra("id", 0);

        // Instanciar o banco de dados
        DbHelper helper = new DbHelper(this);
        SQLiteDatabase database = helper.getReadableDatabase();

        String[] columns = {"NOME", "IDADE", "CURSO"};
        String selection = "_id = ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = database.query("ALUNOS", columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            // Extrair dados do cursor
            String nome = cursor.getString(cursor.getColumnIndex("NOME"));
            String idade = cursor.getString(cursor.getColumnIndex("IDADE"));
            String curso = cursor.getString(cursor.getColumnIndex("CURSO"));

            // Exibir os dados na tela
            nomeText.setText(nome);
            idadeText.setText(idade);
            cursoText.setText(curso);
        }

        // Fechar o cursor
        cursor.close();

        // Fechar o banco de dados
        helper.close();

    }
}
