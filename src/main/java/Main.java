import command.*;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import utility.*;

public class Main {
    public static void main(String[] args) {
        StandartConsole console = new StandartConsole();
        if (args.length == 0) {
            console.println("Введите имя загружаемого файла как аргумент командной строки");
            System.exit(1);
        }
        XMLReader reader = new XMLReader(new File(args[0]), console);
        XMLWriter writer = new XMLWriter(new File("output.xml"), console);
        CollectionManager collectionManager = new CollectionManager(reader, writer);
        // Регистрируем хук для экстренного завершения программы
        XMLWriter BackUpWriter =
                new XMLWriter(
                        new File(
                                "backupcollection"
                                        + LocalDateTime.now()
                                                .format(
                                                        DateTimeFormatter.ofPattern(
                                                                "yyyy-MM-dd'T'HH-mm-ss"))
                                        + ".xml"),
                        console);
        Terminate terminateHook = new Terminate(console, collectionManager, BackUpWriter);
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
                        register("remove", new Remove(console, collectionManager));
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
                                new Print_ascending(
                                        console, collectionManager)); // т.к. аналогичен show из-зи
                        // автосортировки Treeset
                        register(
                                "print_unique_status",
                                new PrintUniqueStatus(console, collectionManager));
                        register("execute_script", new ExecuteScript(console));
                    }
                };
        new Runner(console, commandManager).interactiveMode();
    }
}
