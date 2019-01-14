package br.com.fatec.Model;

public class Obra {
    private int tombo;
    private String dataCriacao;
    private String autor;
    private String descricao;
    private String titulo;
    private String emExposicao;

    public int getTombo() {
        return tombo;
    }
    public void setTombo(int tombo) {
        this.tombo = tombo;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEmExposicao() {
        return emExposicao;
    }
    public void setEmExposicao(String emExposicao) {
        this.emExposicao = emExposicao;
    }
}
