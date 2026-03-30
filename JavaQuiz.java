import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaQuiz extends JFrame implements ActionListener {
    // Quiz Data
    private String[][] questions = {
        {"Which keyword is used to create a subclass in Java?", "extends", "implements", "inherits", "import", "extends"},
        {"What is the default value of a boolean variable?", "true", "false", "null", "0", "false"},
        {"Which method is the entry point of a Java program?", "start()", "init()", "main()", "run()", "main"},
        {"Which package contains the Scanner class?", "java.lang", "java.io", "java.util", "java.net", "java.util"},
        {"Which of these is not a primitive data type?", "int", "double", "boolean", "String", "String"}
    };

    private int currentQuestion = 0;
    private int score = 0;
    private int timeLeft = 60;
    private Timer countdownTimer;

    // GUI Components
    private JLabel lblQuestion, lblTimer, lblScore;
    private JRadioButton[] options = new JRadioButton[4];
    private ButtonGroup group;
    private JButton btnNext;

    public JavaQuiz() {
        setTitle("Java Quiz");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));

        // --- TOP PANEL (Timer & Score) ---
        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        lblTimer = new JLabel("Time Left: 60s", SwingConstants.CENTER);
        lblTimer.setFont(new Font("Arial", Font.BOLD, 16));
        lblScore = new JLabel("Score: 0", SwingConstants.CENTER);
        topPanel.add(lblTimer);
        topPanel.add(lblScore);
        add(topPanel, BorderLayout.NORTH);

        // --- CENTER PANEL (Question & Options) ---
        JPanel centerPanel = new JPanel(new GridLayout(6, 1));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        
        lblQuestion = new JLabel("Question text goes here");
        lblQuestion.setFont(new Font("Arial", Font.BOLD, 14));
        centerPanel.add(lblQuestion);

        group = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            group.add(options[i]);
            centerPanel.add(options[i]);
        }
        add(centerPanel, BorderLayout.CENTER);

        // --- BOTTOM PANEL (Next Button) ---
        btnNext = new JButton("Next Question");
        btnNext.addActionListener(this);
        add(btnNext, BorderLayout.SOUTH);

        // Initialize Timer (1000ms delay)
        countdownTimer = new Timer(1000, e -> {
            timeLeft--;
            lblTimer.setText("Time Left: " + timeLeft + "s");
            if (timeLeft <= 0) {
                handleNext(); // Auto-advance if time runs out
            }
        });

        loadQuestion();
        setVisible(true);
    }

    private void loadQuestion() {
        if (currentQuestion < questions.length) {
            lblQuestion.setText((currentQuestion + 1) + ". " + questions[currentQuestion][0]);
            for (int i = 0; i < 4; i++) {
                options[i].setText(questions[currentQuestion][i + 1]);
                options[i].setSelected(false);
            }
            group.clearSelection();
            timeLeft = 60;
            lblTimer.setText("Time Left: 60s");
            countdownTimer.restart();
        } else {
            showFinalResults();
        }
    }

    private void handleNext() {
        // Check answer
        String correctAnswer = questions[currentQuestion][5];
        for (JRadioButton rb : options) {
            if (rb.isSelected() && rb.getText().equals(correctAnswer)) {
                score++;
            }
        }
        
        lblScore.setText("Score: " + score);
        currentQuestion++;
        loadQuestion();
    }

    private void showFinalResults() {
        countdownTimer.stop();
        int choice = JOptionPane.showConfirmDialog(this, 
            "Quiz Finished!\nFinal Score: " + score + "/" + questions.length + "\nWould you like to restart?", 
            "Results", JOptionPane.YES_NO_OPTION);
        
        if (choice == JOptionPane.YES_OPTION) {
            restartQuiz();
        } else {
            System.exit(0);
        }
    }

    private void restartQuiz() {
        currentQuestion = 0;
        score = 0;
        lblScore.setText("Score: 0");
        loadQuestion();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNext) {
            handleNext();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JavaQuiz::new);
    }
}