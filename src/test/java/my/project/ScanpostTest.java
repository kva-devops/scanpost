package my.project;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.is;

public class ScanpostTest {

    @Test
    public void testDeleteCopy1() {
        Map<String, Set<String>> initial = new HashMap<>();
        initial.put("user1", new HashSet<>(
                Set.of("mail1@mail.ru", "mail2@mail.ru", "mail3@mail.ru")));
        initial.put("user2", new HashSet<>(
                Set.of("mail4@mail.ru", "mail5@mail.ru")));
        initial.put("user3", new HashSet<>(
                Set.of("mail1@mail.ru", "mail6@mail.ru")));
        Map<String, Set<String>> expected = new HashMap<>();
        expected.put("user1", new HashSet<>(
                Set.of("mail1@mail.ru", "mail2@mail.ru", "mail3@mail.ru", "mail6@mail.ru")));
        expected.put("user2", new HashSet<>(
                Set.of("mail4@mail.ru", "mail5@mail.ru")));
        Scanpost object = new Scanpost(initial);
        Map<String, Set<String>> resultMap = object.deleteCopy();
        Assert.assertThat(resultMap, is(expected));
    }

    @Test
    public void testDeleteCopy2() {
        Map<String, Set<String>> initial = new HashMap<>();
        initial.put("Kolobok", new HashSet<>(
                Set.of("111@aaa.aa", "222@aaa.aa")));
        initial.put("Zayac", new HashSet<>(
                Set.of("222@bbb.bb", "333@bbb.bb")));
        initial.put("Kolobok Kolobkov", new HashSet<>(
                Set.of("333@aaa.aa", "222@aaa.aa")));
        Map<String, Set<String>> expected = new HashMap<>();
        expected.put("Kolobok", new HashSet<>(
                Set.of("111@aaa.aa", "222@aaa.aa", "333@aaa.aa")));
        expected.put("Zayac", new HashSet<>(
                Set.of("222@bbb.bb", "333@bbb.bb")));
        Scanpost object = new Scanpost(initial);
        Map<String, Set<String>> resultMap = object.deleteCopy();
        Assert.assertThat(resultMap, is(expected));
    }
}