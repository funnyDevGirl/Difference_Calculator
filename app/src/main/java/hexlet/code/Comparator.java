package hexlet.code;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;


public class Comparator {
    public static List<Map<String, Object>> genDiff(Map<String, Object> data1, Map<String, Object> data2)
            throws NullPointerException {
        List<Map<String, Object>> result = new ArrayList<>();
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        for (String key : keys) {

            //есть в 1, нет во 2:
            if (!data2.containsKey(key)) {
                Object value1 = !Objects.isNull(data1.get(key)) ? data1.get(key) : "null";
                Map<String, Object> removedValues = Map.of("name", key, "type", "deleted",
                        "oldValue", value1);
                Map<String, Object> removed = new TreeMap<>(removedValues);

                result.add(removed);

                //нет в 1, есть во 2:
            } else if (!data1.containsKey(key)) {
                Object value2 = !Objects.isNull(data2.get(key)) ? data2.get(key) : "null";

                Map<String, Object> addedValues = Map.of("name", key, "type", "added",
                        "newValue", value2);
                Map<String, Object> added = new TreeMap<>(addedValues);

                result.add(added);

                //есть в обоих:
            } else if (data1.containsKey(key) && data2.containsKey(key)) {
                Object valueForKey1 = !Objects.isNull(data1.get(key)) ? data1.get(key) : "null";
                Object valueForKey2 = !Objects.isNull(data2.get(key)) ? data2.get(key) : "null";

                //значение изменено:
                if (!valueForKey1.equals(valueForKey2)) {
                    Map<String, Object> changedValues = Map.of("name", key, "type", "changed",
                            "oldValue", valueForKey1, "newValue", valueForKey2);
                    Map<String, Object> changed = new TreeMap<>(changedValues);

                    result.add(changed);
                    //значение не изменилось:
                } else {
                    Map<String, Object> unchangedValues = Map.of("name", key, "type", "unchanged",
                            "oldValue", valueForKey1);
                    Map<String, Object> unchanged = new TreeMap<>(unchangedValues);

                    result.add(unchanged);
                }
            }
        }
        return result;
    }
}
