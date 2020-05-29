package com.stolk.alecsandro.obra.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "contas")
public class Conta extends EntidadeId {

    @NotBlank(message = "{conta.banco.vazio}")
    private String banco;

    @NotBlank(message = "{conta.agencia.vazio}")
    private String agencia;

    @NotBlank(message = "{conta.numero.vazio}")
    private String numero;

    public Conta() {
    }

    public Conta(@NotBlank(message = "{conta.banco.vazio}") String banco, @NotBlank(message = "{conta.agencia.vazio}") String agencia, @NotBlank(message = "{conta.numero.vazio}") String numero) {
        this.banco = banco;
        this.agencia = agencia;
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta that = (Conta) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
