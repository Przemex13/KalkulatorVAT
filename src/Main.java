import javax.swing.*;
import javax.swing.text.Caret;
import java.awt.event.*;
import java.util.Arrays;

public class Main extends JFrame{
    private JPanel mainPanel;
    private JLabel kalkulatorProcentowPanel;
    JTextField netValueTextField;
    JTextField vatTextField;
    JTextField grosValueTextField;
    private JButton obliczButton;
    private JPanel panel;
    private JButton clearButton;
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

                if (netValueDisplayString.isEmpty()) {
                    isTexdFieldInUse[0] = false;
                }else {
                    isTexdFieldInUse[0] = true;
                }
            }
        });
        netValueTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {}

            @Override
            public void focusLost(FocusEvent e) {

                if (netValueTextField.getText().isEmpty()){
                    netValueDisplayString = "0";
                    netValueTextField.setText("0.00");
                    if(netValueTextField.getText().isEmpty()) netValueTextField.setText("0.00");
                }
                else{
                    netValueTextField.setText(String.valueOf(roundNumberTwoDecimals(Double.valueOf(netValueTextField.getText()))));
                    netValueDisplayString = netValueTextField.getText();
                    if(Double.parseDouble(netValueTextField.getText()) == 0) netValueTextField.setText("0.00");
                    if (Double.parseDouble(netValueTextField.getText()) * 100 % 10 == 0 && !netValueTextField.getText().equals("0.00") ){
                        String nowy = netValueTextField.getText();
                        nowy += "0";
                        netValueTextField.setText(nowy);
                    }
                }

                System.out.println("focus lost");
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
                    vatTaxDisplayString = "0";
                    vatTextField.setText("0.00");
                    if(vatTextField.getText().isEmpty()) vatTextField.setText("0.00");
                }
                else{
                    vatTextField.setText(String.valueOf(roundNumberTwoDecimals(Double.valueOf(vatTextField.getText()))));
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
            }
        });

        grosValueTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {}

            @Override
            public void focusLost(FocusEvent e) {
                if (grosValueTextField.getText().isEmpty()){
                    grosValueDisplayString = "0";
                    grosValueTextField.setText("0.00");
                    if(grosValueTextField.getText().isEmpty()) grosValueTextField.setText("0.00");
                }
                else{
                    grosValueTextField.setText(String.valueOf(roundNumberTwoDecimals(Double.valueOf(grosValueTextField.getText()))));
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





                if (!isTheFieldInUse(netValueTextField) && !isTheFieldInUse(vatTextField) && !isTheFieldInUse(grosValueTextField)){

                    CustomsJDialog dialog = new CustomsJDialog();
                    dialog.netValueMessage.setText( "Aby było możliwe policzenie podatku należy zostawić jedno pole puste.");
                    dialog.pack();
                    dialog.setVisible(true);
                }
                else{

                    if(!isTheFieldInUse(netValueTextField)){
                        double grosValue = Double.parseDouble(grosValueTextField.getText());
                        double vat = Double.parseDouble(vatTextField.getText());
                        double netValue;

                        netValue = grosValue / ( 100 + vat);

                        netValueTextField.setText(String.valueOf(netValue));



                    }
                    if (!isTheFieldInUse(vatTextField)){
                        double grosValue = Double.parseDouble(grosValueTextField.getText());
                        double vat;
                        double netValue = Double.parseDouble(netValueTextField.getText());

                        vat = (grosValue * 100 / netValue) - 100;

                        vatTextField.setText(String.valueOf(vat));
                    }

                    if (!isTheFieldInUse(grosValueTextField)){
                        double grosValue;
                        double vat = Double.parseDouble(vatTextField.getText());
                        double netValue = Double.parseDouble(netValueTextField.getText());

                        grosValue = netValue * (1 +  vat / 100);

                        grosValueTextField.setText(String.valueOf(grosValue));
                    }
                }





                System.out.println("===========================================");
                System.out.println("net value :" + netValueDisplayString);
                System.out.println("vat value :" + vatTaxDisplayString);
                System.out.println("gross value :" + grosValueDisplayString);
            }
        });

        clearButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("clear");
                netValueTextField.setText("");
                vatTextField.setText("");
                grosValueTextField.setText("");
                netValueTextField.requestFocus();


            }
        });
    }
    private static int whichFieldIsUnknown( Main glowneOkno){
        if (isTheFieldInUse(glowneOkno.netValueTextField)) return 1;
        if (isTheFieldInUse(glowneOkno.vatTextField)) return 2;
        if (isTheFieldInUse(glowneOkno.grosValueTextField)) return 3;
        return 4;
    }
    private static boolean isTheFieldInUse (JTextField pole){

        return (!pole.getText().isEmpty() && !pole.getText().equals("0.00"));
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
