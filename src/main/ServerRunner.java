package main;


public class ServerRunner {
    public static void main(String[] args) {
        ContextBuilder ctxBuilder = new XMLParser("config.xml");

        try{
            System.out.println(ctx.buildContext());
        }catch(ContextBuildException e){
            System.out.println("lol");
        }

    }
}
