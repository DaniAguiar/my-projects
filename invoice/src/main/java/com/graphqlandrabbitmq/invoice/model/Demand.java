package com.graphqlandrabbitmq.invoice.model;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Table(name = "demand")
public class Demand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull("Nome da ordem não pode ser nulo")
    private String name;

    @NotNull("Preço total não pode ser nulo")
    private Double price;

    @NotNull("Uma pessoa deve estar vinculada a ordem")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;
}
