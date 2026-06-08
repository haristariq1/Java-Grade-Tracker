package grade.service;

import grade.model.Student;
import java.util.HashMap;
import java.util.Map;

public class GradeService {
    private Map<String, Student> students = new HashMap<>();
    private int idCounter = 101;

    public Student addStudent(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Student name cannot be empty.");
        String id = "STU" + idCounter++;
        Student s = new Student(name.trim(), id);
        students.put(id, s);
        return s;
    }

    public Student getStudent(String id) {
        Student s = students.get(id);
        if (s == null)
            throw new IllegalArgumentException("Student not found: " + id);
        return s;
    }

    public void addGrade(String studentId, double grade) {
        getStudent(studentId).addGrade(grade);
    }

    public void removeStudent(String studentId) {
        if (!students.containsKey(studentId))
            throw new IllegalArgumentException("Student not found: " + studentId);
        students.remove(studentId);
    }

    public Map<String, Student> getAllStudents() {
        return students;
    }

    public Student getTopStudent() {
        return students.values().stream()
            .max((a, b) -> Double.compare(a.getAverage(), b.getAverage()))
            .orElse(null);
    }
}