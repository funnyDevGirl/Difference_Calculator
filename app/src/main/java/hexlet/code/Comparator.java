package hexlet.code;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.TreeSet;
import java.util.Set;
import java.util.Objects;


public class Comparator {
    public static List<Map<String, Object>> genDiff(Map<String, Object> data1,
                                                    Map<String, Object> data2)
            throws NullPointerException {

        List<Map<String, Object>> result = new ArrayList<>();

        Set<String> keys = new TreeSet<>(data1.keySet());

        keys.addAll(data2.keySet());

        for (String key : keys) {

            //есть в 1, нет во 2:
            if (!data2.containsKey(key)) {

                Object value1 = !Objects.isNull(data1.get(key)) ? data1.get(key) : null;

                Map<String, Object> removed = Map.of(key,
                        new Status(Status.DELETED, value1, null));

                result.add(removed);

            //нет в 1, есть во 2:
            } else if (!data1.containsKey(key)) {

                Object value2 = !Objects.isNull(data2.get(key)) ? data2.get(key) : null;

                Map<String, Object> added = Map.of(key,
                        new Status(Status.ADDED, null, value2));

                result.add(added);

            //есть в обоих, значение изменено:
            } else if (!Objects.equals(data1.get(key), data2.get(key))) {

                Map<String, Object> changed = Map.of(key,
                        new Status(Status.CHANGED, data1.get(key), data2.get(key)));

                result.add(changed);

            //есть в обоих, значение не изменилось:
            } else if (Objects.equals(data1.get(key), data2.get(key))) {

                Map<String, Object> unchanged = Map.of(key,
                        new Status(Status.UNCHANGED, data1.get(key), null));

                result.add(unchanged);
            }
        }

        return result;
    }
}
