package foxhounds.service.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import model.MapVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.util.Map;

public class MapTest {
    private Map undertest;

    @BeforeEach
    public void setup() {
        undertest = new Map();
    }

    @Test
    public void testgetRowofMapShouldReturnCorrectListWhenCalled() {
        //given
        char[][] values = {
                {'H', '0'},
                {'F', '0'}
        };
        boolean[][] endrow = {
                {true, true},
                {false, false}
        };
        MapVO mapVO = new MapVO(2, 2, values, null);
        //when
        List<Character> result = undertest.getRowofMap(mapVO, 0);
        //then
        List<Character> expected = List.of('H', '0');
        assertEquals(expected, result);
    }
}
