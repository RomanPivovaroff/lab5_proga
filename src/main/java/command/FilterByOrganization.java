package command;

import entity.Organization;
import entity.Worker;
import java.util.TreeSet;
import java.util.stream.Collectors;
import utility.Ask;
import utility.CollectionManager;
import utility.Console;
import utility.StandardAppConsole;

public class FilterByOrganization extends AbstractCommand {
    private final Console console;
    private final CollectionManager collectionManager;

    public FilterByOrganization(StandardAppConsole console, CollectionManager collectionManager) {
        super(
                "filter_by_organization {organization}",
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
            TreeSet<Worker> res = new TreeSet<>();
            for (var w : collectionManager.getCollection()) {
                if ((ch == null && w.getOrganization() == null)
                        || (ch != null && ch.equals(w.getOrganization()))) {
                    res.add(w);
                    flag = false;
                }
            }
            if (flag) {
                return new ExecutionResponse(true, "Не найден соответсвующий Organization");
            }
            String result = res.stream().map(Worker::toString).collect(Collectors.joining("\n"));
            return new ExecutionResponse(
                    true, (result + "\n" + "все элементы с заданной организацией выведены!"));
        } catch (Ask.AskBreak e) {
            return new ExecutionResponse(false, "Отмена...");
        }
    }
}
