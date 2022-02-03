package zad1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Futil {
    public static void processDir(String dirName, String resultFileName) {
    List<Path> pathList = new ArrayList<>();
    try {
        pathList.addAll(Files.walk(Paths.get(dirName)).filter((e) -> Files.isRegularFile(e)).collect(Collectors.toList()));
        FileChannel fileChannel2 = FileChannel.open(Paths.get(resultFileName), StandardOpenOption.CREATE,
                StandardOpenOption.WRITE, StandardOpenOption.READ);
        pathList.forEach((e) -> {
            try {
                FileChannel fileChannel1 = FileChannel.open(e);
                ByteBuffer b = ByteBuffer.allocate(2048);
                fileChannel1.read(b);
                b.clear();
                CharBuffer charBuffer =  Charset.forName("Cp1250").decode(b);
                fileChannel2.write(Charset.forName("UTF-8").encode(charBuffer));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    } catch (IOException e) {
        e.printStackTrace();
    }


}

}