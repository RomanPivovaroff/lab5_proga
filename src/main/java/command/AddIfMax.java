package command;

import entity.Worker;
import utility.Ask;
import utility.CollectionManager;
import utility.Console;

/**
 * Класс команды для добавления элемента в коллекцию, если его значение превышает значение
 * наибольшего элемента этой коллекции.
 */
public class AddIfMax extends AbstractCommand {
    private final Console console;
    private final CollectionManager collectionManager;

    public AddIfMax(Console console, CollectionManager collectionManager) {
        super(
                "add_if_max {element}",
                "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse execute(String[] arguments) {
        try {
            if (!arguments[1].isEmpty())
                return new ExecutionResponse(
                        false,
                        "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
            Worker mymax = collectionManager.getCollection().last();
            console.println("* Создание нового Worker:");
            Worker a = Ask.AskWorker(console, collectionManager.getFreeId());
            if (a != null && a.validate()) {
                if (a.compareTo(mymax) > 0) {
                    collectionManager.add(a);
                    return new ExecutionResponse(true, "Worker успешно добавлен!");
                } else
                    return new ExecutionResponse(
                            false, "созданный worker не максимальный! Worker не создан!");
            } else return new ExecutionResponse(false, "Поля worker не валидны! Worker не создан!");
        } catch (Ask.AskBreak e) {

            return new ExecutionResponse(false, "Отмена...");
        }
    }
}
