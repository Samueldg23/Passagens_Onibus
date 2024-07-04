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
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "numero", length = 20)
    private String numero;

    @Column(name = "senha", length = 100)
    private String senha;

    @ManyToOne
    @JoinColumn(name = "viagem_id")
    private Viagem viagem;

}
