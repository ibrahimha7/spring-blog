package com.example.blog.AUTH;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table
@ToString
@RequiredArgsConstructor
@AllArgsConstructor

public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String role;

}
