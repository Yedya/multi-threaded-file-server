package main.Parser;

import java.util.Map;

public interface ContextBuilder {
    Map<String, String> buildContext() throws ContextBuildException;
}
