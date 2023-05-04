package com.example.Librarymanagementsystem.entity;

import com.example.Librarymanagementsystem.enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "card")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CreationTimestamp
    private Date issueDate;
    @UpdateTimestamp
    private Date lastUpdate;
    @Enumerated(EnumType.STRING)
   private CardStatus cardStatus;
   private String validTill;
   @OneToOne
           @JoinColumn
   Student student;
   @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
   List<Book> bookList=new ArrayList<>();
   @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
   List<Transaction>transactionList=new ArrayList<>();

}