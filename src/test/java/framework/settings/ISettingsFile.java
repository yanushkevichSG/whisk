package framework.settings;

import java.util.List;
import java.util.Map;

public interface ISettingsFile {
    Object getValue(String var1);

    List<String> getList(String var1);

    Map<String, Object> getMap(String var1);

    boolean isValuePresent(String var1);

    default Object getValueOrDefault(String path, Object defaultValue) {
        return this.isValuePresent(path) ? this.getValue(path) : defaultValue;
    }
}

