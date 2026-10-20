package com.ranim.filrouge.entity;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private boolean done;
}
