import javax.swing.*;
import java.awt.event.*;

public class CustomsJDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;

    JLabel dialogMessage;
    private JLabel vatTaxMessage;
    private JLabel grosValueMessage;
    private JLabel warningMessage;

    public CustomsJDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });



        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {

       dispose();

    }

    private void onCancel() {
        dispose();
    }



}
