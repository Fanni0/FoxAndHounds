package service.util;

import java.util.ArrayList;
import java.util.List;

public class Collection {

    public List<Character> collectNonZeroValues(List<Character> characterList) {
        List<Character> result = new ArrayList<>();

        for (char value : characterList) {
            if (value != '0') {
                result.add(value);
            }
        }
        return result;
    }

    public boolean contains(List<Character> nonzeroList) {
        if (!nonzeroList.contains('H') || !nonzeroList.contains('F')) {
            return false;
        } else {
            return true;
        }

    }
}
