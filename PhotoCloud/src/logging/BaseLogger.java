package logging;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The BaseLogger class is to log the events. It writes the information with the time stamps 
 * on the info_log_file and the errors on the error_log_file.
 */
public class BaseLogger implements Serializable {
    private static final String INFO_LOG_FILE = "application_info.txt";
    private static final String ERROR_LOG_FILE = "application_error.txt";
    private static final BaseLogger INSTANCE = new BaseLogger();

    private static BufferedWriter infoWriter;
    private BufferedWriter errorWriter;
    private static SimpleDateFormat dateFormat;  
  

    /**
     * Constructs a new instance of the BaseLogger class.
     * The constructor initializes the log writers and sets up a shutdown hook to close the writers 
     * automatically when the application is shut down.
     */
    private BaseLogger() {
    	
        try {
            infoWriter = new BufferedWriter(new FileWriter(INFO_LOG_FILE, true));
            errorWriter = new BufferedWriter(new FileWriter(ERROR_LOG_FILE, true));
            dateFormat = new SimpleDateFormat("[E MMM dd HH:mm:ss z yyyy]");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Register the shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logInfo("Application shutdown at " + dateFormat.format(new Date()));
            closeWriters();
        }));
    }
    
    /**
     * Returns an instance of the BaseLogger class.
     *
     * @return The singleton instance
     */
    public static BaseLogger getInstance() {
        return INSTANCE;
    }

    /**
     * Logs an informational message.
     *
     * @param message The message to log
     */
    public static void logInfo(String message) {
        log(message, infoWriter);
    }


    /**
     * Logs an error message.
     *
     * @param message The error message to log
     */
    public void logError(String message) {
        log(message, errorWriter);
    }

    private static void log(String message, BufferedWriter writer) {
        try {
            String timestamp = dateFormat.format(new Date());
            String formattedMessage = String.format("%s [INFO] %s", timestamp, message);
            writer.write(formattedMessage);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Closes the log writers.
     */
    public void closeWriters() {
        try {
            if (infoWriter != null) {
                infoWriter.close();
            }
            if (errorWriter != null) {
                errorWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
