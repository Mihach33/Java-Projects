/**
 *
 *  @author Smilianets Mykhailo S20511
 *
 */

package zad3;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Anagrams {
    private List<String> wordsList;
    private List<List> lists;

    public Anagrams(String allWords) {
        wordsList = new ArrayList<String>();
        File file = new File(allWords);
        BufferedReader br;
        String lineTmp;
        try {
            br = new BufferedReader(new FileReader(file));
            while ((lineTmp = br.readLine()) != null) {
                String[] words = lineTmp.split("\\s");
                for (int i = 0; i < words.length; i++) {
                    wordsList.add(words[i]);
                }
            }

        } catch (FileNotFoundException e1) {
            System.err.println("Anagrams konstruktor FileNotFoundException !");
        } catch (IOException e) {
            System.err.println("Anagrams konstruktor IOException !");
        }

    }

    public List<List> getSortedByAnQty() {
        lists = new ArrayList<List>();
        List<String> p = new ArrayList<String>();

        for (int i = 0; i < wordsList.size(); i++) {

            if (!p.contains(wordsList.get(i))) {
                List<String> tmp = new ArrayList<String>();

                for (int j = 0; j < wordsList.size(); j++) {
                    if (isAnagram(wordsList.get(i), wordsList.get(j))) {

                        p.add(wordsList.get(j));
                        tmp.add(wordsList.get(j));

                    }
                }

                lists.add(tmp);
            }

        }

        lists.sort((o1, o2) -> o2.size() - o1.size());
        return lists;

    }

    public String getAnagramsFor(String next) {
        for (int i = 0; i < lists.size(); i++) {
            List<String> tmp = new ArrayList<String>(lists.get(i));

            for (int j = 0; j < tmp.size(); j++) {
                if(tmp.get(j).equals(next)) {
                    tmp.remove(j);
                    return next + ": " + tmp;
                }

            }
        }
        return "";
    }

    public boolean isAnagram(String s1, String s2) {
        char[] w1 = s1.replaceAll("[\\s]", "").toCharArray();
        char[] w2 = s2.replaceAll("[\\s]", "").toCharArray();
        Arrays.sort(w1);
        Arrays.sort(w2);
        return Arrays.equals(w1, w2);
    }
}  
