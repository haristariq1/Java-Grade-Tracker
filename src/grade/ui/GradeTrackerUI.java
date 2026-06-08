package grade.ui;

import grade.model.Student;
import grade.service.GradeService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GradeTrackerUI extends JFrame {

    private GradeService gradeService = new GradeService();
    private DefaultTableModel tableModel;

    private JTextField nameField, studentIdField, gradeField, removeIdField;
    private JLabel statusLabel, topStudentLabel;

    public GradeTrackerUI() {
        setTitle("Student Grade Tracker");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        add(buildTopPanel(), BorderLayout.NORTH);
        add(buildCenterPanel(), BorderLayout.CENTER);
        add(buildStatusBar(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel buildTopPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Add Student"));
        panel.setBackground(new Color(230, 245, 255));

        nameField = new JTextField();
        panel.add(labeledField("Student Name:", nameField));

        JButton addBtn = new JButton("Add Student");
        addBtn.setBackground(new Color(0, 102, 204));
        addBtn.setForeground(Color.WHITE);
        addBtn.addActionListener(e -> handleAddStudent()); // Event Handling

        JPanel btnPanel = new JPanel();
        btnPanel.add(addBtn);
        panel.add(btnPanel);

        topStudentLabel = new JLabel("Top Student: —");
        topStudentLabel.setForeground(new Color(0, 100, 0));
        panel.add(topStudentLabel);

        return panel;
    }

    private JPanel buildCenterPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
        panel.add(buildGradePanel());
        panel.add(buildRemovePanel());
        panel.add(buildTablePanel());
        return panel;
    }

    private JPanel buildGradePanel() {
        JPanel panel = new JPanel(new GridLayout(6, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Add Grade"));
        panel.setBackground(new Color(240, 255, 240));

        studentIdField = new JTextField();
        gradeField     = new JTextField();

        panel.add(new JLabel("Student ID:"));
        panel.add(studentIdField);
        panel.add(new JLabel("Grade (0–100):"));
        panel.add(gradeField);

        JButton gradeBtn = new JButton("Add Grade");
        gradeBtn.setBackground(new Color(0, 153, 51));
        gradeBtn.setForeground(Color.WHITE);
        gradeBtn.addActionListener(e -> handleAddGrade()); // Event Handling
        panel.add(gradeBtn);

        return panel;
    }

    private JPanel buildRemovePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Remove Student"));
        panel.setBackground(new Color(255, 240, 240));

        removeIdField = new JTextField();
        panel.add(new JLabel("Student ID:"));
        panel.add(removeIdField);

        JButton removeBtn = new JButton("Remove Student");
        removeBtn.setBackground(new Color(204, 0, 0));
        removeBtn.setForeground(Color.WHITE);
        removeBtn.addActionListener(e -> handleRemoveStudent()); // Event Handling
        panel.add(removeBtn);

        return panel;
    }

    private JPanel buildTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("All Students"));

        String[] columns = {"ID", "Name", "Grades", "Average", "Letter"};
        tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> refreshTable()); // Event Handling
        panel.add(refreshBtn, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel buildStatusBar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel("Welcome to Student Grade Tracker");
        statusLabel.setForeground(new Color(0, 100, 0));
        panel.add(statusLabel);
        return panel;
    }

    // ── Event Handlers ────────────────────────────────────────────────────────
    private void handleAddStudent() {
        try {
            Student s = gradeService.addStudent(nameField.getText());
            setStatus("✅ Student added: " + s.getStudentId(), true);
            nameField.setText("");
            refreshTable();
        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage()); // Exception Handling
        }
    }

    private void handleAddGrade() {
        try {
            String id    = studentIdField.getText().trim();
            double grade = parseDouble(gradeField.getText());
            gradeService.addGrade(id, grade);
            setStatus("✅ Grade added for " + id, true);
            gradeField.setText("");
            refreshTable();
            updateTopStudent();
        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage()); // Exception Handling
        }
    }

    private void handleRemoveStudent() {
        try {
            String id = removeIdField.getText().trim();
            gradeService.removeStudent(id);
            setStatus("✅ Student removed: " + id, true);
            removeIdField.setText("");
            refreshTable();
            updateTopStudent();
        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage()); // Exception Handling
        }
    }

    // ── Helpers (Refactored) ──────────────────────────────────────────────────
    private double parseDouble(String text) {
        try {
            return Double.parseDouble(text.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number. Enter a value like 85 or 92.5");
        }
    }

    private JPanel labeledField(String label, JTextField field) {
        JPanel p = new JPanel(new BorderLayout(5, 5));
        p.add(new JLabel(label), BorderLayout.WEST);
        p.add(field, BorderLayout.CENTER);
        p.setOpaque(false);
        return p;
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Student s : gradeService.getAllStudents().values()) {
            tableModel.addRow(new Object[]{
                s.getStudentId(),
                s.getName(),
                s.getGrades().toString(),
                String.format("%.2f", s.getAverage()),
                s.getLetterGrade()
            });
        }
    }

    private void updateTopStudent() {
        Student top = gradeService.getTopStudent();
        topStudentLabel.setText(top == null ? "Top Student: —"
            : "Top Student: " + top.getName() + " (" + String.format("%.1f", top.getAverage()) + ")");
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
        setStatus("❌ " + msg, false);
    }

    private void setStatus(String msg, boolean ok) {
        statusLabel.setText(msg);
        statusLabel.setForeground(ok ? new Color(0, 128, 0) : Color.RED);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GradeTrackerUI::new);
    }
}