package hexlet.code;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public final class Status {

    public static final String DELETED = "deleted";
    public static final String ADDED = "added";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";

    private String statusName;
    private Object oldValue;
    private Object newValue;

    Status(String statusname, Object oldvalue, Object newvalue) {
        statusName = statusname;
        oldValue = oldvalue;
        newValue = newvalue;
    }
}
