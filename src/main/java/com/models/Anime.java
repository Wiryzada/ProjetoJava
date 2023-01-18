package com.models;

public class Anime {
    private int id;
    private String titulo;
    private int ano;
    private String descricao;
    private String avaliacao;
    private boolean status;

    public Anime() {
    }

    public Anime(String titulo, int ano, String descricao, String avaliacao, boolean status) {
        this.titulo = titulo;
        this.ano = ano;
        this.descricao = descricao;
        this.avaliacao = avaliacao;
        this.status = status;
    }

    public Anime(int id, String titulo, int ano, String descricao, String avaliacao, boolean status) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        this.descricao = descricao;
        this.avaliacao = avaliacao;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String estado = isStatus()? "Assistido." : "Não assistido." + "\n";
        return "ID: " + id + " Titulo: " + titulo + " Ano: " + ano + " Descrição: " + descricao + " Avaliação: " + avaliacao + " Status: " + estado;
    }
}
