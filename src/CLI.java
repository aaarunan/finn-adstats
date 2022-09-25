import ad.AdMap;
import ad.filesystem.AdFileHandler;
import ad.filesystem.FileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * A small CLI for notifiying the user of what the program is doing.
 */
public class CLI {
    private final Scanner scanner = new Scanner(System.in);

    public CLI() {
    }

    /**
     * Main method of class, and starts the program.
     */
    public void start() {
        System.out.println("Parsing ads from file...");
        AdMap adMap = readAds();
        if (adMap == null) {
            return;
        }
        System.out.println("Writing results...");
        writeResults(adMap);
    }

    /**
     * Read ads from 'ads.txt', and parses them.
     *
     * @return parsed ads in an AdMap.
     */

    private AdMap readAds() {
        AdMap adMap = null;
        try {
            AdFileHandler adFileHandler = new AdFileHandler();
            adMap = adFileHandler.mapAdsFromFile(new File(AdFileHandler.DEFAULT_DIR + "/ads.txt"));
            System.out.println("Done.");
        } catch (IOException | FileFormatException e) {
            System.err.println("An exception was caught during parsing! \n");
            e.printStackTrace();
        }
        return adMap;
    }

    /**
     * Writes result from a adMap to 'AdStats.txt'
     *
     * @param adMap AdMap for writing.
     */
    private void writeResults(AdMap adMap) {
        String fileName = "AdStats.txt";
        File file = new File(AdFileHandler.DEFAULT_DIR + fileName);
        if (file.exists()) {
            System.out.println("The file '" + fileName + "' already exists, do you want to overwrite it? [y,N]");
            if (!scanner.next().equals("y")) {
                System.out.println("Aborting writing results.");
                return;
            }
        }
        try {
            AdFileHandler adFileHandler = new AdFileHandler();
            adFileHandler.writeResultToFile(adMap, file);
            //For testing
            System.out.println("Finished.");
        } catch (IOException e) {
            System.err.println("An unexpected error occurred when writing the results!");
            e.printStackTrace();

        }
    }
}
