package atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Pattern;

public class atmInterface extends JFrame {

    private JPanel atmInterface;

    CardLayout cardLayout = (CardLayout)atmInterface.getLayout();

    private JTextField login_usernameTextField, login_passwordTextField, newUsernameTextField;
    private JButton createNewAccountButton, adminLoginButton;
    private JTextArea wELCTextArea;
    private JPanel loginScreen, createNewUser, adminControls;
    private JTextField creditScore;
    private JTextArea newUserMessage;
    private JLabel newUsernameLabel;
    private JLabel creditScoreLabel;
    private JButton backButton;
    private JButton loginButton;
    private JButton requestAccountButton;
    private JTabbedPane admin_tabs;
    private JList newUserRequests;
    private JList existingUserRequests;
    private JPanel Main;
    private JButton admin_setDateButton;
    private JSpinner admin_5spinner;
    private JSpinner admin_10spinner;
    private JSpinner admin_20spinner;
    private JSpinner admin_50spinner;
    private JButton admin_addCashButton;
    private JButton admin_logoutButton;
    private JButton admin_newAcceptButton;
    private JButton admin_existingAcceptButton;
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
    private JLabel login_invalidLabel;
    private JPanel main_menu;
    private JPanel main_transfers;
    private JPanel main_withdrawals;
    private JPanel transfers_internal;
    private JPanel transfers_external;
    private JPanel transfers_billPayment;
    private JLabel admin_5bills;
    private JLabel admin_10bills;
    private JLabel admin_20bills;
    private JLabel admin_50bills;
    private JLabel main_totalWithdrawal;
    private JLabel main_insufficientFunds;
    private JLabel main_passwordMessage;
    private JLabel main_newPasswordMessage;

    ATM atm = new ATM();





    public class logoutActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            cardLayout.show(atmInterface, "loginScreen");
        }
    }

    public atmInterface() {

        // ----------------------------- reused actions -----------------------------

        logoutActionListener logout = new logoutActionListener();

        // ----------------------------- login page -----------------------------

        createNewAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(atmInterface, "createNewUser");
            }
        });

//
//        adminLoginButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (atm.adminCheck(login_passwordTextField.getText())) {
//                    cardLayout.show(atmInterface, "adminControls");
//                    login_passwordTextField.setText("");
//                }
//            }
//        });


//        loginButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (atm.login(login_usernameTextField.getText(), login_passwordTextField.getText())) {
//                    cardLayout.show(atmInterface, "mainMenu");
//                } else {
//                    login_invalidLabel.setText("Try again!");
//                }
//                login_usernameTextField.setText("");
//                login_passwordTextField.setText("");
//            }
//        });

        // admin controls
        admin_logoutButton.addActionListener(logout);


        // create new user
        backButton.addActionListener(logout);


        // ----------------------------- menu menu -----------------------------

        mainMenu_logoutButton.addActionListener(logout);


        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPassword = main_passwordField.getText();
                // TODO: should do some regex thing to make sure password is only numbers and letters
                // ATM changed so this results in an error
//                atm.changePassword(newPassword);
                main_newPasswordMessage.setText("New password: " + newPassword);
                main_passwordField.setText("");
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

