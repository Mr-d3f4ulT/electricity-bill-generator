package friday;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BillingUI extends JFrame {

    private JTextField nameField, meterField, addressField, accountField, connectionField, loadField, mobileField, emailField;
    private JTextField billingMonthField, dueDateField, unitsField;
    private JButton generateButton, clearButton;

    public BillingUI() {
        setTitle("Electricity Bill Generator");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(14, 2, 10, 5));

        // Fields
        nameField = new JTextField();
        meterField = new JTextField();
        addressField = new JTextField();
        accountField = new JTextField();
        connectionField = new JTextField();
        loadField = new JTextField();
        mobileField = new JTextField();
        emailField = new JTextField();

        billingMonthField = new JTextField();
        dueDateField = new JTextField();
        unitsField = new JTextField();

        panel.add(new JLabel("Customer Name:")); panel.add(nameField);
        panel.add(new JLabel("Meter Number:")); panel.add(meterField);
        panel.add(new JLabel("Address:")); panel.add(addressField);
        panel.add(new JLabel("Account Number:")); panel.add(accountField);
        panel.add(new JLabel("Connection Date (DD-MM-YYYY):")); panel.add(connectionField);
        panel.add(new JLabel("Sanctioned Load (e.g. 4 KW):")); panel.add(loadField);
        panel.add(new JLabel("Mobile Number:")); panel.add(mobileField);
        panel.add(new JLabel("Email:")); panel.add(emailField);

        panel.add(new JLabel("Billing Month (e.g. July2025):")); panel.add(billingMonthField);
        panel.add(new JLabel("Due Date (DD-MM-YYYY):")); panel.add(dueDateField);
        panel.add(new JLabel("Units Consumed:")); panel.add(unitsField);

        generateButton = new JButton("Generate Bill");
        clearButton = new JButton("Clear All");

        panel.add(generateButton);
        panel.add(clearButton);

        add(panel);

        // Action Listeners
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    String meter = meterField.getText();
                    String address = addressField.getText();
                    String account = accountField.getText();
                    String connection = connectionField.getText();
                    String load = loadField.getText();
                    String mobile = mobileField.getText();
                    String email = emailField.getText();

                    String billingMonth = billingMonthField.getText();
                    String dueDate = dueDateField.getText();
                    int units = Integer.parseInt(unitsField.getText());

                    String billDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                    Customer customer = new Customer(name, meter, address, account, connection, load, mobile, email);
                    BillService service = new BillService();
                    double sanctionedKW = Double.parseDouble(load.replaceAll("[^0-9.]", ""));
                    double amount = service.calculateAmount(units, sanctionedKW);

                    Bill bill = new Bill(customer, units, billingMonth, billDate, dueDate, amount);

                    service.saveBillToFile(bill);
                    PDFExporter exporter = new PDFExporter();
                    exporter.generateBillPDF(bill);

                    JOptionPane.showMessageDialog(null, "Bill generated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Component comp : panel.getComponents()) {
                    if (comp instanceof JTextField) {
                        ((JTextField) comp).setText("");
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BillingUI().setVisible(true));
    }
}
