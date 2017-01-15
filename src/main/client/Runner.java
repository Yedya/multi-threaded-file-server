package main.client;

import main.client.configuration.ConfigurationBuilder;
import main.client.configuration.XMLConfigurationBuilder;
import main.client.controllers.ClientController;

import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        //Needs to throw an error if the file does not exist
        ConfigurationBuilder configBuilder = new XMLConfigurationBuilder("config.xml");
        //ClientController constructor takes a configBuilder. ClientController delegates parsing of file to configBuilder
        //ClientController Has a generic ConfigurationBuilder which can either be JSON or XML
        ClientController client = new ClientController(configBuilder);
        client.configure();

        boolean RUNNING = true;
        Scanner console = new Scanner(System.in);
        int userInput;
        String fileName = null;

        while(RUNNING){

            //Menu
            System.out.println("1: Connect to server \n 2: Display File Listing \n 3: Download File \n 4: Exit");
            userInput = console.nextInt();

            switch(userInput){
                case 1:
                    // client.connect();
                    break;
                case 2:
                    //client.displayFiles();
                    break;
                case 3:
                    fileName = console.next();
                    //client.downloadFile(fileName);
                case 4:
                    //client.exit();
                    RUNNING = false;
                    break;
            }
        }
    }
}
