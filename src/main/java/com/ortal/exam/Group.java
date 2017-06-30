package com.ortal.exam;

import java.util.Map;
import java.util.Set;

public class Group {
    private Map<String, Integer> commonWords;
    private Set<String> sentences;
    private Set<String> differences;

    public void merge(Group group) {
        sentences.addAll(group.sentences);
        differences.addAll(group.differences);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("=====\n");
        for (String sentence : sentences) {
            sb.append(sentence);
            sb.append("\n");
        }

        sb.append("The changing word was: ");
        for (String dif : differences) {
            sb.append(dif);
            sb.append(" ");
        }
        sb.append("\n=====\n");

        return sb.toString();
    }

    public Map<String, Integer> getCommonWords() {
        return commonWords;
    }

    public void setCommonWords(Map<String, Integer> commonWords) {
        this.commonWords = commonWords;
    }

    public Set<String> getSentences() {
        return sentences;
    }

    public void setSentences(Set<String> sentences) {
        this.sentences = sentences;
    }

    public Set<String> getDifferences() {
        return differences;
    }

    public void setDifferences(Set<String> differences) {
        this.differences = differences;
    }
}