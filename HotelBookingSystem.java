import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HotelBookingSystem extends JFrame implements ActionListener {
    // Components
    private JTabbedPane tabbedPane;
    private JTextField txtCustomerName;
    private JComboBox<String> cbRoomType;
    private JSpinner spinCheckIn, spinCheckOut;
    private JTable roomTable;
    private DefaultTableModel tableModel;
    private JButton btnBook, btnReceipt;

    // Room Data
    private String[][] roomData = {
        {"101", "Single", "Available"},
        {"102", "Single", "Available"},
        {"201", "Double", "Available"},
        {"202", "Double", "Available"},
        {"301", "Suite", "Available"},
        {"302", "Suite", "Available"}
    };

    public HotelBookingSystem() {
        setTitle("Hotel Booking System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane();

        // --- 1. BOOKING TAB ---
        JPanel bookingPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        bookingPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        bookingPanel.add(new JLabel("Customer Name:"));
        txtCustomerName = new JTextField();
        bookingPanel.add(txtCustomerName);

        bookingPanel.add(new JLabel("Room Type:"));
        cbRoomType = new JComboBox<>(new String[]{"Single", "Double", "Suite"});
        bookingPanel.add(cbRoomType);

        // Date Spinners
        bookingPanel.add(new JLabel("Check-in Date:"));
        spinCheckIn = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor deIn = new JSpinner.DateEditor(spinCheckIn, "dd/MM/yyyy");
        spinCheckIn.setEditor(deIn);
        bookingPanel.add(spinCheckIn);

        bookingPanel.add(new JLabel("Check-out Date:"));
        spinCheckOut = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor deOut = new JSpinner.DateEditor(spinCheckOut, "dd/MM/yyyy");
        spinCheckOut.setEditor(deOut);
        bookingPanel.add(spinCheckOut);

        btnBook = new JButton("Book Now");
        btnReceipt = new JButton("Generate Receipt");
        bookingPanel.add(btnBook);
        bookingPanel.add(btnReceipt);

        // --- 2. ROOMS TAB ---
        String[] cols = {"Room No", "Type", "Status"};
        tableModel = new DefaultTableModel(roomData, cols);
        roomTable = new JTable(tableModel);
        JPanel roomsPanel = new JPanel(new BorderLayout());
        roomsPanel.add(new JScrollPane(roomTable), BorderLayout.CENTER);

        // Add tabs
        tabbedPane.addTab("Booking", bookingPanel);
        tabbedPane.addTab("Rooms", roomsPanel);

        add(tabbedPane);

        // Listeners
        btnBook.addActionListener(this);
        btnReceipt.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBook) {
            handleBooking();
        } else if (e.getSource() == btnReceipt) {
            showReceipt();
        }
    }

    private void handleBooking() {
        String name = txtCustomerName.getText().trim();
        String selectedType = (String) cbRoomType.getSelectedItem();
        Date checkIn = (Date) spinCheckIn.getValue();
        Date checkOut = (Date) spinCheckOut.getValue();

        // Date Validation
        if (name.isEmpty() || checkOut.before(checkIn)) {
            JOptionPane.showMessageDialog(this, "Check-out must be after check-in and name is required.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update Room Status Dynamically
        boolean found = false;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 1).equals(selectedType) && 
                tableModel.getValueAt(i, 2).equals("Available")) {
                tableModel.setValueAt("Booked", i, 2);
                found = true;
                JOptionPane.showMessageDialog(this, "Room booked successfully!");
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "No " + selectedType + " rooms available.");
        }
    }

    private void showReceipt() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String content = "--- HOTEL RECEIPT ---\n" +
                         "Customer: " + txtCustomerName.getText() + "\n" +
                         "Room Type: " + cbRoomType.getSelectedItem() + "\n" +
                         "Check-in: " + sdf.format(spinCheckIn.getValue()) + "\n" +
                         "Check-out: " + sdf.format(spinCheckOut.getValue()) + "\n" +
                         "Status: Confirmed";

        JDialog receiptDialog = new JDialog(this, "Receipt", true);
        receiptDialog.setLayout(new BorderLayout());
        JTextArea area = new JTextArea(content);
        area.setEditable(false);
        area.setMargin(new Insets(10, 10, 10, 10));
        receiptDialog.add(area, BorderLayout.CENTER);
        
        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(x -> receiptDialog.dispose());
        receiptDialog.add(btnClose, BorderLayout.SOUTH);
        
        receiptDialog.setSize(300, 250);
        receiptDialog.setLocationRelativeTo(this);
        receiptDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HotelBookingSystem::new);
    }
}