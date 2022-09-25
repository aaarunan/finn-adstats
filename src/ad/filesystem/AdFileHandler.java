package ad.filesystem;

import ad.Ad;
import ad.AdMap;

import java.io.*;

/**
 * Handles ad files from the filesystem. The class parses ads from files, and writes results swell.
 */
public class AdFileHandler {
    public final static String DEFAULT_DIR = System.getProperty("user.dir") + "/resources/";
    private int lineNr = 1;

    public AdFileHandler() {

    }

    /**
     * Parses all ads in a file, and adds them into a AdMap
     *
     * @param file file that should be parsed
     * @return Parsed AdMap
     * @throws FileFormatException if the file is wrongly formatted
     * @throws IOException         if and I/O exception occurred
     */
    public AdMap mapAdsFromFile(File file) throws FileFormatException, IOException {
        AdMap adMap = new AdMap();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            lineNr = 1;
            //parse the ads
            while ((line = reader.readLine()) != null) {
                adMap.map(parseLine(line));
                parseLine(line);
                lineNr++;
            }
        }

        return adMap;
    }

    /**
     * A helper function that parses a line from a file.
     * Constructs an Ad if all the fields in the file is valid.
     *
     * @param line line that is being parsed
     * @return Constructed Ad
     * @throws FileFormatException if the line is wrongly formatted.
     */

    private Ad parseLine(String line) throws FileFormatException {
        String[] values = line.split(",");
        String realAdType;
        long adId;
        double adPrice;

        if (values.length > 3) {
            throw new FileFormatException("Too many fields on line: " + lineNr);
        }

        //try to parse the values
        try {
            adId = Long.parseLong(values[0].replaceAll("\\s+", ""));
            realAdType = values[1].replaceAll("\\s+", "");
            adPrice = Double.parseDouble(values[2].replaceAll("\\s+", ""));

        } catch (NumberFormatException e) {
            throw new FileFormatException("Could not parse numbers on line :" + lineNr);
        } catch (IndexOutOfBoundsException e) {
            throw new FileFormatException("Too few fields on line: " + lineNr);
        }

        Ad ad;

        //try to construct the ads
        try {
            ad = new Ad(adId, realAdType, adPrice);
        } catch (IllegalArgumentException e) {
            throw new FileFormatException(String.format("%s on Line: %d", e.getMessage(), lineNr));
        }

        return ad;
    }


    public void writeResultToFile(AdMap adMap, File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(adMap.toString());
        }
    }
}
