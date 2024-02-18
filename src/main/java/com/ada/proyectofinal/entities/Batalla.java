package com.ada.proyectofinal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "batalla")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Batalla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
