package sample.basededados;

import sample.modelo.Carro;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ton on 22/10/2014.
 *
 * Padrão Singleton --> padrão de projeto
 */
public class BancoDeDadosEmMemoria {

    // tabela carros
    public List<Carro> tbCarros = new ArrayList<Carro>();

    private static BancoDeDadosEmMemoria bancoDeDadosEmMemoria;

    //construtor privado não roda o comando new
    private BancoDeDadosEmMemoria() {

        tbCarros.add( new Carro("01","Corolla","Toyota","04","Prata","Anderson","AAA 2525"));
        tbCarros.add( new Carro("02","Cruze","Chevrolet","04","Preto","Joselito","BBB 1010"));
        tbCarros.add( new Carro("03","Omega","Chevrolet","04","Branco","Abel","CCC 1212"));
        tbCarros.add( new Carro("04","Clio","Renault","02","Prata","Anderson","ABC 1234"));
        tbCarros.add( new Carro("05","Fusca","Volks","02","Azul","ANA","DFA 4321"));
        tbCarros.add( new Carro("06","147","FIAT","02","Amarelo","Pedro","ABC 8974"));
        tbCarros.add( new Carro("07","Chevette","Chevrolet","02","Prata","Anderson","DCS 2356"));
        tbCarros.add( new Carro("08","Captiva","Chevrolet","04","Rosa","Alfredo","AFC 4562"));
        tbCarros.add( new Carro("09","Uno","Fiat","04","Branco","Anderson","ASZ 1547"));

    }

    //padrão singleton
    public static BancoDeDadosEmMemoria getInstance() {

        if (bancoDeDadosEmMemoria == null) {
            bancoDeDadosEmMemoria = new BancoDeDadosEmMemoria();
        }

        return bancoDeDadosEmMemoria;
    }

}
