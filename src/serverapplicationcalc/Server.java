/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplicationcalc;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author Ginevra
 */
public class Server {
    ServerSocket server= null;
    Socket client=null;
    String nome_op=null;
    double val1,val2, ris;
    String risultato=null;
    BufferedReader InDalClient;
    DataOutputStream outVersoClient;
    boolean continua=true;
    public Socket attendi() 
    {
        try
        {
            System.out.println("SERVER partito in esecuzione");
            server=new ServerSocket(6789);
            client=server.accept();
            server.close();
            InDalClient= new  BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient= new DataOutputStream(client.getOutputStream());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server!");
            System.exit(1);
        }
        return client;
    }
    public void comunica()
    {
        try
        {
            //while(continua)
            //{
                System.out.println("benvenuto client, quale operazione vuoi fare?. Attendo...");
                nome_op=InDalClient.readLine();
                System.out.println("l'operazione scelta e' : "+nome_op);
                val1=Integer.parseInt(InDalClient.readLine());
                val2=Integer.parseInt(InDalClient.readLine());
                if(nome_op.equals("addizione"))
                {
                    addizione(val1,val2);
                    System.out.println("dentro if");
                }
                else if(nome_op.equals("sottrazione"))
                {
                    sottrazione(val1,val2);
                }
                else if(nome_op.equals("moltiplicazione"))
                {
                    moltiplicazione(val1,val2);
                }
                else if(nome_op.equals("divisione"))
                {
                    divisione(val1,val2);
                }
               System.out.println("invio il risultato al client...");
               risultato= Double.toString(ris);
               outVersoClient.writeBytes(risultato+'\n');

               System.out.println("SERVER: fine elaborazione ");
               /*String appo;
               appo=InDalClient.readLine();
               if(appo.equals("esci"))
               {
                   continua=false;
                   outVersoClient.writeBytes(appo);
               }
               else if(appo.equals("continua"))
               {
                   outVersoClient.writeBytes(appo);
               }
            }*/
            client.close();
        }
        catch(Exception e)
        {
                  System.out.println("ERRORE");
        }
    }
    public double addizione(double x,double y)
    {
        ris=x+y;
        System.out.println("la risposta è"+ ris);
        return ris;
    }
    public double sottrazione(double x,double y)
    {
        ris=x-y;
        System.out.println("la risposta è"+ ris);
        return ris;
    }
    public double moltiplicazione(double x,double y)
    {
        ris=x*y;
        System.out.println("la risposta è"+ ris);
        return ris;
    }
    public double divisione(double x,double y)
    {
        ris=x/y;
        System.out.println("la risposta è"+ ris);
        return ris;
    }
}
