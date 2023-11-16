/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hoadon;

/**
 *
 * @author MINH KHOI
 */
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvoiceSystem extends JFrame implements ActionListener {
    private JTextField txtProductName, txtQuantity, txtPrice;
    private JButton btnCalculate, btnClear;
    
    public InvoiceSystem() {
        setTitle("Invoice System");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Tạo các label và text field
        JLabel lblProductName = new JLabel("Product Name:");
        txtProductName = new JTextField(20);
        
        JLabel lblQuantity = new JLabel("Quantity:");
        txtQuantity = new JTextField(10);
        
        JLabel lblPrice = new JLabel("Price:");
        txtPrice = new JTextField(10);
        
        // Tạo các button
        btnCalculate = new JButton("Calculate");
        btnClear = new JButton("Clear");
        
        // Đặt layout cho JFrame
        setLayout(new GridLayout(4, 2));
        
        // Thêm các thành phần vào JFrame
        add(lblProductName);
        add(txtProductName);
        add(lblQuantity);
        add(txtQuantity);
        add(lblPrice);
        add(txtPrice);
        add(btnCalculate);
        add(btnClear);
        
        // Đăng ký sự kiện cho các button
        btnCalculate.addActionListener(this);
        btnClear.addActionListener(this);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalculate) {
            String productName = txtProductName.getText();
            int quantity = Integer.parseInt(txtQuantity.getText());
            double price = Double.parseDouble(txtPrice.getText());
            
            double total = quantity * price;
            
            JOptionPane.showMessageDialog(null, "Product: " + productName + "\nQuantity: " + quantity +
                    "\nPrice: " + price + "\nTotal: " + total);
        } else if (e.getSource() == btnClear) {
            txtProductName.setText("");
            txtQuantity.setText("");
            txtPrice.setText("");
        }
    }
    
    public static void main(String[] args) {
        new InvoiceSystem();
    }
}
