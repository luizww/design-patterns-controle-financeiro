package br.com.loth.financeiro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Lancamento {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private TipoLancamento tipo;
    private String categoria;
    private LocalDate data;

    public Lancamento(Long id, String descricao, BigDecimal valor, TipoLancamento tipo,
                      String categoria, LocalDate data) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.categoria = categoria;
        this.data = data;
    }

    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
    public BigDecimal getValor() { return valor; }
    public TipoLancamento getTipo() { return tipo; }
    public String getCategoria() { return categoria; }
    public LocalDate getData() { return data; }
}
