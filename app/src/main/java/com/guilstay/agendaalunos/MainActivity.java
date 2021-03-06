package com.guilstay.agendaalunos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;

import android.view.MenuItem;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import dao.AlunoDao;
import models.Aluno;

public class MainActivity extends AppCompatActivity {

    private ListView listaAlunos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaAlunos = findViewById(R.id.lista_alunos);

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int possition, long id) {
                Aluno aluno =  (Aluno) listaAlunos.getItemAtPosition(possition);
                Toast.makeText(MainActivity.this, "Aluno " + aluno.getNome() + "Clicado", Toast.LENGTH_SHORT).show();
            }
        });

        Button novoAluno = (Button) findViewById(R.id.novo_aluno);
        novoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaiParaNovoAluno = new Intent(MainActivity.this, NovoAluno.class);
                startActivity(vaiParaNovoAluno);
            }
        });

        registerForContextMenu(listaAlunos);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo)  {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(info.position);

                AlunoDao dao = new AlunoDao(MainActivity.this);
                dao.deleta(aluno);
                dao.close();
                carregaLista();
                Toast.makeText(MainActivity.this, "Aluno deletado com sucesso", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    public void carregaLista() {
        AlunoDao dao = new AlunoDao(this);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();


        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        listaAlunos.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }


}
