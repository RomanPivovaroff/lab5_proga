package command;

import entity.Worker;
import utility.Ask;
import utility.CollectionManager;
import utility.Console;

/** Команда 'add'. Добавляет новый элемент в коллекцию. */
public class Add extends AbstractCommand {
    private final Console console;
    private final CollectionManager collectionManager;

    public Add(Console console, CollectionManager collectionManager) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     *
     * @return Успешность выполнения команды и сообщение об успешности.
     */
    @Override
    public ExecutionResponse execute(String[] arguments) {
        try {
            if (!arguments[1].isEmpty())
                return new ExecutionResponse(
                        false, "Неправильное количество аргументов!\nИспользование: '" + "'");

            console.println("* Создание нового Worker:");
            Worker a = Ask.AskWorker(console, collectionManager.getFreeId());

            if (a != null && a.validate()) {
                collectionManager.add(a);
                return new ExecutionResponse(true, "Worker успешно добавлен!");
            } else return new ExecutionResponse(false, "Поля worker не валидны! Worker не создан!");
        } catch (Ask.AskBreak e) {
            return new ExecutionResponse(false, "Отмена...");
        }
    }
}
