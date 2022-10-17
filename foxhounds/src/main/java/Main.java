import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import model.GameState;
import model.MapVO;
import service.command.Command;
import service.command.InputHandler;
import service.command.impl.ExitCommand;
import service.command.impl.PrintCommand;
import service.game.GameController;
import service.game.GameStepPerformer;
import service.input.UserInputReader;
import service.map.MapReaderFacade;
import service.map.parser.MapParser;
import service.map.reader.MapReader;
import service.map.reader.impl.BufferedReaderMapReader;
import service.map.validation.MapValidator;
import service.map.validation.impl.MapWayValidator;
import service.util.Collection;
import service.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("Map/Board(small).txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        MapReader mapReader = new BufferedReaderMapReader(reader);
        //List<String> rawMap = mapReader.read();

        MapParser mapParser = new MapParser(4, 4);
        //MapVO mapVo = mapParser.parse(rawMap);

        Map maputil = new Map();
        Collection collection = new Collection();

        MapValidator mapValidator = new MapWayValidator(maputil, collection);

        MapReaderFacade mapReaderFacade = new MapReaderFacade(mapReader, mapParser, mapValidator);
        MapVO mapVO = mapReaderFacade.readMap();

        GameState gameState = new GameState(mapVO, false);
        BufferedReader stinput = new BufferedReader(new InputStreamReader(System.in));
        UserInputReader userInputReader = new UserInputReader(stinput);
        PrintWrapper printWrapper = new PrintWrapper();
        MapPrinter mapPrinter = new MapPrinter(2,2, maputil, printWrapper);
        List<Command> commandList = Arrays.asList(
                new PrintCommand(mapPrinter, gameState),
                new ExitCommand(gameState)
        );

        InputHandler inputHandler = new InputHandler(commandList);
        GameStepPerformer gameStepPerformer = new GameStepPerformer(userInputReader, inputHandler);
        GameController gameController = new GameController(gameState, maputil, gameStepPerformer);

        gameController.gameLoop();
    }
}
