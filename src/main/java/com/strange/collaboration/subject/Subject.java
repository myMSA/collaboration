package com.strange.collaboration.subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "SUBJECT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String explanation;
    private String writer;
    private String regDate=LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
    private LocalDateTime updatedDatetime;
}
