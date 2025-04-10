// model/Account.java
package com.banking.system.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private String accountType;
    private double balance;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}