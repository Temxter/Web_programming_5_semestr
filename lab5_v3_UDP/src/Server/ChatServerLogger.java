package Server;

import java.io.FileWriter;
import java.io.IOException;

public class ChatServerLogger {

    static public void connections(String s) {
        try (FileWriter fw = new FileWriter("connections.log", true)) {
            fw.write(s);
            fw.flush();
        } catch (IOException ex) {
            System.err.println("Logger failed to open file: " + ex.getMessage());
        }
    }


    static public void console(String s) {
        System.out.println(s);
    }

    synchronized static void logFile(){

    }
}
