package main;


public class ContextBuildException extends Exception{
    String s1;

    ContextBuildException(String s2) {
        s1 = s2;
    }

    @Override
    public String toString() {
        return ("Error building context: "+s1);
    }
}
