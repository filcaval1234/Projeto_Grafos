/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package Grafo.Interface;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
/**
 *
 * @author fc.corporation
 
public class EstudoDeInterface extends JFrame{
    private JTextField interfaceInput;
    private JButton buttonCalculate;
    
    public EstudoDeInterface(){
        super("calculadora");
        setLayout(new FlowLayout());
        
        interfaceInput = new JTextField("int a (operação) int b");
        add(interfaceInput);
        
        buttonCalculate = new JButton("calculate");
        add(buttonCalculate);
        
        ActionHandler actionHandler = new ActionHandler();
        buttonCalculate.addActionListener(actionHandler);
    }
    private int soma(int a, int b){
        return a+b;
    }
    private class ActionHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = "";
            if(e.getSource() == buttonCalculate){
                input = interfaceInput.getText();
                String a = input.substring(input.indexOf("+"));
                input = input.replace(" ", "");
                String b = input.substring(input.indexOf("+"), input.length());
                
                JOptionPane.showMessageDialog(null, soma(Integer.parseInt(a),
                        Integer.parseInt(b)));
            }
        }
        
    }
    public static void main(String[] args) {
        EstudoDeInterface estu = new EstudoDeInterface();
        estu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        estu.setSize(300,300);
        estu.setVisible(true);
    }
}
*/