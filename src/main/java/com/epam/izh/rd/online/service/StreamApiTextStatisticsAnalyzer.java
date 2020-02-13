package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Collections.*;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
       return getWords(text).stream().mapToInt(String::length).sum();
    }

    @Override
    public int countNumberOfWords(String text) {
        List<String> list = getWords(text);
        return (int) list.stream().count();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return  (int) getWords(text).stream().distinct().count();
    }

    @Override
    public List<String> getWords(String text) {
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(text
                .replaceAll("\\p{Punct}","").replaceAll("\n"," ").replaceAll("\\s+"," ")
                .split(" ")));
        return list;
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return getWords(text).stream().collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
          return  getWords(text).stream().collect(Collectors.groupingBy(Function.identity(),Collectors.collectingAndThen((Collectors.counting()), Long::intValue)));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        if (direction == Direction.ASC){
           return getWords(text).stream()
                   .sorted((o1, o2) -> {
                    if (o1.length() > o2.length()){
                        return +1;
                    }
                    else if (o1.length() == o2.length()){
                        return 0;
                    }
                    else{
                        return -1;
                    } })
                   .collect(Collectors.toList());

        } else{
            return getWords(text).stream()
                    .sorted((o1, o2) -> {
                        if (o1.length() > o2.length()){
                            return -1;
                        }
                        else if (o1.length() == o2.length()){
                            return 0;
                        }
                        else{
                            return +1;
                        } })
                    .collect(Collectors.toList());
        }
    }
}
