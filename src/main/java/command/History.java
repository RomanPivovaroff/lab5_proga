package command;

import java.util.List;
import java.util.stream.Collectors;
import utility.CommandManager;
import utility.Console;

/** Команда 'history'. вывести последние 7 команд (без их аргументов). */
public class History extends AbstractCommand {
    private final Console console;
    private final CommandManager commandManager;

    public History(Console console, CommandManager commandManager) {
        super("history", "вывести последние 7 команд (без их аргументов)");
        this.console = console;
        this.commandManager = commandManager;
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
        List<String> commandList = commandManager.getCommandHistory();
        return new ExecutionResponse(
                commandList.stream()
                        .skip(Math.max(0, commandList.size() - 7))
                        .map(command -> " " + command)
                        .collect(Collectors.joining("\n")));
    }
}
