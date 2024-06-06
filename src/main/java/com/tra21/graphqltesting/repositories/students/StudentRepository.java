package com.tra21.graphqltesting.repositories.students;

import com.tra21.graphqltesting.models.students.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
