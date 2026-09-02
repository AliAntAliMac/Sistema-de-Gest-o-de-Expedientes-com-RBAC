//package com.isced.expedientes.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "expediente")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Expediente {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String titulo;
//
//    @Column(columnDefinition = "TEXT")
//    private String descricao;
//
//    private LocalDateTime dataCriacao;
//
//    private String status; // Ex: PENDENTE, APROVADO, REJEITADO
//
//    @PrePersist
//    protected void onCreate() {
//        this.dataCriacao = LocalDateTime.now();
//        if (this.status == null) {
//            this.status = "PENDENTE";
//        }
//    }
//}
//
//
//



package com.isced.expedientes.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "expedientes")
public class Expediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "informe o numero de processo")
    private String numeroProcesso;
    private String assunto;
    private String remetente;
    private LocalDateTime dataRegisto = LocalDateTime.now();
    private String estado; // REGISTADO, TRAMITADO, ARQUIVADO

    @ManyToOne
    @JoinColumn(name = "utilizador_id")
    private Utilizador criador;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumeroProcesso() { return numeroProcesso; }
    public void setNumeroProcesso(String numeroProcesso) { this.numeroProcesso = numeroProcesso; }
    public String getAssunto() { return assunto; }
    public void setAssunto(String assunto) { this.assunto = assunto; }
    public String getRemetente() { return remetente; }
    public void setRemetente(String remetente) { this.remetente = remetente; }
    public LocalDateTime getDataRegisto() { return dataRegisto; }
    public void setDataRegisto(LocalDateTime dataRegisto) { this.dataRegisto = dataRegisto; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Utilizador getCriador() { return criador; }
    public void setCriador(Utilizador criador) { this.criador = criador; }
}
