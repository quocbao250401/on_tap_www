package com.example.www_on_tap.entity;

import com.example.www_on_tap.enums.EmployeeStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "fullname")
    private String full_name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "status")
    private EmployeeStatus status;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "dob")
    private LocalDate dob;
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Order> lstOrder;
}
