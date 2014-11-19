package sample.dao;

import sample.basededados.BancoDeDadosEmMemoria;
import sample.modelo.Pessoa;

import java.util.List;

/**
 * Created by Ton on 22/10/2014.
 */
public class PessoaDao {

    private BancoDeDadosEmMemoria banco = BancoDeDadosEmMemoria.getInstance();

    public void salvar(Pessoa pessoa) {

        banco.tbPessoas.add(pessoa);

        System.out.println("salvando pessoa: " + pessoa);
    }

    public Pessoa pesquisaPorCodigo(String chave) {

        List<Pessoa> pessoas = banco.tbPessoas;

        for (Pessoa pessoa : pessoas) {
            if (pessoa.getCodigo().equals(chave)) {
                return pessoa;
            }
        }

        return null;
    }
}
