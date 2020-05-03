package Lab2_3;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class FolderProcessor{
    private final ExecutorService pool;
    public String[] processedFiles;

    public FolderProcessor(ExecutorService pool){
        this.pool = pool;
    }

    void processFolder(String inputPath) {
        File inputFolder = new File(inputPath);
        if (inputFolder.isFile()) return;

        processedFiles = inputFolder.list();
        assert processedFiles != null;
        for (String filename : processedFiles) {
            String filePath = inputFolder.toPath().resolve(filename).toString();
            if (new File(filePath).isFile()) {
                pool.execute (() -> {
                    PrintWriter outputStream;
                    BufferedReader inputStream;
                    ArrayList<String> tokens = new ArrayList<>();
                    ArrayList<String> oneLine = new ArrayList<>();
                    StringBuilder oneLineString = new StringBuilder();
                    String output;
                    try {
                        inputStream = new BufferedReader(new FileReader(filePath));
                        System.out.println("Зчитування даних з файлу.");
                        while ((output = inputStream.readLine()) != null) {
                            for (var o : output.split(" ")) {
                                if (3 <= o.length() && o.length() <= 5)
                                    continue;
                                oneLine.add(o);
                            }
                            for (var s:oneLine) {
                                oneLineString.append(s).append(" ");
                            }
                            oneLineString.append("\r\n");
                            tokens.add(oneLineString.toString());
                            oneLineString = new StringBuilder();
                            oneLine.clear();
                        }
                        inputStream.close();
                        outputStream = new PrintWriter(new FileWriter(filePath));
                        for (var t : tokens) {
                            outputStream.print(t + " ");
                        }
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }
}
