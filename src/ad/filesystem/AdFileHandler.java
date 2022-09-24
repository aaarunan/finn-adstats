package ad.filesystem;

import ad.Ad;
import ad.AdMap;

import java.io.*;

public class AdFileHandler {
    public final static String FILETYPE = "txt";
    public final static String DEFAULT_DIR = System.getProperty("user.dir") + "/resources/";
    private int lineNr = 1;

    public AdFileHandler() {

    }

    public AdMap loadAdsFromFile(File file) throws FileFormatException, IOException {
        //Check if file is csv
        if (!isCorrectFileExtension(file.toString())) {
            throw new FileFormatException(String.format("File '%s' is not csv.", file.getName()));
        }

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
     * A helper function that parses a line from a file. It uses regex to clean the values and checks if the health and
     * count values can be parsed to int.
     *
     * @param line line that is being parsed
     * @return pares values
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

    /**
     * Helper method for checking if file is the correct filetype.
     *
     * @param fileName file that is checked
     * @return true if csv
     */

    protected boolean isCorrectFileExtension(String fileName) {
        int index = fileName.lastIndexOf('.');

        if (index <= 0) {
            return false;
        }

        String extension = fileName.substring(index + 1);
        return extension.equals(FILETYPE);
    }
}
