package config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    public final static String PATH_DB_FILE = "src\\db\\DB.xml";
    public final static String PATH_DB_FOLDER = "src\\db";
    public final static String PATH_CONFIG_FILE_NAME = "src\\config\\Config.props";
    public final static String PATH_CONFIG_FILE_DESCRIPTION = "Configuracion de la aplicacion";


    public void CreateConfigFile() {
        Properties config = new Properties();

        config.setProperty("username", "admin");
        config.setProperty("password", "admin");

        try {
            config.store(new FileOutputStream(PATH_CONFIG_FILE_NAME), PATH_CONFIG_FILE_DESCRIPTION);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean LoadConfigFile(String _user, String _pass) {
        Properties config2 = new Properties();

        try {
            config2.load(new FileInputStream(PATH_CONFIG_FILE_NAME));

            String username = config2.getProperty("username");
            String password = config2.getProperty("password");

            if (username.equals(_user) && password.equals(_pass)) {
                return true;
            } else {
                return false;
            }

        } catch (IOException e) {
            return false;
        }
    }
}


