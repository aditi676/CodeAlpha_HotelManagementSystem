package management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class CheckOut extends JFrame implements ActionListener {

    Choice c1;
    JLabel lblroomnumber, lblcheckin, lblcheckout;
    JButton checkoutBtn, backBtn;

    public CheckOut() {
        setLayout(null);

        JLabel text = new JLabel("CHECKOUT");
        text.setBounds(130, 20, 150, 50);
        text.setForeground(Color.BLUE);
        text.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(text);

        JLabel id = new JLabel("Customer ID");
        id.setBounds(50, 80, 100, 30);
        id.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(id);

        c1 = new Choice();
        c1.setBounds(250, 85, 150, 25);
        add(c1);

        try {
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) {
                c1.add(rs.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"));
        Image i5 = i4.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JButton fetchBtn = new JButton(i6);
        fetchBtn.setBounds(410, 85, 20, 20);
        add(fetchBtn);

        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(50, 120, 120, 30);
        lblroom.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(lblroom);

        lblroomnumber = new JLabel();
        lblroomnumber.setBounds(250, 120, 150, 30);
        lblroomnumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(lblroomnumber);

        JLabel lblcheckinText = new JLabel("Check-in Time");
        lblcheckinText.setBounds(50, 160, 120, 30);
        lblcheckinText.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(lblcheckinText);

        lblcheckin = new JLabel();
        lblcheckin.setBounds(250, 160, 200, 30);
        lblcheckin.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(lblcheckin);

        JLabel lblcheckoutText = new JLabel("Checkout Time");
        lblcheckoutText.setBounds(50, 200, 120, 30);
        lblcheckoutText.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(lblcheckoutText);

        Date date = new Date();
        lblcheckout = new JLabel("" + date);
        lblcheckout.setBounds(250, 200, 200, 30);
        lblcheckout.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(lblcheckout);

        checkoutBtn = new JButton("Checkout");
        checkoutBtn.setBackground(Color.BLACK);
        checkoutBtn.setForeground(Color.WHITE);
        checkoutBtn.setBounds(80, 260, 100, 30);
        checkoutBtn.addActionListener(this);
        add(checkoutBtn);

        backBtn = new JButton("Back");
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        backBtn.setBounds(220, 260, 100, 30);
        backBtn.addActionListener(this);
        add(backBtn);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
        Image i3 = i1.getImage().getScaledInstance(400, 225, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel img = new JLabel(i2);
        img.setBounds(450, 50, 300, 225);
        add(img);

        fetchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = c1.getSelectedItem();
                try {
                    conn c = new conn();
                    ResultSet rs = c.s.executeQuery("select * from customer where number = '" + id + "'");
                    while (rs.next()) {
                        lblroomnumber.setText(rs.getString("room"));
                        lblcheckin.setText(rs.getString("checkintime"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        getContentPane().setBackground(Color.WHITE);
        setBounds(300, 200, 800, 400);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == checkoutBtn) {
            String id = c1.getSelectedItem();
            String room = lblroomnumber.getText();
            String query1 = "delete from customer where number = '" + id + "'";
            String query2 = "update room set availability = 'Available' where roomnumber = '" + room + "'";

            try {
                conn c = new conn();
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                JOptionPane.showMessageDialog(null, "Checkout Successful");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == backBtn) {
            new Reception().setVisible(true);
			setVisible(false);
        }
    }

    public static void main(String[] args) {
        new CheckOut();
    }
}
