package utility;

import java.io.IOException;

/** Класс для корректного завершения программы при падении */
public class Terminate extends Thread {
    private final Console console;
    private final CollectionManager collectionManager;

    public Terminate(Console console, CollectionManager collectionManager) {
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет действия при завершении программы Выводит сообщение о завершении и записывает
     * данные в backupcollection.xml
     */
    public void run() {
        console.println("Завершение программы");
        try {
            collectionManager.saveCollection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
