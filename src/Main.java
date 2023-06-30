import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends JFrame{
    private JPanel mainPanel;
    private JLabel kalkulatorProcentowPanel;
    private JTextField netValueTextField;
    private JTextField vatTextField;
    private JTextField grosValueTextField;
    private JButton obliczButton;
    private JPanel panel;


    private String netValueDisplayString;
    private String vatTaxDisplayString;
    private String grosValueDisplayString;

    public static boolean[]isTexdFieldInUse = new boolean[3];






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
                if (Double.parseDouble(netValueDisplayString) == 0) isTexdFieldInUse[0] = false;
                else isTexdFieldInUse[0] = true;
            }
        });
        netValueTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {}

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
                    if (Double.parseDouble(netValueTextField.getText()) * 100 % 10 == 0 && !netValueTextField.getText().equals("0.00") ){
                        String nowy = netValueTextField.getText();
                        nowy += "0";
                        netValueTextField.setText(nowy);
                    }
                }
            }
        });



        vatTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                char pomocniczyVatTax =' ';

                if (isCharValid(e.getKeyChar()) && isContainComa(vatTextField, e.getKeyChar())){
                    pomocniczyVatTax = e.getKeyChar();
                }else {
                    e.consume();
                }
                vatTaxDisplayString = vatTextField.getText() + pomocniczyVatTax;
            }
        });

        vatTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {}

            @Override
            public void focusLost(FocusEvent e) {


                if (vatTextField.getText().isEmpty()){
                    vatTaxDisplayString ="0";
                    vatTextField.setText("0.00");
                    if(vatTextField.getText().isEmpty()) vatTextField.setText("0.00");

                }
                else{
                    vatTextField.setText(String.valueOf(roundNumberTwoDecimals(Double.valueOf(vatTaxDisplayString))));
                    vatTaxDisplayString = vatTextField.getText();
                    if(Double.parseDouble(vatTextField.getText()) == 0) vatTextField.setText("0.00");
                    if (Double.parseDouble(vatTextField.getText()) * 100 % 10 == 0 && !vatTextField.getText().equals("0.00") ){
                        String nowy = vatTextField.getText();
                        nowy += "0";
                        vatTextField.setText(nowy);
                    }
                }
            }
        });



        grosValueTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                char pomocniczyGrosValue =' ';

                if (isCharValid(e.getKeyChar())&& isContainComa(grosValueTextField, e.getKeyChar())){
                    pomocniczyGrosValue = e.getKeyChar();
                }else {
                    e.consume();
                }
                grosValueDisplayString = grosValueTextField.getText() + pomocniczyGrosValue;
            }
        });

        grosValueTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {}

            @Override
            public void focusLost(FocusEvent e) {

                if (grosValueTextField.getText().isEmpty()){
                    grosValueDisplayString ="0";
                    grosValueTextField.setText("0.00");
                    if(grosValueTextField.getText().isEmpty()) grosValueTextField.setText("0.00");
                }
                else{
                    grosValueTextField.setText(String.valueOf(roundNumberTwoDecimals(Double.valueOf(grosValueDisplayString))));
                    grosValueDisplayString = grosValueTextField.getText();
                    if(Double.parseDouble(grosValueTextField.getText()) == 0) grosValueTextField.setText("0.00");
                    if (Double.parseDouble(grosValueTextField.getText()) * 100 % 10 == 0 && !grosValueTextField.getText().equals("0.00") ){
                        String nowy = grosValueTextField.getText();
                        nowy += "0";
                        grosValueTextField.setText(nowy);
                    }
                }
            }
        });

        obliczButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                System.out.println("===========================================");
                System.out.println("net value :" + netValueDisplayString);
                System.out.println("vat value :" + vatTaxDisplayString);
                System.out.println("gross value :" + grosValueDisplayString);
            }
        });
    }
    private static boolean controlWhichWindowToFreeze(boolean [] tablica){

       int trueInt = 0;
       int falseInt = 0;
       int nrPusty = -1;
       for (int i = 0; i < tablica.length; i ++){
           if (tablica[i] == true) trueInt ++;
           if (tablica[i] == false) falseInt ++;
           if(trueInt == 2){
               nrPusty = Arrays.asList(tablica).indexOf(false);

               System.out.println(nrPusty);
           }

       }

        return  false;
    }
    private boolean isCharValid (char element){

        return (element >= '0' && element <= '9' ||
                element == '.')
                ? true : false;
    }


    private static boolean isContainComa(JTextField pole, char wpis){

            boolean isOK = true;

            if (pole.getText().indexOf('.') != -1 && wpis == '.') isOK = false;
            if(pole.getText().isEmpty()) isOK = true;
            return isOK ;


    }


    private static Double roundNumberTwoDecimals (double number){
        number *= 100;
        number = Math.round(number);
        number /= 100;



        return number;
    }
        public static void main(String[] args) {
        Main main = new Main();

    }
}
