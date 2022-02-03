/**
 *
 *  @author Smilianets Mykhailo S20511
 *
 */
package zad2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {

  public static void main(String[] args) throws IOException {

    ForException<String, List<String>> flines = (fileName) -> {
      List<String> lines = new ArrayList<>();

      Stream<String> stream = Files.lines(Paths.get(fileName));
        stream.forEach(lines::add);
      return lines;
    };

    ForException<List<String>, String> join = (lines) -> (String.join("", lines));

    ForException<String, List<Integer>> collectInts = (line) -> {
      List<Integer> integers = new ArrayList<>();

      for (String s : line.replaceAll("[^\\d ]", " ").trim().split(" ")) {
        if (s.length() > 0)
          integers.add(Integer.valueOf(s));
      }

      return integers;
    };

    ForException<List<Integer>, Integer> sum = (integers) -> (integers.stream().mapToInt(i -> i).sum());


    String fname = System.getProperty("user.home") + "/LamComFile.txt";
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
