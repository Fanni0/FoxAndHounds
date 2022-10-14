package service.map;

import java.util.List;

import model.MapVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.exception.MapReadException;
import service.map.parser.MapParser;
import service.map.reader.MapReader;
import service.map.validation.MapValidator;

public class MapReaderFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapReaderFacade.class);

    private final MapReader mapReader;
    private final MapParser mapParser;
    private final MapValidator mapValidator;

    public MapReaderFacade(MapReader mapReader, MapParser mapParser, MapValidator mapValidator) {
        this.mapReader = mapReader;
        this.mapParser = mapParser;
        this.mapValidator = mapValidator;
    }

    public MapVO readMap() {
        MapVO result;

        try {
            List<String> rawMap = mapReader.read();
            result = mapParser.parse(rawMap);
            mapValidator.validate(result);
        } catch (MapReadException e) {
            LOGGER.error("Failed to read map");
            throw new RuntimeException("Failed to read map");
        } catch (RuntimeException e) {
            LOGGER.error("Unknown exception", e);
            throw new RuntimeException("Unknown error", e);
        }
        return result;
    }
}
