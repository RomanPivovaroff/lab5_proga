package utility;

import entity.SerializedWorker;
import entity.Worker;
import exceptions.NotEnoughRightsToReadException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.*;
import java.util.TreeSet;

/** Класс для чтения коллекции из файла */
public class XMLReader {
    private final File file;
    private final Console console;

    public XMLReader(File file, Console console) {
        this.file = file;
        this.console = console;
    }

    /** Метод, читающий данные из файла заданного при создании объекта ридера. */
    public void read(TreeSet<Worker> collection) {
        try (var fileReader = new FileReader(file)) {
            if (!file.exists()) throw new FileNotFoundException();
            if (!file.canRead()) throw new NotEnoughRightsToReadException();
            var reader = new BufferedReader(fileReader);
            StringBuilder xmlData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    xmlData.append(line);
                }
            }

            JAXBContext context = JAXBContext.newInstance(SerializedWorker.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            SerializedWorker serializedWorker =
                    (SerializedWorker) unmarshaller.unmarshal(new StringReader(xmlData.toString()));
            TreeSet<Worker> workers = serializedWorker.toTreeSet();
            for (Worker t : workers) {
                if (!t.validate())
                    throw new InvalidObjectException("Ошибка в характиристике рабочего");
            }
            collection.addAll(workers);
            console.println("Коллекция загружена успешно");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            console.printError("Файл сохранения не найден, коллекция не была загружена.");
        } catch (JAXBException | InvalidObjectException e) {
            console.printError(
                    "Файл сохранения поврежден, коллекция не была загружена." + e.getMessage());

        } catch (NotEnoughRightsToReadException e) {
            console.printError(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
