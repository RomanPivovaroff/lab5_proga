import command.*;
import java.io.File;
import utility.*;

public class Main {
    public static void main(String[] args) {
        StandardAppConsole console = new StandardAppConsole();
        if (args.length == 0) {
            console.println("Введите имя загружаемого файла как аргумент командной строки");
            System.exit(1);
        }
        XMLReader reader = new XMLReader(new File(args[0]), console);
        XMLWriter writer = new XMLWriter(new File(args[0]), console);
        CollectionManager collectionManager = new CollectionManager(reader, writer);
        // Регистрируем хук для экстренного завершения программы
        Terminate terminateHook = new Terminate(console, collectionManager);
        Runtime.getRuntime().addShutdownHook(terminateHook);
        // продолжаем логику программы
        if (!collectionManager.init()) {
            System.exit(1);
        }
        var commandManager =
                new CommandManager() {
                    {
                        register("info", new Info(console, collectionManager));
                        register("show", new Show(console, collectionManager));
                        register("add", new Add(console, collectionManager));
                        register("save", new Save(console, collectionManager));
                        register("help", new Help(console, this));
                        register("update", new Update(console, collectionManager));
                        register("remove_by_id", new RemoveById(console, collectionManager));
                        register("clear", new Clear(console, collectionManager));
                        register("exit", new Exit(console));
                        register("add_if_max", new AddIfMax(console, collectionManager));
                        register("remove_greater", new RemoveGreater(console, collectionManager));
                        register("history", new History(console, this));
                        register(
                                "filter_by_organization",
                                new FilterByOrganization(console, collectionManager));
                        register(
                                "print_ascending",
                                new PrintAscending(
                                        console, collectionManager)); 
                        register(
                                "print_unique_status",
                                new PrintUniqueStatus(console, collectionManager));
                        register("execute_script", new ExecuteScript(console));
                    }
                };
        new Runner(console, commandManager).interactiveMode();
    }
}
