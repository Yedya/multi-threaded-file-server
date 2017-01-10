package main.Configuration;

import java.util.Map;

public interface ConfigurationBuilder {
    Map<String, String> build() throws ConfigurationBuildException;
}
