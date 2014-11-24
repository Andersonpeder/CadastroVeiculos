package sample.modelo;

import java.io.Serializable;

/**
 * Created by Ton on 22/10/2014.
 */
public class Carro implements Serializable{

    private String codigo;

    private String modelo;

    private String marca;

    private String qtdportas;

    private String cor;

    private String proprietario;

    private String placa;


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setmodelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getQtdportas() {
        return qtdportas;
    }

    public void setQtdportas(String qtdportas) {
        this.qtdportas = qtdportas;
    }

    public String getcor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Carro carro = (Carro) o;

        if (!codigo.equals(carro.codigo)) return false;
        if (!modelo.equals(carro.modelo)) return false;
        if (!marca.equals(carro.marca)) return false;
        if (!qtdportas.equals(carro.qtdportas)) return false;
        if (!cor.equals(carro.cor)) return false;
        if (!proprietario.equals(carro.proprietario)) return false;
        if (!placa.equals(carro.placa)) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = codigo.hashCode();
        result = 31 * result + modelo.hashCode();
        result = 31 * result + marca.hashCode();
        result = 31 * result + qtdportas.hashCode();
        result = 31 * result + cor.hashCode();
        result = 31 * result + proprietario.hashCode();
        result = 31 * result + placa.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "codigo='" + codigo + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                "qtdportas='" + qtdportas + '\'' +
                ", cor='" + cor + '\'' +
                ", proprietario='" + proprietario + '\'' +
                ", placa='" + placa + '\'' +
                '}';
    }

    public Carro(String codigo, String modelo, String marca, String qtdportas, String cor, String proprietario, String placa) {

        this.codigo = codigo;
        this.modelo = modelo;
        this.marca = marca;
        this.qtdportas = qtdportas;
        this.cor = cor;
        this.proprietario = proprietario;
        this.placa = placa;
    }

    public Carro() {

    }
}
