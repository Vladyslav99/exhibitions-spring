package my.spring.exhibitions.util;

public class ImagePathFormatterUtil {
    private static final String TO_APPEND_PATH = "\\pictures\\";

    public static String formatPath(String path){
        return TO_APPEND_PATH + path;
    }
}
