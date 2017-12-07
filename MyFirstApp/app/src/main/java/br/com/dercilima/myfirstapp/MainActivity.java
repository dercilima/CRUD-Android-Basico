package br.com.dercilima.myfirstapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText nomeEdit;
    private EditText idadeEdit;
    private EditText cursoEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomeEdit = findViewById(R.id.nome_edit);
        idadeEdit = findViewById(R.id.idade_edit);
        cursoEdit = findViewById(R.id.curso_edit);

        Button salvarButton = findViewById(R.id.salvar_button);
        salvarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Abrir a tela de DetalhesActivity
//                Intent intent = new Intent(MainActivity.this, DetalhesActivity.class);
//                intent.putExtra("nome", nomeEdit.getText().toString());
//                intent.putExtra("idade", idadeEdit.getText().toString());
//                intent.putExtra("curso", cursoEdit.getText().toString());
//                startActivity(intent);

                salvar();

            }
        });

    }

    private void salvar() {
        // Instanciar o banco de dados
        DbHelper helper = new DbHelper(this);
        SQLiteDatabase database = helper.getWritableDatabase();

        // Recuperar as informações dos edits
        String nome = nomeEdit.getText().toString();
        String idade = idadeEdit.getText().toString();
        String curso = cursoEdit.getText().toString();

        // Montar os dados no ContentValues
        ContentValues values = new ContentValues();
        values.put("NOME", nome);
        values.put("IDADE", idade);
        values.put("CURSO", curso);

        // Salvar no banco dados
        long id = database.insertOrThrow("ALUNOS", null, values);

        // Sempre que abrir o banco de dados, deve fechá-lo
        helper.close();

        // Abrir a tela de detalhes
        Intent intent = new Intent(this, DetalhesActivity.class);
        // Anexar o id do registro na intent
        intent.putExtra("id", id);
        startActivity(intent);

    }
}
