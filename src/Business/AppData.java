package Business;

import data.TextFileIO;

import java.io.IOException;

public class AppData {
    private static AppData appData = null;

    private AppData() {

    }

    public static AppData getAppData() {
        if (appData == null) {
            appData = new AppData();
        }
        return appData;
    }

    public String[] readFile() {
        String wholeFile = TextFileIO.ReadFile();
        String[] lines = wholeFile.split("\n");
        return lines;
    }

    public void writeRecord(CovidRecord record) throws IOException {
        TextFileIO.writeData(record.toString());
    }
}
