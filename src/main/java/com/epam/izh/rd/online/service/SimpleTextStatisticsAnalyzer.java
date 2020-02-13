package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;

import static java.util.Collections.*;

/**
 * Совет:
 * Начните с реализации метода {@link SimpleTextStatisticsAnalyzer#getWords(String)}.
 * Затем переиспользуйте данный метод при реализации других.
 * <p>
 * При необходимости, можно создать внутри данного класса дополнительные вспомогательные приватные методы.
 */
public class SimpleTextStatisticsAnalyzer implements TextStatisticsAnalyzer {

    /**
     * Необходимо реализовать функционал подсчета суммарной длины всех слов (пробелы, знаким препинания итд не считаются).
     * Например для текста "One, I - tWo!!" - данный метод должен вернуть 7.
     *
     * @param text текст
     */
    @Override
    public int countSumLengthOfWords(String text) {
        int sum = 0;
        for (String words: getWords(text)) {
            sum += words.length();
        }
        return sum;
    }

    /**
     * Необходимо реализовать функционал подсчета количества слов в тексте.
     * Например для текста "One, two, three, three - one, tWo, tWo!!" - данный метод должен вернуть 7.
     *
     * @param text текст
     */
    @Override
    public int countNumberOfWords(String text) {
        return getWords(text).size();
    }

    /**
     * Необходимо реализовать функционал подсчета количества уникальных слов в тексте (с учетом регистра).
     * Например для текста "One, two, three, three - one, tWo, tWo!!" - данный метод должен вернуть 5.
     * param text текст
     */
    @Override
    public int countNumberOfUniqueWords(String text) {
        HashSet<String> hashSet = new HashSet<>();
        for(String word: getWords(text)){
            hashSet.add(word);
        }
        return hashSet.size();
    }

    /**
     * Необходимо реализовать функционал получения списка слов из текста.
     * Пробелы, запятые, точки, кавычки и другие знаки препинания являются разделителями слов.
     * Например для текста "One, two, three, three - one, tWo, tWo!!" должен вернуться список :
     * {"One", "two", "three", "three", "one", "tWo", "tWo"}
     *
     * @param text текст
     */
    @Override
    public List<String> getWords(String text) {
       List<String> list = new ArrayList<>();
       text = text.replaceAll("\n"," ");
       list.addAll(Arrays.asList(text.replaceAll("\\p{Punct}","").replaceAll("\\s+"," ").split(" ")));
       return list;

    }


    /**
     * Необходимо реализовать функционал получения списка уникальных слов из текста.
     * Пробелы, запятые, точки, кавычки и другие знаки препинания являются разделителями слов.
     * Например для текста "One, two, three, three - one, tWo, tWo!!" должен вернуться список :
     * {"One", "two", "three", "one", "tWo"}
     *
     * @param text текст
     */
    @Override
    public Set<String> getUniqueWords(String text) {
        HashSet<String> hashSet = new HashSet<>();
        for(String word: getWords(text)){
            hashSet.add(word);
        }
        return hashSet;
    }

    /**
     * Необходимо реализовать функционал подсчета количества повторений слов.
     * Например для текста "One, two, three, three - one, tWo, tWo!!" должны вернуться результаты :
     * {"One" : 1, "two" : 1, "three" : 2, "one" : 1, "tWo" : 2}
     *
     * @param text текст
     */
    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        HashMap<String,Integer> hashMap = new HashMap<>();
        List<String> list = new ArrayList();
        list = getWords(text);
        int count = 0;
        for (int i = 0; i < list.size(); i++){
            for (int j = 0; j < list.size(); j++){
                if (list.get(i).equals(list.get(j))){
                    count++;
                }
            }
            hashMap.put(getWords(text).get(i),count);
            count = 0;
        }
        return hashMap;
    }

    /**
     * Необходимо реализовать функционал вывода слов из текста в отсортированном виде (по длине) в зависимости от параметра direction.
     * Например для текста "Hello, Hi, mother, father - good, cat, c!!" должны вернуться результаты :
     * ASC : {"mother", "father", "Hello", "good", "cat", "Hi", "c"}
     * DESC : {"c", "Hi", "cat", "good", "Hello", "father", "mother"}
     *
     * @param text текст
     */
    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        List<String> list = getWords(text);
        if (direction == Direction.ASC){
            list.sort((o1, o2) -> {
                if (o1.length() > o2.length()) {
                    return +1;
                }
                else if (o1.length()==o2.length()){
                    return 0;
                }
                else{
                    return -1;
                }
            });
            System.out.println(list);
        }
        if (direction == Direction.DESC) {
            list.sort((o1, o2) -> {
                if (o1.length() > o2.length()) {
                    return -1;
                }
                else if (o1.length()==o2.length()){
                    return 0;
                }
                else{
                    return +1;
                }
            });
        }
        return list;
    }


}