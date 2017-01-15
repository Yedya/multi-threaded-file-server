package main.client.configuration;

/**
 * Created by seanking on 15/01/2017.
 */
public class Configuration {
    private String serverHost;
    private String downloadDirectory;
    private int serverPort;
    private String username;

    public Configuration(String serverHost, String downloadDirectory, int serverPort, String username){
        this.serverHost =serverHost;
        this.downloadDirectory = downloadDirectory;
        this.serverPort = serverPort;
        this.username = username;
    }

    public String getServerHost() {
        return serverHost;
    }

    public String getDownloadDirectory() {
        return downloadDirectory;
    }

    public int getServerPort() {
        return serverPort;
    }

    public String getUsername() {
        return username;
    }
}
