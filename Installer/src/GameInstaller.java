import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameInstaller {
    private StringBuilder log;
    private String gamesPath;

    public GameInstaller(String gamesPath) {
        this.gamesPath = gamesPath;
        this.log = new StringBuilder();
    }

    public void install() {
        addLog("Начало установки игры");

        //Создаем  директории
        createDirectory(gamesPath + File.separator + "src");
        createDirectory(gamesPath + File.separator + "res");
        createDirectory(gamesPath + File.separator + "savegames");
        createDirectory(gamesPath + File.separator + "temp");

        //Создаем поддиректории в src
        createDirectory(gamesPath + File.separator + "src" + File.separator + "main");
        createDirectory(gamesPath + File.separator + "src" + File.separator + "test");

        //Создаем файлы в src/main
        createFile(gamesPath + File.separator + "src" + File.separator + "main" + File.separator + "Main.java");
        createFile(gamesPath + File.separator + "src" + File.separator + "main" + File.separator + "Utils.java");

        //Создаем поддиректории в res
        createDirectory(gamesPath + File.separator + "res" + File.separator + "drawables");
        createDirectory(gamesPath + File.separator + "res" + File.separator + "vectors");
        createDirectory(gamesPath + File.separator + "res" + File.separator + "icons");

        //Создаем файл temp.txt в директории temp
        String tempFilePath = gamesPath + File.separator + "temp" + File.separator + "temp.txt";
        createFile(tempFilePath);

        addLog("Установка завершена");

        // Записываем лог в файл temp.txt
        saveLog(tempFilePath);
    }

    private void createDirectory(String path) {
        File directory = new File(path);

        if (directory.mkdir()) {
            addLog("Директория создана: " + path + "\n");
        } else {
            if (directory.exists()) {
                addLog("Директория уже существует: " + path + "\n");
            } else {
                addLog("Ошибка при создании директории: " + path + "\n");
            }
        }
    }

    private void createFile(String path) {
        File file = new File(path);

        try {
            if (file.createNewFile()) {
                addLog("Файл создан: " + path + "\n");
            } else {
                if (file.exists()) {
                    addLog("Файл уже существует: " + path + "\n");
                } else {
                    addLog("Ошибка при создании файла: " + path + "\n");
                }
            }
        } catch (IOException e) {
            addLog("IOException при создании файла: " + path + " - " + e.getMessage() + "\n");
        }
    }

    private void addLog(String message) {
        log.append(message);
        System.out.print(message); // Также выводим в консоль
    }

    private void saveLog(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(log.toString());
            System.out.println("\n Лог успешно записан в файл: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка при записи лога: " + e.getMessage());
        }
    }
}
