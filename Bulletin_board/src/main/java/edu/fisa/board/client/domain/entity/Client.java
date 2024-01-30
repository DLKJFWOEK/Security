package edu.fisa.board.client.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;

@Table(name = "clients")
@Entity
@Getter
public class Client {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String pwd;


}
