package io.indico.api.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.indico.api.text.Language;

/**
 * Created by Chris on 6/23/15.
 */
public class EnumParser {
    public static <T extends Enum<T>> Map<T, Double> parse(Class<T> enumClass, Map<String, Double> apiResponse) {
        if (enumClass == Language.class && apiResponse.containsKey("Persian (Farsi)")) {
            apiResponse.put("Persian", apiResponse.remove("Persian (Farsi)"));
        }

        Map<T, Double> mappedResponse = new HashMap<>();
        for (Map.Entry<String, Double> entry : apiResponse.entrySet()) {
            mappedResponse.put(Enum.valueOf(enumClass, titleCase(entry.getKey())), entry.getValue());
        }
        return mappedResponse;
    }

    public static <T extends Enum> List<Map<T, Double>> parse(Class<T> enumClass, List<Map<String, Double>> apiResponse) {
        List<Map<T, Double>> cleanedResponse = new ArrayList<>();
        for (Map<String, Double> entry : apiResponse) {
            cleanedResponse.add(parse(enumClass, entry));
        }
        return cleanedResponse;
    }

    public static String titleCase(String realName) {
        return realName.substring(0, 1).toUpperCase() + realName.substring(1).toLowerCase();
    }
}
