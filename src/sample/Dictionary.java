package sample;

import sample.Word;

import java.util.*;

public class Dictionary {
    private List<Word> word = new ArrayList<>();

    public List<Word> getWord() {
        return new ArrayList<Word>(word);
    }

    public List<Word> editWord() {
        return this.word;
    }

    public void addWord(Word _word) {
        this.word.add(_word);
    }
}
