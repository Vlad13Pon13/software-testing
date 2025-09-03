package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import models.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static coomon.CommonFunctions.randomString;

public class Generator {

    @Parameter(names = { "--type", "-t" }, description = "Выбор типа генерации данных Contacts/Groups")
    String type;

    @Parameter(names = { "--output", "-o" }, description = "Файл для сохранения данных")
    String output;

    @Parameter(names = { "--format", "-f" }, description = "Формат сохраняемого файла")
    String format;

    @Parameter(names = { "--count", "-c" }, description = "Количество сгенерируемых данных")
    int count;


    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }


    private Object generate() {
        if ("groups".equals(type)){
            return generateGroups();
        } else if ("contacts".equals(type)) {
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Неизвестный тип данных: " + type );
        }
    }

    private Object generateGroups() {
        ArrayList<GroupData> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new GroupData().
                    withName(randomString(i * 10)).
                    withHeader(randomString(i * 10)).
                    withFooter(randomString(i * 10)));

        }
        return  list;
    }

    private Object generateContacts() {
        return null;
    }


    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(output), data);
        }else {
            throw new IllegalArgumentException("Неизвестный формат данных: " +format);
        }
    }
}
