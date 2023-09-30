package com.driver;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {
    private final Map<String, Student> students = new HashMap<>();
    private final Map<String, Teacher> teachers = new HashMap<>();
    private final Map<String, String> studentTeacherPairs = new HashMap<>();



    public void addStudent(Student student) {
        students.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {
        teachers.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String studentName, String teacherName) {
        studentTeacherPairs.put(studentName, teacherName);
    }

    public Student getStudentByName(String name) {
        return students.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teachers.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacherName) {
        return studentTeacherPairs.entrySet().stream()
                .filter(entry -> entry.getValue().equals(teacherName))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<String> getAllStudents() {
        return students.keySet().stream().collect(Collectors.toList());
    }

    public boolean studentExists(String studentName) {
        return students.containsKey(studentName);
    }

    public boolean teacherExists(String teacherName) {
        return teachers.containsKey(teacherName);
    }

    public void deleteTeacherByName(String teacherName) {
        teachers.remove(teacherName);
        // Remove associated student-teacher pairs
        studentTeacherPairs.entrySet().removeIf(entry -> entry.getValue().equals(teacherName));
    }

    public void deleteAllTeachers() {
        teachers.clear();
        // Remove all student-teacher pairs
        studentTeacherPairs.clear();
    }
}
