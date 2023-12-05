package hexlet.code;

import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeSet;
import java.util.Set;
import java.util.Objects;
import java.util.TreeMap;


public class Comparator {
    public static List<Map<String, Object>> genDiff(Map<String, Object> data1, Map<String, Object> data2)
            throws NullPointerException {
        List<Map<String, Object>> result = new ArrayList<>();
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        for (String key : keys) {

            //есть в 1, нет во 2:
            if (!data2.containsKey(key)) {

                Object value1 = !Objects.isNull(data1.get(key)) ? data1.get(key) : null;

                Map<String, Object> removedValues = Map.of("name", key, "type", "deleted",
                        "oldValue", value1);
                Map<String, Object> removed = new TreeMap<>(removedValues);

                result.add(removed);

                //нет в 1, есть во 2:
            } else if (!data1.containsKey(key)) {
                Object value2 = !Objects.isNull(data2.get(key)) ? data2.get(key) : null;

                Map<String, Object> addedValues = Map.of("name", key, "type", "added",
                        "newValue", value2);
                Map<String, Object> added = new TreeMap<>(addedValues);

                result.add(added);

                //есть в обоих:
            } else if (data1.containsKey(key) && data2.containsKey(key)) {

                Object valueForKey1 = data1.get(key);
                Object valueForKey2 = data2.get(key);

                //значение изменено:
                if (!isEqual(valueForKey1, valueForKey2)) {

                    Map<String, Object> changedValues = new LinkedHashMap<>();
                    changedValues.put("name", key);
                    changedValues.put("type", "changed");
                    changedValues.put("oldValue", valueForKey1);
                    changedValues.put("newValue", valueForKey2);
                    Map<String, Object> changed = new TreeMap<>(changedValues);

                    result.add(changed);
                    //значение не изменилось:
                } else {

                    Map<String, Object> unchangedValues = new LinkedHashMap<>();
                    unchangedValues.put("name", key);
                    unchangedValues.put("type", "unchanged");
                    unchangedValues.put("oldValue", valueForKey1);
                    Map<String, Object> unchanged = new TreeMap<>(unchangedValues);

                    result.add(unchanged);
                }
            }
        }
        return result;
    }

    private static Boolean isEqual(Object value1, Object value2) {
        if (value1 == null || value2 == null) {
            return value1 == value2;
        }

        return value1.equals(value2);
    }
}
