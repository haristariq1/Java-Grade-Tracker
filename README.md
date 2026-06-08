# 🎓 Student Grade Tracker

A Java Swing desktop application for managing student records and tracking academic performance. Built as a demonstration of core software engineering concepts including layered architecture, event handling, exception handling, and unit testing.

---

## 📸 Features

- **Add Students** — Register students with auto-generated IDs (STU101, STU102, ...)
- **Record Grades** — Add multiple grades (0–100) per student
- **Auto Grading** — Automatically calculates average and letter grade (A / B / C / D / F)
- **Remove Students** — Delete student records from the system
- **Top Student** — Live display of the highest-performing student
- **Full Table View** — See all students, grades, averages, and letter grades at a glance
- **Input Validation** — Handles invalid input and edge cases gracefully

---

## 🏗️ Project Structure

```
GradeTracker/
├── src/
│   ├── grade.model/
│   │   └── Student.java          # Student data + grade logic
│   ├── grade.service/
│   │   └── GradeService.java     # Business logic layer
│   ├── grade.ui/
│   │   └── GradeTrackerUI.java   # Java Swing GUI
│   └── grade.test/
│       └── GradeServiceTest.java # JUnit 5 unit tests
└── README.md
```

---

## 💡 Concepts Demonstrated

| Concept | Where |
|---|---|
| **Event Handling** | Button listeners in `GradeTrackerUI` trigger all operations |
| **Exception Handling** | `try/catch` blocks handle invalid grades, empty names, missing IDs |
| **Code Refactoring** | Clean 3-layer design — Model, Service, UI — with reusable helper methods |
| **Unit Testing** | 8 JUnit 5 tests covering all business logic in `GradeServiceTest` |
| **Git & GitHub** | Full version history with meaningful commit messages |
| **OOP Principles** | Encapsulation, separation of concerns, single responsibility |

---

## 🧪 Unit Tests (8 total)

| Test | What it checks |
|---|---|
| `testAddStudent_ValidName` | Student created successfully |
| `testAddStudent_EmptyName_ThrowsException` | Empty name rejected |
| `testAddGrade_ValidGrade` | Grade recorded, average correct |
| `testAddGrade_OutOfRange_ThrowsException` | Grade > 100 rejected |
| `testLetterGrade_A` | Average ≥ 90 returns "A" |
| `testLetterGrade_F` | Average < 60 returns "F" |
| `testRemoveStudent_Valid` | Student removed successfully |
| `testGetTopStudent_ReturnsHighestAverage` | Correct top student returned |

---

## ⚙️ Setup Instructions

### Requirements
- JDK 11 or higher
- Eclipse IDE for Java Developers

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/haristariq1/Java-Grade-Tracker.git
   ```

2. Open Eclipse → **File** → **Open Projects from File System**

3. Select the cloned folder → click **Finish**

4. Add JUnit 5:
   - Right-click project → **Build Path** → **Add Libraries** → **JUnit** → **JUnit 5** → Finish

5. Run the app:
   - Right-click `GradeTrackerUI.java` → **Run As** → **Java Application**

6. Run tests:
   - Right-click `GradeServiceTest.java` → **Run As** → **JUnit Test**

---

## 🖥️ How to Use

1. **Add a student** — Enter name in the top field → click **Add Student** → note the generated ID
2. **Add grades** — Enter the student ID + a grade (0–100) → click **Add Grade**
3. **View results** — Click **Refresh** to see updated averages and letter grades
4. **Remove a student** — Enter student ID → click **Remove Student**
5. **Top student** — Updates automatically after every grade entry

---

## 👨‍💻 Author

**Haris Tariq**  
[github.com/haristariq1](https://github.com/haristariq1)
