package sample;

import java.io.*;
import java.rmi.server.ExportException;
import java.util.*;

public class DictionaryCommandLine extends DictionaryManagement {


    public void showAllWords() {
        System.out.println(String.format("No |%-10s|%-10s","Endlish","Vietnamese"));
        int temp = getWord().size();
        for(int i=0;i<temp;i++) {
            System.out.println(String.format("%-3d|%-10s|%-10s",i+1,getWord().get(i).getWord_target(),getWord().get(i).getWord_explain()));
        }
    }

    public void dictionaryBasic() {
        this.insertFromCommandline();
        this.showAllWords();
    }

    public void dictionaryAdvanced() throws IOException {
        this.insertFromFile();
        this.showAllWords();
        this.dictionaryLookup();
    }

    public List<Word> dictionarySearcher(String search) {
        List<Word> temp = new ArrayList<>();
        for(int i=0;i<getWord().size();i++) {
            if(getWord().get(i).getWord_target().contains(search)) temp.add(getWord().get(i));
        }
        return temp;
    }


}
