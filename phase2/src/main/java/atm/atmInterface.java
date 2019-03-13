package atm;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class atmInterface {

    private JPanel atmInterface;

    CardLayout cardLayout = (CardLayout) atmInterface.getLayout();

    private JTextField login_usernameTextField, login_passwordTextField, new_username;
    private JButton createNewAccountButton, adminLoginButton;
    private JTextArea wELCTextArea;
    private JPanel loginScreen, createNewUser, adminControls;
    private JTextField creditScoreTextField;
    private JLabel newUsernameLabel;
    private JButton new_backButton;
    private JButton loginButton;
    private JButton requestAccountButton;
    private JTabbedPane admin_tabs;
    private JPanel Main;
    private JButton admin_setDateButton;
    private JSpinner admin_5spinner;
    private JSpinner admin_10spinner;
    private JSpinner admin_20spinner;
    private JSpinner admin_50spinner;
    private JButton admin_addCashButton;
    private JButton admin_logoutButton;
    private JPanel mainMenu;
    private JTabbedPane menu;
    private JPanel main_accounts;
    private JButton changePasswordButton;
    private JTextField main_passwordField;
    private JButton mainMenu_logoutButton;
    private JRadioButton lineOfCreditRadioButton;
    private JRadioButton chequingRadioButton;
    private JRadioButton creditCardRadioButton;
    private JRadioButton savingsRadioButton;
    private JButton mainMenu_requestAccountButton;
    private JComboBox menu_accountChooser;
    private JTextArea menu_accountInfo;
    private JTabbedPane trasnfers;
    private JSpinner main_5spinner;
    private JSpinner main_10spinner;
    private JSpinner main_20spinner;
    private JSpinner main_50spinner;
    private JButton transferButton;
    private JTextField textField3;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JButton button1;
    private JTextField textField1;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JComboBox comboBox6;
    private JTextField textField2;
    private JButton payButton;
    private JButton main_withdrawButton;
    private JLabel login_usernameLabel;
    private JLabel login_passwordLabel;
    private JLabel login_message;
    private JPanel main_menu;
    private JPanel main_transfers;
    private JPanel main_cash;
    private JPanel transfers_internal;
    private JPanel transfers_external;
    private JPanel transfers_billPayment;
    private JLabel admin_5bills;
    private JLabel admin_10bills;
    private JLabel admin_20bills;
    private JLabel admin_50bills;
    private JLabel main_totalWithdrawal;
    private JLabel withdrawalMessage;
    private JLabel main_passwordMessage;
    private JLabel newUser_message;
    private JButton depositButton;
    private JTextField deposit_amount;
    private JLabel depositLabel;
    private JLabel depositMessage;
    private JButton admin_newRequestButton;
    private JButton admin_existingRequestButton;
    private JTextField admin_transactionUser;
    private JComboBox admin_accounts;
    private JButton admin_checkAccountsButton;
    private JButton reverseLastTransactionButton;
    private JLabel main_newPasswordMessage;

    atmRunner atm = new atmRunner();

    public atmInterface() {

        // ----------------------------- login page -----------------------------

        createNewAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(atmInterface, "createNewUser");
                login_usernameTextField.setText("");
                login_passwordTextField.setText("");
            }
        });

        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (atm.adminCheck(login_passwordTextField.getText())) {
                    cardLayout.show(atmInterface, "adminControls");
                    login_usernameTextField.setText("");
                    login_passwordTextField.setText("");
                } else login_message.setText("Admin access denied");
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (atm.userLogin(login_usernameTextField.getText(), login_passwordTextField.getText())) {
                    cardLayout.show(atmInterface, "mainMenu");
                } else {
                    login_message.setText("Access Denied!");
                }
                login_usernameTextField.setText("");
                login_passwordTextField.setText("");
            }
        });

        // --- admin controls ---

        admin_newRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atm.acceptNewUserRequests();
            }
        });

        admin_existingRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atm.acceptExistingUserRequests();
            }
        });

        admin_setDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        admin_logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(atmInterface, "loginScreen");
            }
        });

        admin_checkAccountsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        // --- create new user ---

        new_backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(atmInterface, "loginScreen");
                new_username.setText("");
                creditScoreTextField.setText("");
                login_usernameTextField.setText("");
            }
        });

        requestAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newUser_message.setText(atm.newUserRequest(new_username.getText()));
            }
        });

        // ----------------------------- menu menu -----------------------------

        mainMenu_logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(atmInterface, "loginScreen");
            }
        });


        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atm.changePassword(main_passwordField.getText());
                main_passwordMessage.setText("Password changed");
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

