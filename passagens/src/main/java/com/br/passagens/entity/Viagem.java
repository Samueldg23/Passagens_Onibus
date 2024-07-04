package com.br.passagens.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "viagem")
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "lugar_partida", length = 100)
    private String lugarPartida;

    @Column(name = "lugar_destino", length = 100)
    private String lugarDestino;

    @Column(name = "horario_partida", length = 50)
    private String horarioPartida;

    @Column(name = "horario_chegada", length = 50)
    private String horarioChegada;

    @Column(name = "valor")
    private double valor;

    @Column(name = "descricao", length = 255)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "onibus_id", nullable = false)
    private Onibus onibus;
}
