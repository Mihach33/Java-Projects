package zad4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgLang<K, V> {

    private Map<K,V> LangsMap;
    private Map<String, Set<String>> progsMap;
    private Map<String,Programmer> programmersMap;

    public ProgLang(String fname) throws NoSuchFieldException, SecurityException {
        LangsMap = new LinkedHashMap<K,V>();
        try {
            BufferedReader TSVFile = new BufferedReader(new FileReader(fname));
            String line;

            while ((line = TSVFile.readLine()) != null){
                String[] tokens = line.split("\\t");
                List<String> listTmp = new ArrayList<String>();
                for(int i = 1; i < tokens.length; i ++) listTmp.add(tokens[i]);

                listTmp = listTmp.stream()
                        .distinct()
                        .collect(Collectors.toList());

                LangsMap.put((K)tokens[0],(V)listTmp);
            }
            TSVFile.close();
        } catch (IOException e) {
            System.err.println("ProgLang konstruktor file not found exe!");
        }
    }

    public <K2, V2> Map<K,V> getLangsMap() {
        return (Map<K, V>) LangsMap;
    }

    public Map<String, Set<String>> getProgsMap() {
        progsMap = new LinkedHashMap<String,Set<String>>();
        programmersMap = new LinkedHashMap<String,Programmer>();

        for (K key : LangsMap.keySet()) {
            for (V name : (List<V>)LangsMap.get(key)) {
                if(progsMap.containsKey(name)) {
                    progsMap.get(name).add((String) key);
                } else {
                    Set<String> langs = new HashSet<>();
                    langs.add((String) key);
                    progsMap.put((String) name, langs);
                    programmersMap.put((String) name, new Programmer((String) name,langs));
                }
            }
        }
        return progsMap;
    }

    public Map<K,V> getLangsMapSortedByNumOfProgs() {
        Map<K, V> map = (LinkedHashMap<K, V>) this.LangsMap.entrySet()
                .stream()
                .sorted((e1,e2) -> {
                    List<String> l1 = (List<String>) e1.getValue();
                    List<String> l2 = (List<String>) e2.getValue();
                    ;																	Integer s1 = l1.size();
                    Integer s2 = l2.size();
                    return s2.compareTo(s1);
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        return map;
    }

    public Map<K,V> getProgsMapSortedByNumOfLangs() {
        Map<K, V> map = (LinkedHashMap<K, V>) this.programmersMap.entrySet()
                .stream()
                .sorted((e1,e2)->
                        e2.getValue().getLangsSize().compareTo(e1.getValue().getLangsSize()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        return map;
    }

    public Map<K,V> getProgsMapForNumOfLangsGreaterThan(int i) {
        Map<K, V> map = (LinkedHashMap<K, V>) this.programmersMap.entrySet()
                .stream()
                .filter(e -> e.getValue().getLangsSize() > i)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        return map;
    }

    public static <K,V> Map <K,V> sorted(Map<K,V> mapArg, Comparator<Map.Entry<K,V>> comp) {
        Map<K,V> map = mapArg.entrySet().stream()
                .sorted(comp)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e2, LinkedHashMap::new));
        return map;
    }

    public static <K,V> Map <K,V> filtered(Map<K,V> mapArg, Predicate<Map.Entry<K,V>> pred) {
        Map<K,V> map = mapArg.entrySet().stream()
                .filter(pred)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        return map;
    }
}
