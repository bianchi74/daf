package it.gov.daf.dataquality.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Marco Bianchi (bianchi74@gmail.com)
 */
public class CollectionUtils {

    private CollectionUtils(){}

    public static Map<String,String> mergeMaps(Map<String,String> map1, Map<String,String> map2){
        HashMap<String,String> map = new HashMap<>();
        for (Map.Entry<String, String> entry : map1.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry : map2.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    public static Set<Object> mergeSets(Set<Object> set1, Set<Object> set2){

        Set<Object> set = new HashSet<>();

        for (Object item: set1){
            set.add(item);
        }
        for (Object item: set2){
            set.add(item);
        }
        return set;

    }

}
