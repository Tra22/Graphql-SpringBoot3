package com.tra21.graphqltesting.services.students;

import com.tra21.graphqltesting.exception.NotFoundException;
import com.tra21.graphqltesting.mappers.students.StudentMapper;
import com.tra21.graphqltesting.models.students.Student;
import com.tra21.graphqltesting.payloads.dtos.students.CreateStudentDTO;
import com.tra21.graphqltesting.payloads.dtos.students.StudentDTO;
import com.tra21.graphqltesting.payloads.dtos.students.UpdateStudentDTO;
import com.tra21.graphqltesting.payloads.req.PaginationReq;
import com.tra21.graphqltesting.payloads.res.students.StudentContents;
import com.tra21.graphqltesting.repositories.students.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    @Override
    public StudentDTO getStudents(Long studentId) {
        return studentMapper.mapReverse(studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException("Can't find student")));
    }

    @Override
    public StudentContents allStudents(PaginationReq paginationReq) {
        PageRequest pageRequest = PageRequest.of(paginationReq.getPage(), paginationReq.getSize(), Sort.by("id"));
        Page<Student> students = studentRepository.findAll(pageRequest);
        return studentMapper.mapPageToStudentContents(students);
    }

    @Override
    public StudentDTO createStudent(CreateStudentDTO createStudentDTO) {
        Student student = studentMapper.map(createStudentDTO);
        studentRepository.save(student);
        return studentMapper.mapReverse(student);
    }

    @Override
    public StudentDTO updateStudent(Long studentId, UpdateStudentDTO updateStudentDTO) {
        Student studentOri = studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException("Can't find student"));
        Student student = studentMapper.map(studentOri, updateStudentDTO);
        studentRepository.save(student);
        return studentMapper.mapReverse(student);
    }

    @Override
    public boolean deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException("Can't find student"));
        studentRepository.delete(student);
        return true;
    }
}
