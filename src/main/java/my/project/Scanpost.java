package my.project;

import java.util.*;

/**
 * Класс удаляет дубликаты учетных записей пользователей, если в них содержатся одинаковые емаил адреса.
 * При этом остается только одна учетная запись, в которую заносятся все неповторяющиеся
 * @author Vladimir Kutyavin
 * @version 0.1
 *
 */
public class Scanpost {
    /**
     * Начальные данные передаются и сохраняются в структуре HashMap, в которой ключ - имя пользователя,
     * а значение - множество емаил адресов
     */
    private final Map<String, Set<String>> init;

    /**
     * Конструктор класса Scanpost
     * @param init принимает в качестве параметра карту "Пользователь - Множество емаил адресов"
     */
    public Scanpost(Map<String, Set<String>> init) {
        this.init = init;
    }

    /**
     * Удаляет дубликаты пользователей, у которых имеются одинаковые емаил адреса
     * @return возвращает карту, в которой не содержится дубликатов пользователей с одинаковыми емаил адресами
     */
    public Map<String, Set<String>> deleteCopy() {
        Map<String, List<String>> mapOneMail = reverseIndexMap(init);
        Map<String, List<String>> duplicateUserMap = findDuplicate(mapOneMail);
        return deleteUser(init, duplicateUserMap);
    }

    /**
     * Разворачивает карту, превращая ключи в значения, а значения в ключи.
     * В возвращаемой карте в качестве ключа выступает отдельный емаил адрес, а в качестве значения -
     * список пользователей, которым принадлежит данный емаил адрес
     * @param map принимает карту "Пользователь - Множество емаил адресов"
     * @return возвращает карту "Емаил - Список пользователей"
     */
    private Map<String, List<String>> reverseIndexMap(Map<String, Set<String>> map) {
        Map<String, List<String>> mapOneMail = new HashMap<>();
        List<String> listMails = new ArrayList<>();
        for (String elem : map.keySet()) {
            listMails.add(elem);
            listMails.addAll(map.get(elem));
        }
        List<String> buffNameList = new ArrayList<>();
        for (String elemMail : listMails) {
            if (!elemMail.contains("@")) {
                List<String> userNameList = new ArrayList<>();
                userNameList.add(elemMail);
                buffNameList = userNameList;
                continue;
            }
            List<String> buffList1 = new ArrayList<>();
            List<String> buffList2 = new ArrayList<>();
            buffList1 = mapOneMail.get(elemMail);
            buffList2 = mapOneMail.put(elemMail, buffNameList);
            if (buffList2 != null) {
                List<String> buffList3 = new ArrayList<>();
                buffList3.addAll(buffList1);
                buffList3.addAll(buffNameList);
                mapOneMail.put(elemMail, buffList3);
            }
        }
        return mapOneMail;
    }

    /**
     * Находит в карте "Емаил - Список пользователей" елементы, в которых длина "Списка пользователей" больше 1.
     * Затем вносит данный список в новую карту. В качестве ключа берется первый элемент списка пользователей,
     * а в качестве значения весь список.
     * @param map принимает карту "Емаил - Список пользователей"
     * @return карту "Пользователь - Список пользователей"
     */
    private Map<String, List<String>> findDuplicate(Map<String, List<String>> map) {
        Map<String, List<String>> duplicate = new HashMap<>();
        for (List<String> elem : map.values()) {
            if (elem.size() > 1) {
                duplicate.put(elem.get(0), elem);
            }
        }
        return duplicate;
    }

    /**
     * Метод удаляет из исходной карты "Пользователь - Множество емаил адресов" элементы, пользоватлей которые содержатся
     * в карте дубликатов "Пользователь - Список пользователей". При этом множество емаил адресов этих пользователей
     * объединяются в одно множество и присваиваюстя одному пользователю, который остается в исходной карте
     * @param map принимает начальную карту "Пользователь - Множество емаил адресов"
     * @param copy принимает карту "Пользователь - Список пользователей", содержащую список дубликатов пользователя
     * @return возвращает карту, в которой все пользователи уникальны, т.е. пользователи не имеют одинаковых емаилов
     */
    private Map<String, Set<String>> deleteUser(Map<String, Set<String>> map, Map<String, List<String>> copy) {
        Set<Map.Entry<String, List<String>>> foo = copy.entrySet();
        Map<String, Set<String>> result = new HashMap<>();
        for (Map.Entry<String, List<String>> elem : foo) {
            Set<String> buffHashSet = new HashSet<>();
            for (int i = 0; i < elem.getValue().size(); i++) {
                buffHashSet.addAll(map.get(elem.getValue().get(i)));
                result.put(elem.getKey(), buffHashSet);
                map.remove(elem.getValue().get(i));
            }
        }
        result.putAll(map);
        return result;
    }
}
