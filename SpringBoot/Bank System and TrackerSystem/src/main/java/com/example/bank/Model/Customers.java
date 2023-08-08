package com.example.bank.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customers {
    private String id;
    private String username;
    private int balance;
}
