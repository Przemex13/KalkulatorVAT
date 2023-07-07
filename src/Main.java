import javax.swing.*;
import java.awt.event.*;

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
    public Main (){
        setTitle("Kalkulator VAT");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 230);
        setVisible(true);
        setContentPane(mainPanel);

// listeners
        netValueTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {netValueDisplayString = KeyListenerService(netValueTextField, e);}
        });
        netValueTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {netValueDisplayString = focusLostService( netValueTextField);}});

        vatTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {vatTaxDisplayString = KeyListenerService(vatTextField, e);}});
        vatTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {vatTaxDisplayString = focusLostService(vatTextField);}
        });

        grosValueTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){grosValueDisplayString = KeyListenerService(grosValueTextField, e);}});
        grosValueTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {grosValueDisplayString = focusLostService(grosValueTextField);}});

        obliczButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if ((!isTheFieldInUse(netValueTextField) && !isTheFieldInUse(vatTextField) && !isTheFieldInUse(grosValueTextField))||
                        isTheFieldInUse(netValueTextField) && isTheFieldInUse(vatTextField) && isTheFieldInUse(grosValueTextField)){

                    CustomsJDialog dialog = new CustomsJDialog();
                    dialog.dialogMessage.setText( "Aby było możliwe policzenie podatku należy zostawić jedno pole puste.");
                    dialog.pack();
                    dialog.setVisible(true);
                }
                else{

                    if(!isTheFieldInUse(netValueTextField)){
                        double grosValue = Double.parseDouble(grosValueTextField.getText());
                        double vat = Double.parseDouble(vatTextField.getText());
                        double netValue;

                        netValue = grosValue / ( 100 + vat);

                        netValueTextField.setText(String.valueOf(roundNumberTwoDecimals(netValue)));



                    }
                    if (!isTheFieldInUse(vatTextField)){
                        double grosValue = Double.parseDouble(grosValueTextField.getText());
                        double vat;
                        double netValue = Double.parseDouble(netValueTextField.getText());

                        vat = (grosValue * 100 / netValue) - 100;

                        vatTextField.setText(String.valueOf(roundNumberTwoDecimals(vat)));
                    }

                    if (!isTheFieldInUse(grosValueTextField)){
                        double grosValue;
                        double vat = Double.parseDouble(vatTextField.getText());
                        double netValue = Double.parseDouble(netValueTextField.getText());

                        grosValue = netValue * (1 +  vat / 100);

                        grosValueTextField.setText(String.valueOf(roundNumberTwoDecimals(grosValue)));
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

                netValueTextField.setText("");
                vatTextField.setText("");
                grosValueTextField.setText("");


            }
        });
    }
    static String focusLostService(JTextField pole){
        String DisplayString;

        if (pole.getText().isEmpty()){
         DisplayString = "0";
            pole.setText("0.00");
            if(pole.getText().isEmpty()) pole.setText("0.00");
        }
        else{
            pole.setText(String.valueOf(roundNumberTwoDecimals(Double.valueOf(pole.getText()))));
            DisplayString = pole.getText();
            if(Double.parseDouble(pole.getText()) == 0) pole.setText("0.00");
            if (Double.parseDouble(pole.getText()) * 100 % 10 == 0 && !pole.getText().equals("0.00") ){
                String nowy = pole.getText();
                nowy += "0";
                pole.setText(nowy);
            }
        }
        return DisplayString;
    }

    private static boolean isTheFieldInUse (JTextField pole){
        return (!pole.getText().isEmpty() && !pole.getText().equals("0.00"));}
    static String KeyListenerService( JTextField pole, KeyEvent e){
        char pomocniczyNetValue =' ';
        String netValueDisplayString;
        if (isCharValid(e.getKeyChar())&& isContainComa(pole, e.getKeyChar())){
            pomocniczyNetValue = e.getKeyChar();
        }else {e.consume();}
        netValueDisplayString = pole.getText() + pomocniczyNetValue;
        return netValueDisplayString;}
    private static boolean isCharValid (char element){
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