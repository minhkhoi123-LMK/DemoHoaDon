/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hoadon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class HoaDon extends JFrame {
    private JTextField txtItem;
    private JTextField txtQuantity;
    private JTextField txtPrice;
    private JTextArea txtInvoice;

    public HoaDon() {
        setTitle("Invoice Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        JLabel lblItem = new JLabel("Item:");
        txtItem = new JTextField();
        JLabel lblQuantity = new JLabel("Quantity:");
        txtQuantity = new JTextField();
        JLabel lblPrice = new JLabel("Price:");
        txtPrice = new JTextField();
        JButton btnAddItem = new JButton("Add Item");

        inputPanel.add(lblItem);
        inputPanel.add(txtItem);
        inputPanel.add(lblQuantity);
        inputPanel.add(txtQuantity);
        inputPanel.add(lblPrice);
        inputPanel.add(txtPrice);
        inputPanel.add(new JLabel()); // Empty label for spacing
        inputPanel.add(btnAddItem);

        JPanel invoicePanel = new JPanel(new BorderLayout());
        JLabel lblInvoice = new JLabel("Invoice:");
        txtInvoice = new JTextArea();
        txtInvoice.setEditable(false);
        invoicePanel.add(lblInvoice, BorderLayout.NORTH);
        invoicePanel.add(new JScrollPane(txtInvoice), BorderLayout.CENTER);

        add(inputPanel, BorderLayout.NORTH);
        add(invoicePanel, BorderLayout.CENTER);

        btnAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = txtItem.getText();
                int quantity = Integer.parseInt(txtQuantity.getText());
                double price = Double.parseDouble(txtPrice.getText());

                double total = quantity * price;
                String invoiceEntry = String.format("%s - Quantity: %d, Price: $%.2f, Total: $%.2f\n",
                        item, quantity, price, total);
                txtInvoice.append(invoiceEntry);

                // Clear input fields
                txtItem.setText("");
                txtQuantity.setText("");
                txtPrice.setText("");
            }
        });
        
              JButton btnPrint = new JButton("Print");
        btnPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printInvoice();
            }
        });
        add(btnPrint, BorderLayout.SOUTH);
    }

    private void printInvoice() {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    return NO_SUCH_PAGE;
                }

                Graphics2D g2d = (Graphics2D) graphics;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                FontMetrics metrics = g2d.getFontMetrics();
                int lineHeight = metrics.getHeight();

                int x = 50;
                int y = 50;

                String[] lines = txtInvoice.getText().split("\n");
                for (String line : lines) {
                    g2d.drawString(line, x, y);
                    y += lineHeight;
                }

                return PAGE_EXISTS;
            }
        });

        boolean printDialog = printerJob.printDialog();
        if (printDialog) {
            try {
                printerJob.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HoaDon invoiceGenerator = new HoaDon();
                invoiceGenerator.setVisible(true);
            }
        });
    }
}