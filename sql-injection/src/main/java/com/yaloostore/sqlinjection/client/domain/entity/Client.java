package com.yaloostore.sqlinjection.client.domain.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "clients")
public class Client {


    @Id
    private Long clientId;
    private String loginId;
    private String pwd;


}
