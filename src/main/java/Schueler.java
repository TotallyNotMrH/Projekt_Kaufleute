import javax.swing.*;

public class Schueler extends JDialog {
    private JPanel panel;

    public Schueler() {
        setTitle("Schueler");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(panel);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setSize(400, 300);
    }
}
