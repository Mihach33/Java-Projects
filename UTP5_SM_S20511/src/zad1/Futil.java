package zad1;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Futil {

    static String src = "Windows-1250";
    static Charset dest = StandardCharsets.UTF_8;

    public static void processDir(String dirName, String resultFileName) {
        try (BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(new File(resultFileName)), dest))) {
            FileVisitor<Path> simpleFileVisitor = new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path visitedFilePath, BasicFileAttributes fileAttributes)
                        throws IOException {
                    try (BufferedReader in = new BufferedReader(
                            new InputStreamReader(new FileInputStream(visitedFilePath.toFile()), src))) {
                        String str;
                        while ((str = in.readLine()) != null) {
                            out.write(str + "\n");
                        }
                    }

                    return FileVisitResult.CONTINUE;
                }
            };


            FileSystem fileSystem = FileSystems.getDefault();
            Path rootPath = fileSystem.getPath(dirName);
            Files.walkFileTree(rootPath, simpleFileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
