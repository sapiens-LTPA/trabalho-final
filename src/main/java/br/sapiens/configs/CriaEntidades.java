package br.sapiens.configs;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriaEntidades {

    public CriaEntidades(Connection con) throws SQLException {
        String endereco = "" +
                "CREATE TABLE `endereco` (\n" +
                "  `id` bigint auto_increment,\n" +
                "  `descricao` varchar(200),\n" +
                "  `logradouro` varchar(200),\n" +
                "  PRIMARY KEY (`id`)\n" +
                ");\n";

        String matricula = "CREATE TABLE `Matricula` (\n" +
                " disciplina int, \n " +
                " aluno int, \n " +
                " periodo  varchar(200),\n " +
                " PRIMARY KEY (periodo, disciplina, aluno) \n" +

                ");\n";


        Statement statement = con.createStatement();
        statement.execute(endereco);
        statement.execute(matricula);
        System.out.println("Tabela matricula criada com sucesso. ");
        statement.close();
    }

}
