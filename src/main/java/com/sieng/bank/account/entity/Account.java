package com.sieng.bank.account.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="accounts")
@Data
public class Account {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long accountNumber;
 @ManyToOne
 @JoinColumn(name = "customerId")
 private Customer customerId; 
 private String accountType;
 private String branchAddress;
 private LocalDate createDate;
}
