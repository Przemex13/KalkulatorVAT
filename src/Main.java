import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class Main extends JFrame{
    private JPanel mainPanel;
    private JLabel kalkulatorProcentowPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton obliczButton;
    private JPanel panel;


//    moje zmienne które stworzyłem

    private double netValue;
    private int vatTax;
    private double grosValue;

    private String zzz;






    public Main (){
        setTitle("Kalkulator VAT");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 230);
        setVisible(true);

        setContentPane(mainPanel);

        textField1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }
            @Override
            public void focusLost(FocusEvent e) {
                System.out.println("zmykam!");
                netValue = Double.parseDouble(zzz);
                System.out.println(netValue);

            }
        });

        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                if (!isFigure(e.getKeyChar())) e.consume();
                zzz = textField1.getText() + e.getKeyChar();

            }
        });






    }
    private boolean isFigure (char element){
        if (element >= '0' && element <= '9')
            return true;

        return false;
    }



    public static void main(String[] args) {
        new Main();
    }
}
