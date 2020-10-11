/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientApplication;

import java.io.*;
import java.net.*;

/**
 *
 * @author Ginevra
 */
public class Client {
    String nome_server="localhost";
    int porta_server=6789;
    Socket miosocket;
    BufferedReader input_tastiera;
    String nome_op, val1, val2,risposta,fine;
    DataOutputStream outVersoServer;
    BufferedReader InDalServer;
    boolean continua=true;
    public Socket connetti(){
        System.out.println("Client in esecuzione.");
        try {
            input_tastiera=new BufferedReader(new InputStreamReader(System.in));
            miosocket=new Socket(nome_server,porta_server);
            outVersoServer=new DataOutputStream(miosocket.getOutputStream());
            InDalServer=new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
        }
        catch(UnknownHostException e){
            System.err.println("Host sconosciuto.");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione!");
            System.exit(1);
        }
        return(miosocket);
    }
    public void comunica(){
        try {
            //while(continua)
            //{
                System.out.println("Quale operazione vuoi fare?");
                System.out.println("• addizione");
                System.out.println("• sottrazione");
                System.out.println("• moltiplicazione");
                System.out.println("• divisione");
                nome_op=input_tastiera.readLine();
                System.out.println("Invio del messaggio al server.");
                outVersoServer.writeBytes(nome_op+'\n');
                //////////////////////////
                System.out.println("Quale è il primo numero?");
                val1=(input_tastiera.readLine());
                outVersoServer.writeBytes(val1+'\n');
                System.out.println("Quale è il secondo numero?");
                val2= (input_tastiera.readLine());
                outVersoServer.writeBytes(val2+'\n');
                ///////////////////////////
                risposta=InDalServer.readLine();
                System.out.println("Risposta del server: "+risposta);
                /*System.out.println("Se vuoi uscire digita 'esci', se vuoi continuare digita 'continua'");
                fine=input_tastiera.readLine();
                outVersoServer.writeBytes(fine+'\n');
                String appo;
                appo=InDalServer.readLine();
                System.out.print(appo);
                if(appo.equals("esci"))
                {
                    continua=false;
                }
                else if(appo.equals("continua"))
                {}
                System.out.println("dopo if di escie  continua");
            }*/
            
            System.out.println("Chiusura dell'esecuzione.");
            miosocket.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server.");
            System.exit(1);
        }
    }
}
