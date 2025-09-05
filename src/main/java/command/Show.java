package command;

import utility.CollectionManager;
import utility.Console;

/** Команда 'show'. Выводит все элементы коллекции. */
public class Show extends AbstractCommand {
    private final Console console;
    private final CollectionManager collectionManager;

    public Show(Console console, CollectionManager collectionManager) {
        super(
                "show",
                "вывести в стандартный поток вывода все элементы коллекции в строковом"
                        + " представлении");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     *
     * @return Успешность выполнения команды.
     */
    @Override
    public ExecutionResponse execute(String[] arguments) {
        if (!arguments[1].isEmpty())
            return new ExecutionResponse(
                    false,
                    "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

        return new ExecutionResponse(collectionManager.toString());
    }
}
