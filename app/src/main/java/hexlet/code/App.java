package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "genDiff", mixinStandardHelpOptions = true, version = "genDiff 4.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    // Аннотация @Parameters указывает на аргументы командной строки
    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private String filepath2;

    // Точка входа в приложение
    public static void main(String... args) {
        // Создание объекта командной строки и выполнение команды
        int exitCode = new CommandLine(new App()).execute(args);
        // Выход с указанным кодом
        System.exit(exitCode);
    }

    // Метод вызывается при выполнении команды genDiff
    @Override
    public Integer call() throws IOException {
        // Генерация диффа и вывод результата
        String diff = Differ.generate(filepath1, filepath2);
        System.out.println(diff);
        // Возврат кода завершения
        return 0;
    }
}
