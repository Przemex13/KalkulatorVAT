import javax.swing.*;
import java.awt.event.*;

public class CustomListener extends KeyAdapter implements FocusListener{


    private JTextField listenedTextField;
    private String poletxt;

    private Double finalTextField;


    public CustomListener(Double finalTextField) {
        this.finalTextField = finalTextField;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (!isFigure(e.getKeyChar())) e.consume();
        poletxt = listenedTextField.getText() + e.getKeyChar();
    }


    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        finalTextField = Double.parseDouble(poletxt);
        System.out.println(finalTextField);

    }

    private boolean isFigure (char element){
        if (element >= '0' && element <= '9')
            return true;

        return false;
    }
}
