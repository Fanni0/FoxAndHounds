package service.map.validation.impl;

import java.util.List;

import model.MapVO;
import service.exception.MapValidationException;
import service.map.validation.MapValidator;
import service.util.Collection;
import service.util.Map;

public class MapWayValidator implements MapValidator {

    private final Map maputil;
    private final Collection collectionutil;

    public MapWayValidator(Map maputil, Collection collectionutil) {
        this.maputil = maputil;
        this.collectionutil = collectionutil;
    }

    @Override
    public void validate(MapVO mapVO) {
        int numberofrows = mapVO.getNumberofrows();
        int numberofcolumns = mapVO.getNumberofcolumns();


        for (int i = 0; i < numberofrows; i++) {
            List<Character> rowofmap = maputil.getRowofMap(mapVO, i);
            //List<Character> nonZeroValues = collectionutil.collectNonZeroValues(rowofmap);
            if (collectionutil.contains(collectionutil.collectNonZeroValues(rowofmap))) {
                throw new MapValidationException("Invalid character");
            }
            //System.out.println(collectionutil.collectNonZeroValues(rowofmap));
        }
    }
}
