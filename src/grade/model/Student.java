package grade.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String studentId;
    private List<Double> grades;

    public Student(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
        this.grades = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getStudentId() { return studentId; }
    public List<Double> getGrades() { return grades; }

    public void addGrade(double grade) {
        if (grade < 0 || grade > 100)
            throw new IllegalArgumentException("Grade must be between 0 and 100.");
        grades.add(grade);
    }

    public double getAverage() {
        if (grades.isEmpty()) return 0.0;
        double sum = 0;
        for (double g : grades) sum += g;
        return sum / grades.size();
    }

    public String getLetterGrade() {
        double avg = getAverage();
        if (avg >= 90) return "A";
        if (avg >= 80) return "B";
        if (avg >= 70) return "C";
        if (avg >= 60) return "D";
        return "F";
    }

    @Override
    public String toString() {
        return studentId + " | " + name + " | Avg: " + String.format("%.2f", getAverage()) + " | " + getLetterGrade();
    }
}