package com.facens.academia.dto.response;

import java.time.LocalDateTime;

public class AlunoResponse {
    private Long id;
    private String nome;
    private String email;
    private Integer idade;
    private String telefone;
    private String situacao;
    private LocalDateTime dataCadastro;
    private String nomePlano;

    public AlunoResponse() {}

    public AlunoResponse(Long id, String nome, String email, Integer idade,
                         String telefone, String situacao,
                         LocalDateTime dataCadastro, String nomePlano) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.telefone = telefone;
        this.situacao = situacao;
        this.dataCadastro = dataCadastro;
        this.nomePlano = nomePlano;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public Integer getIdade() { return idade; }
    public String getTelefone() { return telefone; }
    public String getSituacao() { return situacao; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public String getNomePlano() { return nomePlano; }
}