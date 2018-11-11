package client.view;

import java.util.Scanner;

public class CommandLineIntegration {
    private final Scanner cmdReader = new Scanner(System.in);


    public synchronized void safePrintln(String text){

        System.out.println(text);

    }

    public synchronized void safePrint(String text){

        System.out.print(text);
    }

    public String readNextLine(){
        return cmdReader.nextLine();

    }


}
