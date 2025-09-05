package command;

import entity.Status;
import java.util.TreeSet;
import utility.CollectionManager;
import utility.Console;

/**
 * Команда 'Print_unique_status'. Вывести уникальные значения поля status всех элементов в
 * коллекции.
 */
public class PrintUniqueStatus extends AbstractCommand {
    private final Console console;
    private final CollectionManager collectionManager;

    public PrintUniqueStatus(Console console, CollectionManager collectionManager) {
        super(
                "print_unique_status",
                "вывести уникальные значения поля status всех элементов в коллекции");
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
        var beNull = false;
        var ts = new TreeSet<Status>();
        for (var e : collectionManager.getCollection()) {
            if (e.getStatus() == null) beNull = true;
            else ts.add(e.getStatus());
        }
        var s = "";
        if (beNull) s = "null";
        for (var e : ts) s += " " + e;
        return new ExecutionResponse(true, s);
    }
}
