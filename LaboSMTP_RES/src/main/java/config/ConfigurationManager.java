package config;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigurationManager {
    private static Logger logger = Logger.getLogger(ConfigurationManager.class.getName());
    private String smtpServerAddress;
    private int smtpServerPort;
    private int numberOfGroups;
    private String witnessesToCC;

    public ConfigurationManager(String server, int port, int numberOfGroups, String witnessesToCC) {
        this.smtpServerAddress = server;
        this.smtpServerPort = port;
        this.numberOfGroups = numberOfGroups;
        this.witnessesToCC = witnessesToCC;
    }

    public ConfigurationManager() {
        this("", 25, 1, "");
    }


    /**
     * Return a properties for the campaign or null if we couldn't found all
     * @param f : File containing the properties
     * @return a ConfigurationManager or null
     */
    public void getPropValuesFrom(File f) {
        try(InputStreamReader isr = new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8)){
            Properties prop = new Properties();

            prop.load(isr);

            this.smtpServerAddress = prop.getProperty("smtpServerAddress");
            this.smtpServerPort = Integer.parseInt(prop.getProperty("smtpServerPort"));
            this.numberOfGroups = Integer.parseInt(prop.getProperty("numberOfGroups"));
            this.witnessesToCC = prop.getProperty("witnessesToCC");
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    public int getSmtpServerPort() {
        return smtpServerPort;
    }

    public String getSmtpServerAddress() {
        return smtpServerAddress;
    }

    public String getWitnessesToCC() {
        return witnessesToCC;
    }

    @Override
    public String toString() {
        return "ConfigurationManager {\n" +
                "\tsmtpServerAddress = '" + smtpServerAddress + '\'' +
                ", \n\tsmtpServerPort = " + smtpServerPort +
                ", \n\tnumberOfGroups = " + numberOfGroups +
                ", \n\twitnessesToCC = '" + witnessesToCC + '\'' +
                "\n}";
    }
}
