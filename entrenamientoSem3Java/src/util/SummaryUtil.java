package util;

import java.util.ArrayList;
import java.util.List;

public class SummaryUtil {
    private static List<String> summaryMessages = new ArrayList<>();

    public static void addMessage(String message) {
        summaryMessages.add(message);
    }

    public static List<String> getSummaryMessages() {
        return new ArrayList<>(summaryMessages); // Return a copy to prevent external modification
    }

    public static void clearSummary() {
        summaryMessages.clear();
    }
}
