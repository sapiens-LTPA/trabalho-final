package br.sapiens.models;

import java.util.Date;
import java.util.List;

public class Aluno {

    private int id;
    private String nome;
    private Date dataNascimento;
    private List<Matricula> matriculado;
    private Curso curso;

}
