import config.ConfigurationManager;
import model.mail.Group;
import model.mail.Message;
import model.mail.Victims;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static int MIN_VICTIM_PER_GROUP = 3;
    private static List<Message> parsingTextMessageFromFile(File f) {
        List<Message> result = new LinkedList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))){
            String content = "";
            String subject = "";
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("===")) {
                    if (!(content.isEmpty() && subject.isEmpty())) {
                        result.add(new Message(subject, content));
                        content = "";
                    }
                } else if (line.contains("Subject")) {
                    subject = line.substring(line.indexOf(':'));
                } else {
                    content += line + "\n";
                }
            }
            result.add(new Message(subject, content));
        } catch (IOException e) {
            mainLogger.log(Level.SEVERE, e.getMessage());
        }
        return result;
    }

    public static List<Victims> parsingVictimsFromFile(File f) {
        List<Victims> victims = new LinkedList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))){
            String line;
            while ((line = br.readLine()) != null) {
                victims.add(new Victims(line));
            }
        } catch (IOException e) {
        mainLogger.log(Level.SEVERE, e.getMessage());
    }
        return victims;
    }

    private static Logger mainLogger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) throws IOException {
        // récupérer les fichiers de configurations
        ConfigurationManager config = new ConfigurationManager();
        List<Message> messageFromFile = new LinkedList<>();
        List<Victims> victimsFromFile = new LinkedList<>();
        List<Group> groups = new LinkedList<>();
        if (args.length > 0) {
            String userDirectory = System.getProperty("user.dir");
            File f = new File(userDirectory + args[0]);
            if (f.isDirectory()) {
                File[] files = f.listFiles();
                for (File file : files) {
                    switch (file.getName()) {
                        case "config.properties":
                            config.getPropValuesFrom(file);
//                            System.out.println(config);
                            break;
                        case "messages.utf8":
                            messageFromFile = parsingTextMessageFromFile(file);
//                            for (Message m : messageFromFile) {
//                                System.out.println(m);
//                            }
                            break;
                        case "victims.utf8":
                            break;
                        case "victims.RES.utf8":
                            victimsFromFile = parsingVictimsFromFile(file);
//                            for (Victims v : victimsFromFile) {
//                                System.out.println(v);
//                            }
                            break;
                    }
                }
            } else {
                mainLogger.log(Level.SEVERE, "You don't provide a folder. Please provide a folder containing your files for the campaign");
            }
//            System.out.println(args[0]);
        } else {
            mainLogger.log(Level.SEVERE, "You should give an argument to this app. The argument should be a folder containing your files for the campaign");
        }
        // Divide Victims into groups
        int minNumberVictimsPerGroup = victimsFromFile.size() / config.getNumberOfGroups();
        if (minNumberVictimsPerGroup < MIN_VICTIM_PER_GROUP) {
            mainLogger.log(Level.SEVERE, "Sorry but each group should have at least 3 victims but now we have " + minNumberVictimsPerGroup);
        } else {
            mainLogger.log(Level.INFO, "Minimum number victims per groups : " + minNumberVictimsPerGroup);
            for (int i = 0; i < config.getNumberOfGroups(); i++) {
                List<Victims> temp;
                if (i == config.getNumberOfGroups() - 1){
                    temp = victimsFromFile.subList(i * minNumberVictimsPerGroup + 1, (i + 1) * minNumberVictimsPerGroup + victimsFromFile.size() % config.getNumberOfGroups());
                } else {
                    temp = victimsFromFile.subList(i * minNumberVictimsPerGroup + 1, (i + 1) * minNumberVictimsPerGroup);
                }
                groups.add(new Group(i, temp, victimsFromFile.get(i * minNumberVictimsPerGroup)));
            }

//            for (Group g : groups) {
//                System.out.println(g);
//            }

        }

    }

}
