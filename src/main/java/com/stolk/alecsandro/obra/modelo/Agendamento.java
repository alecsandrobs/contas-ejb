package com.stolk.alecsandro.obra.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "agendamentos")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Agendamento extends EntidadeId implements Serializable {

    @Column
    @NotBlank(message = "{agendamento.email.vazio}")
    @Email(message = "{agendamento.email.invalido}")
    private String email;

    @Column
    @NotBlank(message = "{agendamento.assunto.vazio}")
    private String assunto;

    @Column
    @NotBlank(message = "{agendamento.mensagem.vazio}")
    private String mensagem;

    @Column
    private Boolean enviado;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agendamento that = (Agendamento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(Boolean enviado) {
        this.enviado = enviado;
    }
}
