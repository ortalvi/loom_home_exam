package com.ortal.exam;

import java.util.Map;

/**
 * Created by Maor on 30/06/2017.
 */
public class Sentence {
    private String sentence;
    private Map<String, Integer> wordsCount;
    private int length;

    public Sentence(String sentence, Map<String, Integer> wordsCount, int length) {
        this.sentence = sentence;
        this.wordsCount = wordsCount;
        this.length = length;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public Map<String, Integer> getWordsCount() {
        return wordsCount;
    }

    public void setWordsCount(Map<String, Integer> wordsCount) {
        this.wordsCount = wordsCount;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
