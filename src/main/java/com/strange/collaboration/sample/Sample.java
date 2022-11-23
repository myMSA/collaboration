package com.strange.collaboration.sample;

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
public class Sample {
    // 현재는 Sample로서 DTO와 Entity를 모두 혼용하나,
    // 실제 코딩에서는 SampleDTO, SampleEntity와 같이 세부분할 예정
    // 주석처리된 건 훗날쓸거듯
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private String subject;
    private String title;
    private String writer;
    private String content;
    private Long readCount=0L;
    private String regDate=LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));;//update 가 필요없나...
//    private LocalDateTime updatedDatetime;
    private String password;
}
