import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class NeuerBenutzer extends JDialog{
    private JTextField txtUsername;
    private JPasswordField txtPassword1;
    private JPasswordField txtPassword2;
    private JButton saveButton;
    private JPanel panel;

    public NeuerBenutzer() {
        this.setTitle("Neuer Benutzer");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(panel);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setSize(400, 300);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = txtPassword1.getText();
                String password2 = txtPassword2.getText();
                if(password.equals(password2)){
                    fuegeHinzu(username, password);
                }
            }
        });
    }
    public void fuegeHinzu(String user, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://bszw.ddns.net:3306", "wit11g", "geheim");
            String insertSql = "INSERT INTO wit11g_hecht.login"
                    + "(user, password)"
                    + "VALUES('"+user+"', '"+password+"')";
            Statement stmt = con.createStatement();
            stmt.execute(insertSql);
            con.close();
            dispose();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
