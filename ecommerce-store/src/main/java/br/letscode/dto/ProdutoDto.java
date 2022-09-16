package br.letscode.dto;

public class ProdutoDto {

    private long id;
    private String nome;
    private Double precoVenda;
    private int quantidade;
    private String descricao;

    public ProdutoDto(long id, String nome, Double precoVenda, int quantidade, String descricao) {
        this.id = id;
        this.nome = nome;
        this.precoVenda = precoVenda;
        this.quantidade = quantidade;
        this.descricao = descricao;
    }

    public ProdutoDto(String nome, Double precoVenda, int quantidade, String descricao) {
        this.nome = nome;
        this.precoVenda = precoVenda;
        this.quantidade = quantidade;
        this.descricao = descricao;
    }

    public ProdutoDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
