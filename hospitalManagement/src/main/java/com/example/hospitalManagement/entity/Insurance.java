package com.example.hospitalManagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    @Column(nullable = false,unique = true,length = 50)
     private String policyNumber;

    @Column(nullable = false,length = 50)
    private String provider;

    @Column(nullable = false)
    private LocalDate validUntil;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "insurance") // inverse side -> kyu ki ye side foreign key ko dictate nai kar rahi
    private Patient patient;
}
