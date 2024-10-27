// InvoiceApp.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvoiceApp extends JFrame {
    private JTextField customerNameField;
    private JTextField customerAddressField;
    private JTextField itemNameField;
    private JTextField quantityField;
    private JTextField priceField;
    private JTextArea invoiceDisplay;
    private Invoice invoice;
    private InvoiceManager invoiceManager;

    public InvoiceApp() {
        invoiceManager = new InvoiceManager(); // Initialize InvoiceManager
        setTitle("Invoice Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLayout(new BorderLayout(10, 10));

        JPanel customerPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        customerNameField = new JTextField();
        customerAddressField = new JTextField();
        customerPanel.add(new JLabel("Customer Name:"));
        customerPanel.add(customerNameField);
        customerPanel.add(new JLabel("Customer Address:"));
        customerPanel.add(customerAddressField);

        JPanel itemPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        itemNameField = new JTextField();
        quantityField = new JTextField();
        priceField = new JTextField();
        itemPanel.add(new JLabel("Item Name:"));
        itemPanel.add(itemNameField);
        itemPanel.add(new JLabel("Quantity:"));
        itemPanel.add(quantityField);
        itemPanel.add(new JLabel("Unit Price:"));
        itemPanel.add(priceField);

        JButton addItemButton = new JButton("Add Item");
        addItemButton.addActionListener(new AddItemListener());
        itemPanel.add(addItemButton);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(customerPanel, BorderLayout.NORTH);
        topPanel.add(itemPanel, BorderLayout.CENTER);

        invoiceDisplay = new JTextArea();
        invoiceDisplay.setEditable(false);
        invoiceDisplay.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(invoiceDisplay);

        JButton generateInvoiceButton = new JButton("Generate Invoice");
        generateInvoiceButton.addActionListener(new GenerateInvoiceListener());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(generateInvoiceButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private class AddItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String itemName = itemNameField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());

            if (invoice == null) {
                Customer customer = new Customer(customerNameField.getText(), customerAddressField.getText());
                invoice = new Invoice(customer);
                invoiceManager.addInvoice(invoice); // Add the invoice to the manager
            }

            Product product = new Product(itemName, price);
            LineItem lineItem = new LineItem(product, quantity);
            invoice.addLineItem(lineItem);

            // Clear the item fields after adding
            itemNameField.setText("");
            quantityField.setText("");
            priceField.setText("");

            updateInvoiceDisplay();
        }
    }

    private class GenerateInvoiceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (invoice == null) {
                JOptionPane.showMessageDialog(null, "Please add at least one item to generate an invoice.");
                return;
            }
            updateInvoiceDisplay();
        }
    }

    private void updateInvoiceDisplay() {
        if (invoice != null) {
            invoiceDisplay.setText(invoice.getFormattedInvoice());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InvoiceApp frame = new InvoiceApp();
            frame.setVisible(true);
        });
    }
}
