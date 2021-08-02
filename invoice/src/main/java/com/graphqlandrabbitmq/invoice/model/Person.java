package com.graphqlandrabbitmq.invoice.model;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull("Nome deve ser informado")
    private String name;

    @NotNull("CPF deve ser informado")
    private String cpf;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private List<Demand> demandList;
}
