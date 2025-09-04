package utility;

import entity.SerializedWorker;
import java.io.IOException;
import java.util.ArrayList;

/** Класс для корректного завершения программы при падении */
public class Terminate extends Thread {
    private final Console console;
    private final CollectionManager collectionManager;
    private final XMLWriter BackUpWriter;

    public Terminate(Console console, CollectionManager collectionManager, XMLWriter backUpWriter) {
        this.console = console;
        this.collectionManager = collectionManager;
        BackUpWriter = backUpWriter;
    }

    /**
     * Выполняет действия при завершении программы Выводит сообщение о завершении и записывает
     * данные в backupcollection.xml
     */
    public void run() {
        console.println("Завершение программы");
        try {
            BackUpWriter.write(
                    new SerializedWorker(new ArrayList<>(collectionManager.getCollection())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
