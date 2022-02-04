package com.qa.clientsspringboot.domain;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "dob")
    private LocalDate dob;

    @Transient
    private int age;

    public Client() {
    }

    public Client(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Client(Long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public int getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public Client setAge(int age) {
        this.age = age;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Client setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Client setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Client setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Client setDob(LocalDate dob) {
        this.dob = dob;
        return this;
    }


}
