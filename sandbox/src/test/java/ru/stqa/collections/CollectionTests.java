package ru.stqa.collections;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class CollectionTests {

    @Test
    void arrayTest(){
        //var array = new String[]{"a", "b", "c"};
        var array = new String[3];
        array[0] = "a";
        array[1] = "b";
        array[2] = "c";
        Assertions.assertEquals("a", array[0]);
        Assertions.assertEquals(3, array.length);

        array[0]= "d";
        Assertions.assertEquals("d", array[0]);
    }

    @Test
    void listTest(){
        ArrayList list = new ArrayList<>(List.of("a","b","c"));
        Assertions.assertEquals(3, list.size());

        //list.add("a");
        //list.add("b");
        //list.add("c");
        Assertions.assertEquals("a", list.get(0));

        list.set(0, "d");
        Assertions.assertEquals("d", list.get(0));

    }
}
