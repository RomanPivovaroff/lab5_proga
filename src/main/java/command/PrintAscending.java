package command;

import entity.Worker;
import utility.CollectionManager;
import utility.Console;
import utility.StandardAppConsole;

public class PrintAscending extends AbstractCommand {
    private final Console console;
    private final CollectionManager collectionManager;

    public PrintAscending(StandardAppConsole console, CollectionManager collectionManager) {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse execute(String[] arguments) {
        StringBuilder sb = new StringBuilder();
        for (Worker w : collectionManager.getSortCollection()) {
            sb.append(w).append("\n");
        }
        String result = sb.toString();
        return new ExecutionResponse(true, result);
    }
}
