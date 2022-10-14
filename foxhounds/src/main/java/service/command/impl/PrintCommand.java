package service.command.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.command.Command;

public class PrintCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrintCommand.class);


    @Override
    public boolean canProcess(String input) {
        return false;
    }

    @Override
    public void process(String input) {
        LOGGER.info("PROCESSING");

    }
}
