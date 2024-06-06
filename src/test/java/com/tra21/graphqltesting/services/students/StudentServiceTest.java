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
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class StudentServiceTest {
    @Mock
    StudentRepository studentRepository;
    @Mock
    StudentMapper studentMapper  = Mappers.getMapper(StudentMapper.class);
    @InjectMocks
    StudentService studentService;

    @Test
    void StudentService_getStudents_ReturnStudentDTO() {
        Long studentId = 1L;
        Student studentMock =  Mockito.mock(Student.class);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(studentMock));

        Optional<Student> student = studentRepository.findById(studentId);
        StudentDTO result = studentService.getStudents(studentId);
        StudentDTO studentDTO = studentMapper.mapReverse(studentMock);

        assertTrue(student.isPresent());
        assertSame(studentDTO, result);
    }
    @Test
    void StudentService_getStudents_ReturnExceptionNotFoundEntityWithMessage() {
        //Mock Student with id = 12L
        Long studentId = 12L;
        String message = "Can't find student";

        //Asserting that the result will throw NotFoundException
        NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> studentService.getStudents(studentId));
        assertEquals(message, notFoundException.getMessage());
    }

    @Test
    void StudentService_allStudents_ReturnsStudentContents() {
        Page<Student> students = Mockito.mock(Page.class);
        StudentContents contents = Mockito.mock(StudentContents.class);

        // Configuring the behavior of the studentRepository mock
        // When findAll method of studentRepository is called with any Pageable, it should return the mocked Page<Student>.
        when(studentRepository.findAll(Mockito.any(Pageable.class))).thenReturn(students);
        //Configuring the behavioe of the studentMapper mock
        //When mapPageToStudentContents method of studentMapper is called with any Page<T>, it should return the mocked StudentContents
        when(studentMapper.mapPageToStudentContents(students)).thenReturn(contents);

        // Calling the findAll method of studentService with page number 0 and page size 10
        StudentContents studentContents = studentService.allStudents(new PaginationReq(0, 10));

        // Asserting that the result (studentContents) is not null
        Assertions.assertThat(studentContents).isNotNull();
    }

    @Test
    void StudentService_createStudent_ReturnStudentDTO() {
        CreateStudentDTO createStudentDTO = CreateStudentDTO.builder()
                .firstName("test")
                .lastName("1")
                .age(23)
                .isDeleted(false)
                .courses(new ArrayList<>())
                .build();
        Student student = Mockito.mock(Student.class);
        StudentDTO studentDTOMock = Mockito.mock(StudentDTO.class);

        when(studentMapper.map(createStudentDTO)).thenReturn(student);
        when(studentMapper.mapReverse(student)).thenReturn(studentDTOMock);
        when(studentRepository.save(Mockito.mock(Student.class))).thenReturn(student);

        StudentDTO studentDTO = studentService.createStudent(createStudentDTO);

        assertNotNull(studentDTO);
        assertSame(studentDTOMock, studentDTO);
    }

    @Test
    void StudentService_updateStudent_ReturnStudentDTO() {
        Long studentId = 1L;
        Student studentUpdate = Mockito.mock(Student.class);
        Optional<Student> studentFindById = Mockito.mock(Optional.class);
        StudentDTO studentDTOMock = Mockito.mock(StudentDTO.class);
        Student studentCreateMock = Student.builder()
                .id(studentId)
                .firstName("test")
                .lastName("1")
                .age(23)
                .isDeleted(false)
                .courses(new ArrayList<>())
                .build();
        UpdateStudentDTO updateStudentDTO = UpdateStudentDTO.builder()
                .id(studentId)
                .firstName("test")
                .lastName("1")
                .age(23)
                .isDeleted(false)
                .courses(new ArrayList<>())
                .build();

        when(studentRepository.save(studentCreateMock)).thenReturn(studentUpdate);
        when(studentRepository.findById(studentId)).thenReturn(studentFindById);
        when(studentMapper.mapReverse(studentFindById.orElse(null))).thenReturn(studentDTOMock);
        when(studentRepository.save(Mockito.mock(Student.class))).thenReturn(studentUpdate);

        StudentDTO studentDTO = studentService.updateStudent(studentId, updateStudentDTO);

        assertNotNull(studentDTO);
        assertSame(studentDTOMock, studentDTO);
    }

    @Test
    void StudentService_deleteStudent_ReturnStudentDTO() {
        Long studentId = 1L;
        Optional<Student> student = Mockito.mock(Optional.class);
        Student studentCreateMock = Student.builder()
                .id(studentId)
                .firstName("test")
                .lastName("1")
                .age(23)
                .isDeleted(false)
                .courses(new ArrayList<>())
                .build();

        when(studentRepository.findById(studentId)).thenReturn(student);

        studentRepository.save(studentCreateMock);
        boolean isDeleted = studentService.deleteStudent(studentId);

        assertTrue(isDeleted);
    }
}