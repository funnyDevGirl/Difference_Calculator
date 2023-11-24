package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;

public class Comparator {
    public static final String key1 = "FIELD";
    public static final String key2 = "STATUS";
    public static final String key3 = "OLD_VALUE";
    public static final String key4 = "NEW_VALUE";
    public static final String addedValue = "ADDED";
    public static final String removedValue = "REMOVED";
    public static final String updatedValue = "UPDATED";
    public static final String sameValue = "SAME";

    public static List<Map<String, Object>> collectData(Map<String, Object> data1, Map<String, Object> data2) throws NullPointerException{
        List<Map<String, Object>> result = new ArrayList<>();

        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());
        //мапы для 4х сценариев:
        Map<String, Object> addedValues = new HashMap<>();
        Map<String, Object> removedValues = new HashMap<>();
        Map<String, Object> updatedValues = new HashMap<>();
        Map<String, Object> sameValues = new HashMap<>();


        for (String key : keys) {
            //ключ появился во 2, нет в 1
            if (!data1.containsKey(key)) {
                addedValues = Map.of(key1, key, key2, addedValue, key4, data2.get(key));
                //ключ есть в 1, но уже нет во 2
            } else if (!data2.containsKey(key)) {
                removedValues = Map.of(key1, key, key2, removedValue, key4, data1.get(key));
                //ключи есть в 1 и во 2
            } else if (data1.containsKey(key) && data2.containsKey(key)) {
                //значения изменены
                if (!data1.get(key).equals(data2.get(key))) {
                    updatedValues = Map.of(key1, key, key2, updatedValue, key3, data1.get(key), key4, data2.get(key));
                    //значения те же
                } else {
                    sameValues = Map.of(key1, key, key2, sameValue, key3, data1.get(key));
                }
            }
        }
        result.add(addedValues);
        result.add(removedValues);
        result.add(updatedValues);
        result.add(sameValues);
        return result;
    }
}
