package utils;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.newBufferedReader;

/**
 * Created by Poliakov.A on 12/24/2019.
 */
public class Utils {
    public static ArrayList<String> readFile(String path) {
        ArrayList<String> result = new ArrayList<>();
        try {
            System.out.println(path);
            result.addAll(Files.readAllLines(
                    Paths.get(path),
                    Charset.defaultCharset()));
        }
        catch (MalformedInputException e){
            try {
                result.addAll(Files.readAllLines(
                        Paths.get(path),
                        StandardCharsets.ISO_8859_1));
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<String> readAllLines(Path path, Charset cs) throws IOException {
        try (BufferedReader reader = newBufferedReader(path, cs)) {
            List<String> result = new ArrayList<>();
            for (;;) {
                String line = reader.readLine();
                System.out.println(line);
                if (line == null)
                    break;
                result.add(line);
            }
            return result;
        }
    }

    public static void writeFile(String path, ArrayList<String> stringList){
        try {
            File file = new File(path);
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdir();
            }
            Files.write(Paths.get(path), stringList, StandardOpenOption.CREATE );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFileToString(String path) {
        String result = "";
        for (String tmp : readFile(path)) {
            result = result + tmp + "\n";
        }
        return result;
    }

    public static Document getDocument(String path) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc = null;
        try {
            builder = dbFactory.newDocumentBuilder();
            doc = builder.parse(path);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static String projectPath(File file1, File file2) {
        String result = "";
        String[] path1 = file1.getAbsolutePath().split(File.separator);
        String[] path2 = file2.getAbsolutePath().split(File.separator);

        for (int i = 0; i < path1.length; i++) {
            if (path1[i].equals(path2[i])) {
                result = path1[i];
            } else break;
        }

        return result;
    }

    public static void writeToFile(String path, String value) {
        File file = new File(path);
        FileWriter out = null;
        try {
            out = new FileWriter(file, false);
            out.write(value);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // System.out.println(value);
    }

}
