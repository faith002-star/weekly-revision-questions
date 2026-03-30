import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentRegistration extends JFrame implements ActionListener {
    // GUI Components
    private JTextField txtId, txtName, txtCourse, txtYear;
    private JComboBox<String> cbDept;
    private JRadioButton rbMale, rbFemale, rbOther;
    private JCheckBox chkHostel;
    private JTextArea displayArea;
    private JButton btnRegister, btnClear;
    private ButtonGroup genderGroup;

    public StudentRegistration() {
        // 1. JFrame Setup
        setTitle("Student Registration");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // --- INPUT PANEL (GridLayout) ---
        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 5, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Student ID:"));
        txtId = new JTextField();
        inputPanel.add(txtId);

        inputPanel.add(new JLabel("Full Name:"));
        txtName = new JTextField();
        inputPanel.add(txtName);

        inputPanel.add(new JLabel("Department:"));
        String[] depts = {"IT", "Business", "Engineering", "Science"};
        cbDept = new JComboBox<>(depts);
        inputPanel.add(cbDept);

        inputPanel.add(new JLabel("Course:"));
        txtCourse = new JTextField();
        inputPanel.add(txtCourse);

        inputPanel.add(new JLabel("Year of Study:"));
        txtYear = new JTextField();
        inputPanel.add(txtYear);

        inputPanel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        rbOther = new JRadioButton("Other");
        genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);
        genderGroup.add(rbOther);
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);
        genderPanel.add(rbOther);
        inputPanel.add(genderPanel);

        inputPanel.add(new JLabel("Accommodation:"));
        chkHostel = new JCheckBox("Hostel Required");
        inputPanel.add(chkHostel);

        // --- BUTTON PANEL ---
        btnRegister = new JButton("Register");
        btnClear = new JButton("Clear");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnRegister);
        buttonPanel.add(btnClear);
        inputPanel.add(buttonPanel);

        // --- DISPLAY AREA (BorderLayout South) ---
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Add panels to frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // 3. Register Action Listeners
        btnRegister.addActionListener(this);
        btnClear.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegister) {
            handleRegistration();
        } else if (e.getSource() == btnClear) {
            clearFields();
        }
    }

    private void handleRegistration() {
        // 4. Input Validation
        if (txtId.getText().isEmpty() || txtName.getText().isEmpty() || 
            txtCourse.getText().isEmpty() || txtYear.getText().isEmpty() ||
            genderGroup.getSelection() == null) {
            
            JOptionPane.showMessageDialog(this, "Please fill in all fields and select a gender.", 
                                          "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Gather Gender Data
        String gender = rbMale.isSelected() ? "Male" : rbFemale.isSelected() ? "Female" : "Other";
        String hostel = chkHostel.isSelected() ? "Yes" : "No";

        // Display results
        String result = String.format(
            "--- Registration Successful ---\n" +
            "ID: %s\nName: %s\nDept: %s\nCourse: %s\nYear: %s\nGender: %s\nHostel: %s",
            txtId.getText(), txtName.getText(), cbDept.getSelectedItem(),
            txtCourse.getText(), txtYear.getText(), gender, hostel
        );
        
        displayArea.setText(result);
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtCourse.setText("");
        txtYear.setText("");
        cbDept.setSelectedIndex(0);
        genderGroup.clearSelection();
        chkHostel.setSelected(false);
        displayArea.setText("");
    }

    public static void main(String[] args) {
        // Run GUI in the Event Dispatch Thread
        SwingUtilities.invokeLater(StudentRegistration::new);
    }
}