package com.sbs.sample.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity  // DB Table을 만들기 위한 어노테이션
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor  // 매개변수가 없는 생성자를 자동으로 추가
public class Member {
    @Id   // Primary Key 역학을 함
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 생성전략
    @Column(name = "member_id")
    private Long id;

    @Column(length = 100)  // 이름 필드의 길이 제한
    private String name;

    @Column(nullable = false)  // null을 허용하지 않음
    private String pwd;

    @Column(unique = true, length = 150)
    private String email;

    @Column(length = 255)
    private String image;

    private LocalDateTime regDate;  // 회원 가입 시간

    @PrePersist // DB에 INSERT 되기 전에 실행되는 메서드
    public void prePersist() {
        this.regDate = LocalDateTime.now();
    }
}
