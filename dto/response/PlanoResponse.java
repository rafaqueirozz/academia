package com.facens.academia.dto.response;

import java.math.BigDecimal;

public class PlanoResponse {
    private Long id;
    private String nome;
    private String modalidade;
    private BigDecimal valorMensal;
    private Boolean ativo;

    public PlanoResponse() {}

    public PlanoResponse(Long id, String nome, String modalidade,
                         BigDecimal valorMensal, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.modalidade = modalidade;
        this.valorMensal = valorMensal;
        this.ativo = ativo;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getModalidade() { return modalidade; }
    public BigDecimal getValorMensal() { return valorMensal; }
    public Boolean getAtivo() { return ativo; }
}