package command;

import entity.Worker;
import utility.Ask;
import utility.CollectionManager;
import utility.Console;

/** Команда 'update'. Обновляет элемент коллекции. */
public class Update extends AbstractCommand {
    private final Console console;
    private final CollectionManager collectionManager;

    public Update(Console console, CollectionManager collectionManager) {
        super("update <ID> {element}", "обновить значение элемента коллекции по ID");
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
        try {
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

            var old = collectionManager.byId(id);
            if (old == null || !collectionManager.getCollection().contains(old)) {
                return new ExecutionResponse(false, "Не существующий ID");
            }

            console.println("* Создание нового Worker:");
            Worker w = Ask.AskWorker(console, old.getId());
            if (w != null && w.validate()) {
                collectionManager.update(w);
                return new ExecutionResponse("Обновлено!");
            } else {
                return new ExecutionResponse(false, "Поля Рабочего не валидны! Рабочий не создан!");
            }
        } catch (Ask.AskBreak e) {
            return new ExecutionResponse(false, "Поля Рабочего не валидны! Рабочий не создан!");
        }
    }
}
