package com.guilstay.agendaalunos;

import android.widget.EditText;
import android.widget.RatingBar;

import models.Aluno;

public class FormularioHelper {
    private EditText nome;
    private EditText endereco;
    private EditText telefone;
    private EditText site;
    private RatingBar nota;


    public  FormularioHelper(NovoAluno novoAluno){

        nome =  novoAluno.findViewById(R.id.formulario_nome);
        endereco =  novoAluno.findViewById(R.id.formulario_endereco);
        telefone =  novoAluno.findViewById(R.id.formulario_telefone);
        site =  novoAluno.findViewById(R.id.formulario_site);
        nota = novoAluno.findViewById(R.id.formulario_nota);

    }

    public Aluno getAluno(){
        Aluno aluno = new Aluno();
        aluno.setNome(nome.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setNota(Double.valueOf(nota.getProgress()));

        return aluno;
    }

}
