package Lab5;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class Notepad {

    private ArrayList<Record> records = new ArrayList<>();

    public static class Record {
        public String Info;
        public LocalDateTime date;

        public Record(String info) {
            Info = info;
            date = LocalDateTime.now();
        }

        @Override
        public String toString() {
            return Info + " | Дата: " + date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + " " + date.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        }
    }

    public void addRecord(Record record) {
        if (!records.contains(record)) {
            records.add(record);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-------Блокнот-------\n");
        for (var r:
             records) {
            stringBuilder.append(r.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
