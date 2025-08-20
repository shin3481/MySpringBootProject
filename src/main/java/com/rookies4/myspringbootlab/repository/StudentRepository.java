package com.rookies4.myspringbootlab.repository;

import com.rookies4.myspringboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//StudentRepository 인터페이스
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    //Student의 PK로 StudentDetail정보를 조회하는 Query Method
    Optional<Student> findByStudentNumber(String studentNumber);
    // FETCH JOIN을 사용하여 1개의 Query 만 생성이 되도록 처리함
    @Query("SELECT s FROM Student s JOIN FETCH s.studentDetail WHERE s.id = :id")
    Optional<Student> findByIdWithStudentDetail(@Param("id") Long studentId);
    //학번의 중복체크를 위한 메서드
    boolean existsByStudentNumber(String studentNumber);
}