package command;

import java.io.IOException;
import utility.CollectionManager;
import utility.Console;

/** Команда 'save'. Сохраняет коллекцию в файл. */
public class Save extends AbstractCommand {
    private final Console console;
    private final CollectionManager collectionManager;

    public Save(Console console, CollectionManager collectionManager) {
        super("save", "сохранить коллекцию в файл");
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

        boolean saveSuccess = false;
        try {
            saveSuccess = collectionManager.saveCollection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ExecutionResponse(
                true, (saveSuccess) ? "коллккция успешно загружена" : "коллекция не загружена");
    }
}
