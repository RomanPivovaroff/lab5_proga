package command;

import utility.CollectionManager;
import utility.Console;
import utility.StandartConsole;

public class Print_ascending extends AbstractCommand {
    private final Console console;
    private final CollectionManager collectionManager;

    public Print_ascending(StandartConsole console, CollectionManager collectionManager) {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse execute(String[] arguments) {
        return (new Show(console, collectionManager)).execute(arguments);
    }
}
