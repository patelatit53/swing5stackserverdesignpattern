package stack.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Stack;


public class ServerProgram {
    public static void main(String[] args) throws Exception{
        System.out.println("Server Signing ON");
        Stack s1 = new Stack();
        ServerSocket ss  = new ServerSocket(9081);
        Socket soc = ss.accept();
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
        String str = nis.readLine();
        while(!str.equals("End")){
            System.out.println("Server received "+ str);
            try{
               if(str.equals("pop")){
                   int v1 = (int) s1.pop();
                   System.out.println("poped,"+ v1 + ","+s1.toString());
                   nos.println("poped,"+ v1 + ","+s1.toString());
               }
               else{
                   System.out.println("Entering push operation");
                   int v2 = Integer.parseInt(str);
                   s1.push(v2);
                   System.out.println("pushed,"+v2+"," + s1.toString());
                   nos.println("pushed,"+v2+"," + s1.toString());
               }
            }catch(Exception e){System.out.println("Exception caught");}
            
            str = nis.readLine();
        }
        nos.println("End");
        System.out.println("Server Signing OFF");   
    }
}

