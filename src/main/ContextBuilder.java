package main;

import java.util.Map;

public interface ContextBuilder {
    Map<String, String> buildContext() throws ContextBuildException;
}
