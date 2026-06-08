package grade.test;

import grade.model.Student;
import grade.service.GradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GradeServiceTest {

    private GradeService service;

    @BeforeEach
    void setUp() { service = new GradeService(); }

    @Test
    void testAddStudent_ValidName() {
        Student s = service.addStudent("Ali");
        assertNotNull(s);
        assertEquals("Ali", s.getName());
    }

    @Test
    void testAddStudent_EmptyName_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
            () -> service.addStudent(""));
    }

    @Test
    void testAddGrade_ValidGrade() {
        Student s = service.addStudent("Sara");
        service.addGrade(s.getStudentId(), 85);
        assertEquals(85.0, s.getAverage());
    }

    @Test
    void testAddGrade_OutOfRange_ThrowsException() {
        Student s = service.addStudent("Usman");
        assertThrows(IllegalArgumentException.class,
            () -> service.addGrade(s.getStudentId(), 110));
    }

    @Test
    void testLetterGrade_A() {
        Student s = service.addStudent("Fatima");
        service.addGrade(s.getStudentId(), 95);
        assertEquals("A", s.getLetterGrade());
    }

    @Test
    void testLetterGrade_F() {
        Student s = service.addStudent("Hamza");
        service.addGrade(s.getStudentId(), 50);
        assertEquals("F", s.getLetterGrade());
    }

    @Test
    void testRemoveStudent_Valid() {
        Student s = service.addStudent("Zara");
        service.removeStudent(s.getStudentId());
        assertThrows(IllegalArgumentException.class,
            () -> service.getStudent(s.getStudentId()));
    }

    @Test
    void testGetTopStudent_ReturnsHighestAverage() {
        Student s1 = service.addStudent("Bilal");
        Student s2 = service.addStudent("Noor");
        service.addGrade(s1.getStudentId(), 70);
        service.addGrade(s2.getStudentId(), 90);
        assertEquals(s2.getStudentId(), service.getTopStudent().getStudentId());
    }
}