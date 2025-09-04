package command;

import entity.Worker;
import utility.Ask;
import utility.CollectionManager;
import utility.Console;

/** Команда 'remove_greater'. удалить из коллекции все элементы, превышающие заданный. */
public class RemoveGreater extends AbstractCommand {
    private final Console console;
    private final CollectionManager collectionManager;

    public RemoveGreater(Console console, CollectionManager collectionManager) {
        super(
                "remove_greater {element}",
                "удалить из коллекции все элементы, превышающие заданный");
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
            if (!arguments[1].isEmpty())
                return new ExecutionResponse(
                        false,
                        "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
            console.println("* Создание нового Worker:");
            Worker a = Ask.AskWorker(console, collectionManager.getFreeId());
            if (a != null && a.validate()) {
                collectionManager.add(a);
                while (true) {
                    Worker w = collectionManager.getCollection().last();
                    if (a.equals(w)) break;
                    collectionManager.remove(w.getId());
                }
                return new ExecutionResponse(
                        true, "Worker успешно добавлен, все рабочие с большей зарплатой удалены");
            } else return new ExecutionResponse(false, "Поля worker не валидны! Worker не создан!");
        } catch (Ask.AskBreak e) {

            return new ExecutionResponse(false, "Отмена...");
        }
    }
}
