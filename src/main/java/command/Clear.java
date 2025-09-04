package command;

import utility.CollectionManager;
import utility.Console;

/** Команда 'clear'. Очищает коллекцию. */
public class Clear extends AbstractCommand {
    private final Console console;
    private final CollectionManager collectionManager;

    public Clear(Console console, CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
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

        collectionManager.clear();

        return new ExecutionResponse(true, "Коллекция очищена!");
    }
}
