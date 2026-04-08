package com.example.hospitalManagement.entity;

import com.example.hospitalManagement.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@ToString
@Getter
@Setter

@Table(name = "patient",
         uniqueConstraints ={
//       {@UniqueConstraint(name = "unique_patient_email",columnNames = {"email"}),
       @UniqueConstraint(name = "unique_patient_name_birthdate",columnNames = {"name","birthDate"})
       },
        indexes = {
                @Index(name = "idx_patient_birth_date",columnList = "birthDate")
        }
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , length = 40)
    private String name;

   // @ToString.Exclude
    private LocalDate birthDate;

    @Column(unique = true)
    private String email;

    private String gender;

    @OneToOne
    @MapsId // patient ki id user ki id ke barabar
    private User user;

    @Column(name="created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;


    @OneToOne(cascade ={ CascadeType.MERGE,CascadeType.ALL},orphanRemoval = true)
    @JoinColumn(name = "patient_insurance_id") // owning side
    private Insurance insurance;

    @OneToMany(mappedBy = "patient",cascade = {CascadeType.REMOVE},orphanRemoval = true,fetch = FetchType.EAGER) // non owning side still cascade
    private List<Appointment> appointments;
}
//Why use {} ?
//Because columnNames expects an array (list) of columns.

//How does Hibernate know which column?
//
//Because JPA maps fields → columns.
//
//Example:
//
//private String email;
//
//Hibernate automatically creates column:
//
//email
//
//But if you change the column name:
//
//@Column(name = "patient_email")
//private String email;
//
//Now the database column is:
//
//patient_email
//
//So in @UniqueConstraint you must use:
//
//columnNames = {"patient_email"}
//
//Because constraints are applied on database columns, not Java fields.
