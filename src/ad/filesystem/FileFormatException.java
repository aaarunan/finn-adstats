package ad.filesystem;

/**
 * Exception is thrown when the file is wrongly formatted.
 */
public class FileFormatException extends Exception {

    public FileFormatException(String message) {
        super(message);
    }
}
