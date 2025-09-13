package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import coomon.CommonFunctions;
import models.ContactData;
import models.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private Object generateData(Supplier<Object> supplier){
      return   Stream.generate(supplier).limit(count).collect(Collectors.toList());
    }

    private Object generateGroups() {
        return generateData(()->new GroupData().
                withName(randomString(10)).
                withHeader(randomString(10)).
                withFooter(randomString(10)));

    }

    private Object generateContacts() {
        ArrayList<ContactData> list = new ArrayList<>();
        ContactData contactOne = new ContactData(
                CommonFunctions.nameGenerator("male", "firstName"),
                CommonFunctions.nameGenerator("male", "lastName"),
                "123 Elm Street",
                CommonFunctions.randomPhoneNumber(),
                "test@example.com"
        );
        contactOne.setPhoto(CommonFunctions.randomPhoto("src/test/resources/images"));

        ContactData contactTwo = new ContactData(
                CommonFunctions.nameGenerator("female", "firstName"),
                CommonFunctions.nameGenerator("female", "lastName"),
                "123 Elm Street",
                CommonFunctions.randomPhoneNumber(),
                "test@example.com"

        );
        contactTwo.setPhoto(CommonFunctions.randomPhoto("src/test/resources/images"));

        ContactData contactThree = new ContactData(
                CommonFunctions.nameGenerator("male", "firstName"),
                CommonFunctions.nameGenerator("male", "lastName"),
                "123 Elm Street",
                CommonFunctions.randomPhoneNumber(),
                "test@example.com"

        );
        contactThree.setPhoto(CommonFunctions.randomPhoto("src/test/resources/images"));
        list.add(contactOne);
        list.add(contactTwo);
        list.add(contactThree);

        return list;
    }


    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(output), data);
        } else if ("yaml".equals(format)){
            var mapper = new YAMLMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(output),data);
        } else if ("xml".equals(format)){
            var mapper = new XmlMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(output),data);
        } else {
            throw new IllegalArgumentException("Неизвестный формат данных: " +format);
        }
    }
}
