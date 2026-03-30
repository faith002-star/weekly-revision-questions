import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LibrarySearch extends JFrame implements ActionListener {
    // Data structures
    private String[][] bookDatabase = {
        {"The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", "Available"},
        {"1984", "George Orwell", "9780451524935", "Borrowed"},
        {"To Kill a Mockingbird", "Harper Lee", "9780061120084", "Available"},
        {"The Hobbit", "J.R.R. Tolkien", "9780547928227", "Available"},
        {"Java Programming", "John Smith", "9780134685991", "Available"},
        {"Clean Code", "Robert Martin", "9780132350884", "Borrowed"},
        {"The Catcher in the Rye", "J.D. Salinger", "9780316769174", "Available"},
        {"Brave New World", "Aldous Huxley", "9780060850524", "Available"},
        {"The Alchemist", "Paulo Coelho", "9780062315007", "Borrowed"},
        {"Database Systems", "C.J. Date", "9780321197849", "Available"}
    };

    private DefaultListModel<String> historyModel;
    private DefaultTableModel tableModel;
    
    // Components
    private JTextField searchField;
    private JButton btnSearch;
    private JTable resultTable;
    private JList<String> historyList;
    private JMenuItem mntmExit, mntmClear, mntmAbout;

    public LibrarySearch() {
        // 1. JFrame Setup
        setTitle("Library Search");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // --- MENU BAR ---
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        mntmExit = new JMenuItem("Exit");
        menuFile.add(mntmExit);

        JMenu menuView = new JMenu("View");
        mntmClear = new JMenuItem("Clear History");
        menuView.add(mntmClear);

        JMenu menuHelp = new JMenu("Help");
        mntmAbout = new JMenuItem("About");
        menuHelp.add(mntmAbout);

        menuBar.add(menuFile);
        menuBar.add(menuView);
        menuBar.add(menuHelp);
        setJMenuBar(menuBar);

        // --- SEARCH PANEL (North) ---
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(25);
        btnSearch = new JButton("Search");
        searchPanel.add(new JLabel("Book Title:"));
        searchPanel.add(searchField);
        searchPanel.add(btnSearch);
        add(searchPanel, BorderLayout.NORTH);

        // --- RESULTS TABLE (Center) ---
        String[] columns = {"Title", "Author", "ISBN", "Availability"};
        tableModel = new DefaultTableModel(columns, 0);
        resultTable = new JTable(tableModel);
        add(new JScrollPane(resultTable), BorderLayout.CENTER);

        // --- SEARCH HISTORY (East) ---
        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.setBorder(BorderFactory.createTitledBorder("Search History"));
        historyModel = new DefaultListModel<>();
        historyList = new JList<>(historyModel);
        historyPanel.add(new JScrollPane(historyList), BorderLayout.CENTER);
        historyPanel.setPreferredSize(new Dimension(150, 0));
        add(historyPanel, BorderLayout.EAST);

        // --- ACTION LISTENERS ---
        btnSearch.addActionListener(this);
        mntmExit.addActionListener(this);
        mntmClear.addActionListener(this);
        mntmAbout.addActionListener(this);

        // Show all books initially
        updateTable("");
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            performSearch();
        } else if (e.getSource() == mntmExit) {
            System.exit(0);
        } else if (e.getSource() == mntmClear) {
            historyModel.clear();
        } else if (e.getSource() == mntmAbout) {
            JOptionPane.showMessageDialog(this, "Library Search System v1.0\nCreated for Student Project.");
        }
    }

    private void performSearch() {
        String query = searchField.getText().trim();
        
        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search term.");
            return;
        }

        // Add to History JList
        historyModel.addElement(query);
        
        // Filter Table Results
        updateTable(query);
    }

    private void updateTable(String filter) {
        // Clear existing rows
        tableModel.setRowCount(0);

        for (String[] book : bookDatabase) {
            // Case-insensitive check on Title or Author
            if (filter.isEmpty() || 
                book[0].toLowerCase().contains(filter.toLowerCase()) || 
                book[1].toLowerCase().contains(filter.toLowerCase())) {
                tableModel.addRow(book);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibrarySearch::new);
    }
}