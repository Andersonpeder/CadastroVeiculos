package sample.logica;

import sample.dao.CarroDao;
import sample.modelo.Carro;

/**
 * Created by Ton on 22/10/2014.
 */
public class CarroBusinnes {

    private CarroDao dao = new CarroDao();

    public void addNovoCarro(String codigo, String modelo, String marca, String qtdportas, String cor,
                             String proprietario, String placa) {



        //executar validações, testes de nulidade
        Carro carro = new Carro();

        carro.setCodigo(codigo);
        carro.setmodelo(modelo);
        carro.setMarca(marca);
        carro.setQtdportas(qtdportas);
        carro.setCor(cor);
        carro.setProprietario(proprietario);
        carro.setPlaca(placa);


        dao.salvar(carro);

    }

    public Carro pesquisaPorCodigo(String chave) throws Exception {
        String nova = chave.trim();

        if (nova == null || nova.equals("")) {
            throw new Exception("Campo de pesquisa nulo, inválido");
        } else {
            return dao.pesquisaPorCodigo(chave);
        }

    }

    public void excluirCarro(Carro carro) {

        dao.remover(carro);
    }

    public void alterarCarro(Carro carro) {
        dao.alterarCarro(carro);
    }
}
