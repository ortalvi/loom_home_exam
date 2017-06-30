package com.ortal.exam;

import java.util.*;

/**
 * Created by Ortal on 29/06/2017.
 */
public class Investigator {

    public Collection<Group> process(List<String> sentences) {
        Map<Integer, List<Sentence>> inputMapBySize = new HashMap<Integer, List<Sentence>>();

        for (String cur : sentences) {
            Sentence sentence = handleInput(cur);

            List<Sentence> lengthList;
            if (inputMapBySize.containsKey(sentence.getLength())) {
                lengthList = inputMapBySize.get(sentence.getLength());
            } else {
                lengthList = new ArrayList<Sentence>();
            }

            lengthList.add(sentence);
            inputMapBySize.put(sentence.getLength(), lengthList);
        }

        Map<Map<String, Integer>, Group> groups = findGroups(inputMapBySize);

        return groups.values();
    }

    /**
     * Receive a String representing a sentence and parse it
     * @param str
     */
    private Sentence handleInput(String str) {
        String[] words = str.split(" ");
        Map<String, Integer> wordsMap = new HashMap<String, Integer>();
        for (int i = 2; i < words.length; i++) {
            String curWord = words[i];
            Integer count = wordsMap.get(curWord);
            if (count == null) {
                wordsMap.put(curWord, 1);
            } else {
                wordsMap.put(curWord, count + 1);
            }
        }

        return new Sentence(str, wordsMap, words.length);
    }

    /**
     * compare two sentences and return group if they are similar
     * @param first
     * @param sec
     * @return group if similar or null if they aren't
     */
    private Group compareSimilarity(Sentence first, Sentence sec) {
        Map<String, Integer> firstMap = first.getWordsCount();
        Map<String, Integer> secMap = sec.getWordsCount();

        Map<String, Integer> differencesMap = new HashMap<String, Integer>(firstMap);
        Map<String, Integer> commonWords = new HashMap<String, Integer>();
        int count = 0;

        for (String cur : secMap.keySet()) {
            if (firstMap.containsKey(cur)) {
                int dif = Math.abs(firstMap.get(cur) - secMap.get(cur));
                if (dif == 0) {
                    differencesMap.remove(cur);
                } else {
                    differencesMap.put(cur, dif);
                }

                // put common word
                commonWords.put(cur, Math.min(firstMap.get(cur), secMap.get(cur)));
            } else {
                differencesMap.put(cur, secMap.get(cur));
            }
        }

        if (differencesMap.keySet().size() == 2) {
            for (Integer val : differencesMap.values()) {
                count += val;
            }

            if (count == 2) {
                Group group = new Group();
                group.setDifferences(new HashSet<String>(differencesMap.keySet()));
                group.setCommonWords(commonWords);
                Set<String> sentences = new HashSet<String>();
                sentences.add(first.getSentence());
                sentences.add(sec.getSentence());
                group.setSentences(sentences);
                return group;
            }
        }

        return null;
    }


    private Map<Map<String, Integer>, Group> findGroups(Map<Integer, List<Sentence>> inputMapBySize) {
        Map<Map<String, Integer>, Group> groups = new HashMap<Map<String, Integer>, Group>();

        for (List<Sentence> curList : inputMapBySize.values()) {
            for (int i = 0; i < curList.size() - 1; i++) {
                for (int j = i + 1; j < curList.size(); j++) {
                    Sentence first = curList.get(i);
                    Sentence sec = curList.get(j);
                    Group newGroup = compareSimilarity(first, sec);
                    if (newGroup != null) {
                        if (groups.containsKey(newGroup.getCommonWords())) {
                            Group existGroup = groups.get(newGroup.getCommonWords());
                            existGroup.merge(newGroup);
                        } else {
                            groups.put(newGroup.getCommonWords(), newGroup);
                        }
                    }
                }
            }
        }

        return groups;
    }

}
