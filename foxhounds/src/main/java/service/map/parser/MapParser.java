package service.map.parser;

import java.util.List;

import model.MapVO;
import service.exception.MapParseException;

public class MapParser {
    private final int numberofrows;
    private final int numberofcolumns;

    public MapParser(int numberofrows, int numberofcolumns) {
        this.numberofrows = numberofrows;
        this.numberofcolumns = numberofcolumns;
    }

    public MapVO parse(List<String> rawMap) {
        char[][] values = getValues(rawMap);
        boolean[][] endrow = getEndrow(values);

        checkNumberofrows(rawMap);

        return new MapVO(numberofrows, numberofcolumns, values, endrow);
    }

    private char[][] getValues(List<String> rawMap) {
        char[][] result = new char[numberofrows][numberofcolumns];

        for (int i = 0; i < numberofrows; i++) {
            result[i] = new char[numberofcolumns];
            for (int j = 0; j < numberofcolumns; j++) {
                result[i][j] = rawMap.get(i).charAt(j);
            }
        }

        return result;
    }

    private boolean[][] getEndrow(char[][] map) {
        boolean[][] endrow = new boolean[numberofrows][numberofcolumns];

        for (int i = 0; i < numberofrows; i++) {
            for (int j = 0; j < numberofcolumns; j++) {
                endrow[i][j] = i == 0;
            }
        }
        return endrow;
    }

    private void checkNumberofrows(List<String> rawMap) {
        if (rawMap.size() != numberofrows) {
            throw new MapParseException("Board format error: Number of rows " + rawMap.size() + " (supposed to be " + numberofrows + ")");
        }
    }

}
