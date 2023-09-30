package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class StudentService {


    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String addStudent(Student student) {
        // Validate if student already exists
        if (studentRepository.studentExists(student.getName())) {
            return "Student already exists";
        }
        studentRepository.addStudent(student);
        return "New student added successfully";
    }

    public String addTeacher(Teacher teacher) {
        // Validate if teacher already exists
        if (studentRepository.teacherExists(teacher.getName())) {
            return "Teacher already exists";
        }
        studentRepository.addTeacher(teacher);
        return "New teacher added successfully";
    }

    public String addStudentTeacherPair(String studentName, String teacherName) {
        // Validate if both student and teacher exist
        if (!studentRepository.studentExists(studentName) || !studentRepository.teacherExists(teacherName)) {
            return "Student or Teacher does not exist";
        }
        studentRepository.addStudentTeacherPair(studentName, teacherName);
        return "New student-teacher pair added successfully";
    }

    public Student getStudentByName(String name) {
        return studentRepository.getStudentByName(name);
    }

    public Teacher getTeacherByName(String name) {
        return studentRepository.getTeacherByName(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        return studentRepository.getStudentsByTeacherName(teacher);
    }

    public List<String> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public String deleteTeacherByName(String name) {
        if (!studentRepository.teacherExists(name)) {
            return "Teacher not found";
        }
        studentRepository.deleteTeacherByName(name);
        return name + " removed successfully";
    }

    public String deleteAllTeachers() {
        studentRepository.deleteAllTeachers();
        return "All teachers deleted successfully";
    }
}
