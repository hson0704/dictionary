package sample;

import java.util.Scanner;
import java.io.*;

public class DictionaryManagement extends Dictionary {

    public void insertFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        int soluong = scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<soluong;i++) {
            String a = scanner.nextLine();
            String b = scanner.nextLine();
            addWord(new Word(a,b));
        }
    }

    public void insertFromFile() throws IOException {
        FileReader fr = new FileReader("dictionaries.txt");
        BufferedReader br = new BufferedReader(fr);
        String temp = null;
        while ((temp = br.readLine()) != null) {
            int i = 0;
            while(temp.charAt(i)!='\t') {
                i++;
                if(i==temp.length()) break;
            }
            addWord(new Word(temp.substring(0,i),temp.substring(i+1,temp.length())));
        }
        fr.close();
        br.close();
    }

    public void dictionaryExportToFile() throws IOException {
        FileWriter fw = new FileWriter("dictionaries.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < getWord().size(); i++) {
            bw.write(getWord().get(i).getWord_target()+ "\t" + getWord().get(i).getWord_explain());
        }
        fw.close();
        bw.close();
    }

    public void dictionaryLookup() {
        int i=0;
        String hienthi = "";
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Nhập từ cần tìm kiếm");
            String find = scanner.nextLine();
            while(!find.equals(getWord().get(i).getWord_target())) {
                i++;
            }
        } catch(Exception e) {
            System.out.println("Không tìm thấy");
            return;
        }
        System.out.println("Kết quả: " + getWord().get(i).getWord_explain());
    }

    public void remove() {
        System.out.println("Nhập vị trí muốn xóa: ");
        Scanner scanner = new Scanner(System.in);
        int pos = scanner.nextInt();
        editWord().remove(pos);
    }

    public void add() {
        System.out.println("Nhập vị trí muốn thêm: ");
        Scanner scanner = new Scanner(System.in);
        int pos = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nhập từ muốn thêm: ");
        String _word_target = scanner.nextLine();
        System.out.println("Nhập ý nghĩa của từ vừa thêm: ");
        String _word_explain = scanner.nextLine();
        editWord().add(pos, new Word(_word_target, _word_explain));
    }

    public void set() {
        System.out.println("Nhập vị trí muốn sửa: ");
        Scanner scanner = new Scanner(System.in);
        int pos = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nhập từ muốn sửa: ");
        String _word_target = scanner.nextLine();
        System.out.println("Nhập ý nghĩa của từ vừa sửa: ");
        String _word_explain = scanner.nextLine();
        editWord().set(pos, new Word(_word_target, _word_explain));
    }
}
