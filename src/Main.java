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


        netValueTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char pomocniczyNetValue =' ';

                if (isCharValid(e.getKeyChar())&& isContainComa(netValueTextField, e.getKeyChar())){
                    pomocniczyNetValue = e.getKeyChar();
                }else {
                    e.consume();
                }

                netValueDisplayString = netValueTextField.getText() + pomocniczyNetValue;
                System.out.println(netValueDisplayString);


            }
        });
        netValueTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {

                if (netValueTextField.getText().isEmpty()){
                    netValueDisplayString ="0";
                    netValueTextField.setText("0.00");
                    if(netValueTextField.getText().isEmpty()) netValueTextField.setText("0.00");

                }
                else{
                    netValueTextField.setText(String.valueOf(roundNumberTwoDecimals(Double.valueOf(netValueDisplayString))));
                    netValueDisplayString = netValueTextField.getText();
                    if(Double.parseDouble(netValueTextField.getText()) == 0) netValueTextField.setText("0.00");
                }


            }
        });



        vatTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
        });



        grosValueTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {



            }
        });

        obliczButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(netValueTextField.getText().isEmpty());
                System.out.println(vatTextField.getText().isEmpty());
                System.out.println(grosValueTextField.getText().isEmpty());
                System.out.println("===========================================");
                System.out.println("net value :" + netValueDisplayString);
                System.out.println("vat value :" + vatTaxDisplayString);
                System.out.println("gross value :" + grosValueDisplayString);



            }
        });




    }
    private boolean isCharValid (char element){

        return (element >= '0' && element <= '9' ||
                element == '.')
                ? true : false;
    }

    private static boolean isCentCorrect (String str) {

        boolean isCorrect = true;
        double doubleRowFigure = 0;
        double doubleRoundedFigure = 0;

        if (str.isEmpty()) {
            return isCorrect;
        }else{
            doubleRowFigure = Double.parseDouble(str);
            doubleRoundedFigure = roundNumberTwoDecimals(doubleRowFigure);}

            isCorrect = (doubleRowFigure == doubleRoundedFigure);

        return isCorrect || str.isEmpty();
    }
    private static boolean isContainComa(JTextField pole, char wpis){

            boolean isOK = true;

            if (pole.getText().contains( new StringBuffer('.')) && wpis == '.') isOK = false;

            return isOK ;


    }
//    cos sie jebie w metodzie; DZIELIC !!
    private static boolean isDecimalValid (String str){
//        if (str.isEmpty()) str ="0";
//        if (str.length() == 0) {
//            System.out.println(str.length() - str.indexOf('.'));
//            return (str.isEmpty()? true : str.indexOf('.') +3 >= str.length());
//        }
//        return true

        Double doubleFigure = Double.parseDouble(str);
        int intFigure = (int) Math.round(doubleFigure);

    return true;
    }

    private static Double roundNumberTwoDecimals (double number){
        number *= 100;
        number = Math.round(number);
        number /= 100;

        return number;
    }
    private static boolean isNumberRounded (double number){

        return (roundNumberTwoDecimals(number) == number);
    }




    public static void main(String[] args) {
        new Main();





    }
}
