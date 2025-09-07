package command;

/** Интерфейс для классов команд. */
public interface Command {
    /** Запускает цикл выполнения конкретной команды. */
    ExecutionResponse execute(String[] arguments);
}
