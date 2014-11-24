package sample.dao;

import sample.basededados.BancoDeDadosEmMemoria;
import sample.modelo.Carro;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Ton on 22/10/2014.
 */
public class CarroDao {

    private BancoDeDadosEmMemoria banco = BancoDeDadosEmMemoria.getInstance();

    public void salvar(Carro carro) {

        banco.tbCarros.add(carro);

        System.out.println("salvando carro: " + carro);
    }

    public Carro pesquisaPorCodigo(String chave) {

        List<Carro> carros = banco.tbCarros;

        for (Carro carro : carros) {
            if (carro.getCodigo().equals(chave)) {
                return carro;
            }
        }

        return null;
    }

    public void remover(Carro pessoa) {
        banco.tbCarros.remove(pessoa);
    }

    public void alterarCarro(Carro carroSelecionado) {

        banco.tbCarros.stream().forEach(new Consumer<Carro>() {
            @Override
            public void accept(Carro carro) {
                if (carro.getCodigo().equals(carroSelecionado.getCodigo())) {
                    carro.setmodelo(carroSelecionado.getModelo());
                    carro.setMarca(carroSelecionado.getMarca());
                    carro.setQtdportas(carroSelecionado.getQtdportas());
                    carro.setCor(carroSelecionado.getcor());
                    carro.setProprietario(carroSelecionado.getProprietario());
                    carro.setPlaca(carroSelecionado.getPlaca());
                }
            }
        });

    }
}
