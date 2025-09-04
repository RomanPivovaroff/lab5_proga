package command;

import entity.Organization;
import utility.Ask;
import utility.CollectionManager;
import utility.Console;
import utility.StandartConsole;

public class FilterByOrganization extends AbstractCommand {
    private final Console console;
    private final CollectionManager collectionManager;

    public FilterByOrganization(StandartConsole console, CollectionManager collectionManager) {
        super(
                "filter_by_organization organization",
                "вывести элементы, значение поля organization которых равно заданному");
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

            Organization ch = Ask.askOrganization(console);
            boolean flag = true;
            for (var w : collectionManager.getCollection()) {
                if ((ch == null && w.getOrganization() == null)
                        || (ch != null && ch.equals(w.getOrganization()))) {
                    console.println(w);
                    flag = false;
                }
            }
            if (flag) {
                return new ExecutionResponse(false, "Не найден Organization");
            }
            return new ExecutionResponse(true, "все элементы с заданной организацией выведены!");
        } catch (Ask.AskBreak e) {
            return new ExecutionResponse(false, "Отмена...");
        }
    }
}
