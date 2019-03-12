package atm;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class atmInterface extends JFrame {

    private JPanel atmInterface;

    CardLayout cardLayout = (CardLayout)atmInterface.getLayout();

    private JTextField usernameTextField, creditScoreTextField, newUsernameTextField;
    private JButton createNewAccountButton, adminLoginButton;
    private JTextArea textArea1;
    private JPanel loginScreen, createNewUser, adminControls;
    private JTextField creditScore;
    private JTextArea newUserMessage;
    private JLabel newUsernameLabel;
    private JLabel creditScoreLabel;
    private JButton backButton;
    private JButton loginButton;
    private JButton requestAccountButton;
    private JTabbedPane tabbedPane1;
    private JList newUserRequests;
    private JList existingUserRequests;
    private JPanel Main;
    private JButton setDateButton;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JSpinner spinner3;
    private JSpinner spinner4;
    private JButton addButton;
    private JButton logoutButton;
    private JButton acceptButton;
    private JButton acceptButton1;
    private JPanel mainMenu;
    private JButton viewAccountsButton;
    private JButton logoutButton1;
    private JButton changeYourPasswordButton;
    private JButton payABillButton;
    private JButton transferFundsButton;
    private JButton makeAWithdrawalButton;


    public atmInterface() {
        createNewAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(atmInterface, "createNewUser");
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(atmInterface, "loginScreen");
            }
        });
        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(atmInterface, "adminControls");
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(atmInterface, "loginScreen");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("atmInterface");
        frame.setContentPane(new atmInterface().atmInterface);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}

