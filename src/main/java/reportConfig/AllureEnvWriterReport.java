package reportConfig;

import commons.GlobalConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AllureEnvWriterReport {

    public static void writeEnvironment(String projectRoot) {
        try {
            String folder = projectRoot + File.separator + "target/allure-results";
            File dir = new File(folder);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String filePath = folder + File.separator + "environment.properties";
            Properties props = new Properties();
            props.setProperty("Project", "Ecommerce");
            props.setProperty("Tester", "Phuong Nguyen");
            props.setProperty("Environment", "Staging");
            props.setProperty("Browser", "Chrome");
            props.setProperty("OS", GlobalConstants.OS_NAME);
            props.setProperty("Java.Version", GlobalConstants.JAVA_VERSION);

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                props.store(fos, "Allure environment");
            }
            System.out.println("Allure environment file written: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

