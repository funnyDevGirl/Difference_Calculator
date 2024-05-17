package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;


public class Comparator {
    public static Map<String, Object> genDiff(Map<String, Object> data1,
                                              Map<String, Object> data2)
            throws NullPointerException {

        Map<String, Object> result = new LinkedHashMap<>();

        Set<String> keys = new TreeSet<>(data1.keySet());

        keys.addAll(data2.keySet());

        for (String key : keys) {

            //есть в 1, нет во 2:
            if (!data2.containsKey(key)) {

                Object value1 = !Objects.isNull(data1.get(key)) ? data1.get(key) : null;

                result.put(key,
                        new Status(Status.DELETED, value1, null));

            //нет в 1, есть во 2:
            } else if (!data1.containsKey(key)) {

                Object value2 = !Objects.isNull(data2.get(key)) ? data2.get(key) : null;

                result.put(key,
                        new Status(Status.ADDED, null, value2));

            //есть в обоих, значение изменено:
            } else if (!Objects.equals(data1.get(key), data2.get(key))) {

                result.put(key,
                        new Status(Status.CHANGED, data1.get(key), data2.get(key)));

            //есть в обоих, значение не изменилось:
            } else if (Objects.equals(data1.get(key), data2.get(key))) {

                result.put(key,
                        new Status(Status.UNCHANGED, data1.get(key), null));
            }
        }

        return result;
    }
}
