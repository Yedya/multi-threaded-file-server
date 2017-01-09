package main;


import main.Parser.ContextBuildException;
import main.Parser.ContextBuilder;
import main.Parser.XMLParser;

public class ServerRunner {
    public static void main(String[] args) {
        ContextBuilder ctx = new XMLParser("config.xml");

        try{
            System.out.println(ctx.buildContext());
        }catch(ContextBuildException e){
            System.out.println("lol");
        }

    }
}
