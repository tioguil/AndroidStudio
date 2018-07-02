package com.guilstay.agendaalunos;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import dao.AlunoDao;
import models.Aluno;

public class NovoAluno extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_aluno);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:

                Aluno aluno = new FormularioHelper(this).getAluno();
                Toast.makeText(NovoAluno.this, "Aluno " + aluno.getNome() +" salvo!", Toast.LENGTH_SHORT).show();
                AlunoDao dao = new AlunoDao(this);
                dao.insere(aluno);
                dao.close();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
