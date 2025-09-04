package command;

import utility.CollectionManager;
import utility.Console;

/** Команда 'remove'. Удаляет элемент из коллекции по ид. */
public class Remove extends AbstractCommand {
    private final Console console;
    private final CollectionManager collectionManager;

    public Remove(Console console, CollectionManager collectionManager) {
        super("remove <ID>", "удалить элемент из коллекции по ID");
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
        if (arguments[1].isEmpty())
            return new ExecutionResponse(
                    false,
                    "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        int id = -1;
        try {
            id = Integer.parseInt(arguments[1].trim());
        } catch (NumberFormatException e) {
            return new ExecutionResponse(false, "ID не распознан");
        }

        if (collectionManager.byId(id) == null
                || !collectionManager.getCollection().contains(collectionManager.byId(id)))
            return new ExecutionResponse(false, "Не существующий ID");
        collectionManager.remove(id);
        return new ExecutionResponse("Рабочий успешно удалён!");
    }
}
