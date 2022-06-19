package com.ubt.hospitalmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "uuid", updatable = false)
    private String uuid;

    private String email;
    private String password;
    private boolean active;
    private String roles;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "experiences", joinColumns = @JoinColumn(name = "doctor_id", nullable = false))
    private List<String> experiences;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "diseases", joinColumns = @JoinColumn(name = "patient_id", nullable = false))
    private List<String> diseases;


    @OneToMany(fetch = FetchType.LAZY)
    List<WorkTime> workingDays;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String mobileNumber;



}
