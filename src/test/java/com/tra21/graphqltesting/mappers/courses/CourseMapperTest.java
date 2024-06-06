package com.tra21.graphqltesting.mappers.courses;

import com.tra21.graphqltesting.models.courses.Course;
import com.tra21.graphqltesting.payloads.dtos.courses.CourseDTO;
import com.tra21.graphqltesting.payloads.res.Pagination;
import com.tra21.graphqltesting.repositories.courses.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CourseMapperTest {
    @Mock
    CourseRepository courseRepository;
    @InjectMocks
    CourseMapper courseMapper = Mappers.getMapper(CourseMapper.class);

    @Test
    void CourseMapper_map_ReturnCourse() {
        CourseDTO courseDTO = CourseDTO.builder()
                .id(1L)
                .name("Test Course")
                .build();

        Course course =courseMapper.map(courseDTO);

        assertNotNull(course);
    }
    @Test
    void CourseMapper_map_ReturnNullCourse() {
        Course course = courseMapper.map(null);

        assertNull(course);
    }

    @Test
    void CourseMapper_mapReverse_ReturnCourseDTO() {
        Course course = Course.builder()
                .id(1L)
                .name("Test Course")
                .build();

        CourseDTO courseDTO =courseMapper.mapReverse(course);

        assertNotNull(courseDTO);
    }
    @Test
    void CourseMapper_mapReverse_ReturnNullCourseDTO() {
        CourseDTO courseDTO =courseMapper.mapReverse(null);

        assertNull(courseDTO);
    }
    @Test
    void CourseMapper_mapListReverse_ReturnListOfCourseDTOWithSize1() {
        List<Course> coursesMock = List.of(Course.builder().build());

        List<CourseDTO> courseDTOSMock = courseMapper.mapListReverse(coursesMock);

        assertNotNull(courseDTOSMock);
        assertEquals(1, courseDTOSMock.size());
    }
    @Test
    void CourseMapper_mapListReverse_ReturnThrowListEmpty() {
        List<Course> coursesMock = new ArrayList<>();

        List<CourseDTO> courseDTOS = courseMapper.mapListReverse(coursesMock);

        assertNotNull(courseDTOS);
        assertEquals(0, courseDTOS.size());
    }

    @Test
    void mapIdsToCourseList() {
        List<Long> ids = List.of(1L, 2L);
        List<Course> courses = Mockito.mock(List.class);

        when(courseRepository.findAllById(ids)).thenReturn(courses);

        List<Course> coursesResult = courseMapper.mapIdsToCourseList(ids);

        assertNotNull(coursesResult);
        assertEquals(0, coursesResult.size());
    }
}