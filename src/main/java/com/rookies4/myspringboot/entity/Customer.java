package com.rookies4.myspringboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Table(name="customers")
@Getter @Setter
@DynamicUpdate //수정시 모든 컬럼이 아닌 특정 컬럼만 수정 가능하게 하는 어노테이션
public class Customer {
    //Primary Key, PK값을 Persistence Provider가 결정해라
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 유니크한 값을 가져야하고, null값을 허용하지 않는다
    @Column(unique = true, nullable = false)
    private String customerId;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false,updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt = LocalDateTime.now();
}
