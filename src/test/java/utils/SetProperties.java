package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SetProperties {

    private String api_key;
    private String url_host;
    private String username;
    private String password;

    private String media_id;


    public SetProperties(){
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/test/java/resources/data.properties.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setApi_key(properties.getProperty("api_key"));
        setUrl_host(properties.getProperty("url_host"));
        setUsername(properties.getProperty("username"));
        setPassword(properties.getProperty("password"));
        setMedia_id(properties.getProperty("media_id"));
    }



    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getUrl_host() {
        return url_host;
    }

    public void setUrl_host(String url_host) {
        this.url_host = url_host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }


}
