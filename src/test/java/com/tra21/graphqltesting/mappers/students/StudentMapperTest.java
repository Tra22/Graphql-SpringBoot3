package com.tra21.graphqltesting.mappers.students;

import com.tra21.graphqltesting.mappers.courses.CourseMapper;
import com.tra21.graphqltesting.models.courses.Course;
import com.tra21.graphqltesting.models.students.Student;
import com.tra21.graphqltesting.payloads.dtos.students.CreateStudentDTO;
import com.tra21.graphqltesting.payloads.dtos.students.StudentDTO;
import com.tra21.graphqltesting.payloads.dtos.students.UpdateStudentDTO;
import com.tra21.graphqltesting.payloads.res.students.StudentContents;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static graphql.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class StudentMapperTest {
    @Mock
    CourseMapper courseMapper;
    @InjectMocks
    StudentMapper studentMapper = Mappers.getMapper(StudentMapper.class);

    @Test
    void StudentMapper_mapStudentDTOToStudent_ReturnStudent() {
        StudentDTO studentDTO = Mockito.mock(StudentDTO.class);
        List<Course> courses = Mockito.mock(List.class);

        when(courseMapper.mapListReverse(Mockito.mock(List.class))).thenReturn(courses);

        Student student = studentMapper.map(studentDTO);

        assertNotNull(student);
    }

    @Test
    void StudentMapper_mapCreateStudentDTOToStudentDTO_ReturnStudent() {
        CreateStudentDTO createStudentDTO = Mockito.mock(CreateStudentDTO.class);
        List<Course> courses = Mockito.mock(List.class);

        when(courseMapper.mapListReverse(Mockito.mock(List.class))).thenReturn(courses);

        Student student = studentMapper.map(createStudentDTO);

        assertNotNull(student);
    }

    @Test
    void StudentMapper_mapUpdateStudentDTOToStudentDTO_ReturnStudent() {
        UpdateStudentDTO updateStudentDTO = Mockito.mock(UpdateStudentDTO.class);
        Student studentFectMock = Mockito.mock(Student.class);
        List<Course> courses = Mockito.mock(List.class);

        when(courseMapper.mapListReverse(Mockito.mock(List.class))).thenReturn(courses);

        Student student = studentMapper.map(studentFectMock, updateStudentDTO);

        assertNotNull(student);
    }

    @Test
    void StudentMapper_mapReverse_ReturnStudentDTO() {
        Student student = Mockito.mock(Student.class);
        List<Course> courses = Mockito.mock(List.class);

        when(courseMapper.mapListReverse(Mockito.mock(List.class))).thenReturn(courses);

        StudentDTO studentDTO  = studentMapper.mapReverse(student);

        assertNotNull(studentDTO);
    }
    @Test
    void StudentMapper_mapListReverse_ReturnListOfStudentDTOSize1() {
        List<Student> students = List.of(Student.builder().build());

        List<StudentDTO>  studentDTOS = studentMapper.mapListReverse(students);

        assertNotNull(studentDTOS);
        assertEquals(1, studentDTOS.size());
    }

    @Test
    void StudentMapper_mapListReverse_ReturnListOfStudentDTOEmptyList() {
        List<Student> students = new ArrayList<>();

        List<StudentDTO>  studentDTOS = studentMapper.mapListReverse(students);

        assertNotNull(studentDTOS);
        assertEquals(0, studentDTOS.size());
    }

    @Test
    void mapPageToStudentContents() {
        Page<Student> students = Mockito.mock(Page.class);
        StudentContents contents = Mockito.mock(StudentContents.class);

        // Calling the findAll method of studentService with page number 0 and page size 10
        StudentContents studentContents = studentMapper.mapPageToStudentContents(students);

        // Asserting that the result (studentContents) is not null
        Assertions.assertThat(studentContents).isNotNull();
    }
}