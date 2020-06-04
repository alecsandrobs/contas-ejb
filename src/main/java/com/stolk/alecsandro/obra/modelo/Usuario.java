package com.stolk.alecsandro.obra.modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario extends EntidadeId {

    @NotBlank(message = "{usuario.nome.vazio}")
    private String nome;

    @NotBlank(message = "{usuario.apelido.vazio}")
    private String apelido;

    @NotBlank(message = "{usuario.senha.vazio}")
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<SystemRole> roles = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
