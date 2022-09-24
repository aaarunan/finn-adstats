import ad.AdMap;
import ad.filesystem.AdFileHandler;
import ad.filesystem.FileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CLI {
    private final Scanner scanner = new Scanner(System.in);

    public CLI() {
    }

    public void start() {
        System.out.println("Parsing ads from file...");
        AdMap adMap = readAds();
        if (adMap == null) {
            return;
        }
        System.out.println("Writing results...");
        writeResults(adMap);
    }

    private AdMap readAds() {
        AdMap adMap = null;
        try {
            AdFileHandler adFileHandler = new AdFileHandler();
            adMap = adFileHandler.loadAdsFromFile(new File(AdFileHandler.DEFAULT_DIR + "/ads.txt"));
            System.out.println("Done.");
        } catch (IOException | FileFormatException error) {
            System.err.println("Something went wrong with the parsing! \n");
            System.err.println(error.getMessage());
        }
        return adMap;
    }

    private void writeResults(AdMap adMap) {
        File file = new File(AdFileHandler.DEFAULT_DIR + "AdStats1.txt");
        if (file.exists()) {
            System.out.println("This file already exists, do you want to overwrite it? [y,N]");
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
            System.err.println(e.getMessage());

        }
    }
}
