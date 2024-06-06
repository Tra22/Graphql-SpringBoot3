package com.tra21.graphqltesting.controllers;

import com.tra21.graphqltesting.payloads.dtos.students.StudentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureGraphQlTester
class StudentControllerTest {
    @Autowired
    private GraphQlTester tester;


    @Test
    void getStudents() {
    }

    @Test
    void allStudents() {
    }

    @Test
    void createStudent() {
        String document = "mutation CreateStudent {\n" +
                "    createStudent(\n" +
                "        createStudentDTO: {\n" +
                "            firstName: \"test\"\n" +
                "            lastName: \"1\"\n" +
                "            age: 23\n" +
                "            isDeleted: false\n" +
                "            courses: []\n" +
                "        }\n" +
                "    ) {\n" +
                "        id\n" +
                "        firstName\n" +
                "        lastName\n" +
                "        age\n" +
                "        isDeleted\n" +
                "        courses {\n" +
                "            id\n" +
                "            name\n" +
                "        }\n" +
                "    }\n" +
                "}\n";

        StudentDTO studentDTO =
                tester.document(document)
                    .execute()
                    .path("createStudent")
                    .entity(StudentDTO.class)
                    .get();
        assertNotNull(studentDTO);
    }

    @Test
    void updateStudent() {
    }

    @Test
    void deleteStudent() {
    }
}