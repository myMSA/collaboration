package com.strange.collaboration.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "SAMPLE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String regDate=LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
    private LocalDateTime updatedDatetime;
}
