import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Loginfenster extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtuser;
    private JPasswordField txtPassword;
    private JButton neuerBenutzerButton;

    public Loginfenster() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        neuerBenutzerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { oeffneNeuenBenutzer();}
        });
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
    }

    private void onOK() {
        if(txtuser.getText().equals("") || txtPassword.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Bitte geben Sie Daten ein");
        }else{
            boolean funktioniert = login(txtuser.getText(), txtPassword.getText());
            if(funktioniert) {
                oeffneNeuenSchueler();
            }else{
                JOptionPane.showMessageDialog(null, "Login fehlgeschlagen");
            }
        }
    }

    private boolean login(String user, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://bszw.ddns.net:3306", "wit11g", "geheim");
            String select = "SELECT password FROM  wit11g_hecht.login"
                    + " WHERE user = '"
                    + user+"'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(select);
            rs.next();
            String pwd = rs.getString("password");
            con.close();
            return password.equals(pwd);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    public void oeffneNeuenBenutzer(){
        setModal(false);
        NeuerBenutzer benutzer = new NeuerBenutzer();
        benutzer.setVisible(true);
        benutzer.setAlwaysOnTop(true);
        benutzer.requestFocus();
    }
    public void oeffneNeuenSchueler(){
        setModal(false);
        Schueler schueler = new Schueler();

        schueler.setVisible(true);
        schueler.setAlwaysOnTop(true);
        schueler.requestFocus();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        Loginfenster dialog = new Loginfenster();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
