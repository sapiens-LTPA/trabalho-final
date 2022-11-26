package daos;

import br.sapiens.configs.ConexaoSingleton;
import br.sapiens.configs.CriaEntidades;
import br.sapiens.daos.EnderecoDao;
import br.sapiens.models.Endereco;
import br.sapiens.models.LogradouroEnum;
import junit.framework.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EnderecoDaoTest {


    EnderecoDao enderecoDao = new EnderecoDao();

    public EnderecoDaoTest() throws SQLException {

    }

    @BeforeAll
    public static void init() throws SQLException {
        new CriaEntidades(new ConexaoSingleton().getConnection());
    }


    @Test
    public void save() throws SQLException, ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000");

        Endereco endereco = new Endereco(null,"Descrição", LogradouroEnum.Rua, date);
        Endereco enderecoSalvo = enderecoDao.save(endereco);
        Assert.assertTrue(enderecoSalvo.getId() != null);
        Assert.assertTrue(enderecoSalvo.getData().getTime() == 946699200000l);
    }


    @Test
    public void saveAll() throws SQLException, ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000");
        Endereco endereco1 = new Endereco(null,"Descrição - 1", LogradouroEnum.Rua, date);
        Endereco endereco2 = new Endereco(null,"Descrição - 2", LogradouroEnum.Rua, date);
        Iterable<Endereco> enderecoSalvo = enderecoDao.saveAll(List.of(endereco1, endereco2));
        enderecoSalvo.forEach(it -> {
            Assert.assertTrue(it.getId() != null);
            Assert.assertTrue(it.getData().getTime() == 946699200000l);
        });
    }

    @Test
    public void findById() throws SQLException {
        Endereco endereco = new Endereco(null,"Descrição", LogradouroEnum.Rua, new Date());
        Endereco enderecoSalvo = enderecoDao.save(endereco);
        Assert.assertTrue(enderecoSalvo.getId() != null);
        Integer id = enderecoSalvo.getId();
        Endereco enderecoBanco = enderecoDao.findById(id).get();
        Assert.assertTrue(endereco != enderecoBanco);
        Assert.assertTrue( enderecoBanco.getId() == id);
    }


    @Test
    public void update() throws SQLException {
        Endereco endereco = new Endereco(null,"Descrição", LogradouroEnum.Rua, new Date());
        Endereco enderecoSalvo = enderecoDao.save(endereco);
        Integer id = endereco.getId();
        enderecoSalvo.setDescricao("Outra descricao");
        enderecoDao.save(endereco);
        Endereco enderecoBanco = enderecoDao.findById(id).get();
        Assert.assertTrue(enderecoBanco.getDescricao().equals("Outra descricao"));
        Assert.assertTrue(enderecoBanco != endereco);
    }
}
