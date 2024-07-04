package com.br.passagens.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "onibus")
public class Onibus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "modelo", length = 100, nullable = false)
    private String modelo;

    @Column(name = "placa", length = 20, nullable = false)
    private String placa;

    @Column(name = "atributos", length = 255)
    private String atributos;  // Ar condicionado, leito, etc.

    @Column(name = "quantidade_cadeira")
    private int quantidadeCadeira;

    @JsonIgnore
    @Transient
    private List<Cadeira> cadeiras = new ArrayList<Cadeira>();

    
    public void adicionarCadeiras(int quantidade_cadeira) {
        for (int i = 1; i <= quantidade_cadeira; i++) {
            Cadeira cadeira = new Cadeira(null, i, "vago", null, this);
            cadeiras.add(cadeira);
        }
    }
}
