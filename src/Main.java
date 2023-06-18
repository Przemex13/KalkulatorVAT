import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame{
    private JPanel mainPanel;
    private JLabel kalkulatorProcentowPanel;
    private JTextField netValueTextField;
    private JTextField vatTextField;
    private JTextField grosValueTextField;
    private JButton obliczButton;
    private JPanel panel;


//    moje zmienne które stworzyłem

    private double netValue;
    private int vatTax;
    private double grosValue;

    private String netValueDisplayString;
    private String vatTaxDisplayString;
    private String grosValueDisplayString;

    List contain = new ArrayList<>();






    public Main (){
        setTitle("Kalkulator VAT");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 230);
        setVisible(true);

        setContentPane(mainPanel);
// listeners
        netValueTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }
            @Override
            public void focusLost(FocusEvent e) {



            }
        });

        netValueTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                if (!isFigure(e.getKeyChar())) e.consume();
                netValueDisplayString = netValueTextField.getText() + e.getKeyChar();
                if (!netValueTextField.getText().isEmpty()) netValue = Double.parseDouble(netValueDisplayString);
                System.out.println(netValue);
            }
        });

        vatTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }
            @Override
            public void focusLost(FocusEvent e) {


            }
        });

        vatTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                if (!isFigure(e.getKeyChar())) e.consume();
                vatTaxDisplayString = vatTextField.getText() + e.getKeyChar();
                if (!vatTextField.getText().isEmpty()) vatTax = Integer.parseInt(vatTaxDisplayString);
                System.out.println(vatTax);

            }
        });

        grosValueTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }
            @Override
            public void focusLost(FocusEvent e) {








            }
        });

        grosValueTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                if (!isFigure(e.getKeyChar())) e.consume();
                grosValueDisplayString = grosValueTextField.getText() + e.getKeyChar();
                if (!grosValueTextField.getText().isEmpty()) grosValue = Double.parseDouble(grosValueDisplayString);
                System.out.println(grosValue);

            }
        });

        obliczButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(netValueTextField.getText().isEmpty());
                System.out.println(vatTextField.getText().isEmpty());
                System.out.println(grosValueTextField.getText().isEmpty());
                System.out.println("===========================================");
                System.out.println("net value :" + netValue);
                System.out.println("vat value :" + vatTax);
                System.out.println("gross value :" + grosValue);


            }
        });

        System.out.println(netValueTextField.getText().isEmpty());
        System.out.println(vatTextField.getText().isEmpty());
        System.out.println(grosValueTextField.getText().isEmpty());


    }
    private boolean isFigure (char element){
        if (element >= '0' && element <= '9')
            return true;

        return false;
    }

    private static Double roundNumberTwoDecimals (double number){
        number *= 100;
        number = Math.round(number);
        number /= 100;

        return number;
    }
    private static boolean ifNumberIsRounded (double number){

        return (roundNumberTwoDecimals(number) == number);
    }



    public static void main(String[] args) {
        new Main();
    }
}
