package com.seminar.rest.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="info")
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    int id;
    @Column(name="name")
    String name;
    @Column(name="year")
    int year;
    @Column(name="category")
    String category;
}
