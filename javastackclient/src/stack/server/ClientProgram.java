
package stack.server;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import javax.swing.*;

public class ClientProgram {

    public static void main(String[] args) throws Exception{
        Socket soc = new Socket("127.0.0.1",9081);
        PrintWriter nos = new PrintWriter(
                              new BufferedWriter(
                                  new OutputStreamWriter(
                                          soc.getOutputStream()
                                  )
                              )
        ,true);
        BufferedReader nis = new BufferedReader(
                                 new InputStreamReader(
                                     soc.getInputStream()
                                 )
        );
        JFrame f1 = new JFrame("Stack");
        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        JLabel label1 = new JLabel("No Operation Performed Yet");
        JButton b1 = new JButton("Push");
        JTextField tf = new JTextField(15);
        JButton b2 = new JButton("Pop");
        JPanel p = new JPanel();
        p.add(b1);
        p.add(tf);
        p.add(b2);
        f1.add(BorderLayout.NORTH,label1);
        f1.add(ta);
        f1.add(BorderLayout.SOUTH,p);
        f1.setSize(400,400);
        f1.setVisible(true);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b1.addActionListener((ActionEvent e)->{  
           String str = tf.getText();
           if(!str.trim().equals("")){
               if(str.equals("End")){
                   nos.println("End");
               }
               else {
                   try{
                      tf.setText("");
                      nos.println(Integer.parseInt(str));
                      label1.setText("The Last item pushed was " + str);
                   }
                   catch(NumberFormatException ex){
                       label1.setText(
                           "Please enter a valid Number as Input..."
                       );
                   }
               }
           }
        });
        b2.addActionListener((e)->{
           nos.println("pop");
        });
        b2.setEnabled(false);
        String str = nis.readLine().trim();
        while(!str.equals("End")){
            StringTokenizer param = new StringTokenizer(str, ",");
            String command = param.nextToken();
            String value = param.nextToken();
            String status = param.nextToken();
            String data = param.nextToken();
            switch(status){
                case "full"  : b1.setEnabled(false);
                               b2.setEnabled(true);
                               break;
                case "empty" : b2.setEnabled(false);
                               b1.setEnabled(true);
                               break;
                case "normal": b2.setEnabled(true);
                               b1.setEnabled(true);
                               break;
            }
            StringTokenizer display = new StringTokenizer(data,"::");
            data = "";
            while(display.hasMoreTokens()){
                data = data+display.nextToken()+"\n";
            }
            ta.setText(data);
            label1.setText("The last value "+command+" was "+value);
            str = nis.readLine().trim();
        }
        System.exit(1);
    }   
}