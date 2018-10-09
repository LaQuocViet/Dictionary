import java.io.*;
import java.util.*;

public class Dictionary extends Word{

    public void showAll(){
        int n = data.size();
        for(int i=0; i<n; i++){
            System.out.print(data.get(i).eng);
            for(int j=0; j<25-data.get(i).eng.length(); j++)
                System.out.print(" ");
            System.out.println(data.get(i).vie);
        }
    }

    public void getDataFromFile(){
        try {
            File file = new File( "data.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str, strE = "", strV = "";
            int i;
            while ((str=bufferedReader.readLine())!=null){
                for(i=0; str.charAt(i) != '/'; i++){
                   strE+=str.charAt(i);
                }
                for(i=i+1; i<str.length(); i++){
                    strV+=str.charAt(i);
                }
                Word w = new Word(strE, strV);
                data.add(w);
                i=0;
                strE = "";
                strV = "";
            }
            fileReader.close();
            bufferedReader.close();
        }catch (Exception e){}
    }

    public void exportToFile(){
        try{

            File f = new File( "data.txt");
            FileWriter fw = new FileWriter(f);
            int n = data.size();
            for(int i=0; i<n; i++) {
                fw.write(data.get(i).eng + "/" + data.get(i).vie + "\n");
            }
            fw.close();
        }catch (Exception e){}
    }

    public void addWord(Word word){
        data.add(word);
        exportToFile();
        System.out.println("Da them tu \"" + word.eng + "\"");
    }

    public boolean deleteAWord(String word){
        int n = data.size();
        for (int i=0; i<n; i++){
            if (data.get(i).eng.equals(word)) {data.remove(i); exportToFile(); return true;}
        }
        return false;
    }

    public void search(){
            String searchingWord = "";
            String elementOfSearchingWord;
            int pos = 0, expected = -1;
            Scanner scr = new Scanner(System.in);
            while (data.size()>0 && data.size()!=1){
                System.out.print("tu can tim: " + searchingWord);
                elementOfSearchingWord = scr.nextLine();
                if (elementOfSearchingWord.charAt(0) == '.') break;
                if (expected != -1){data.remove(expected); expected = -1;}
                searchingWord += elementOfSearchingWord;
                for(int i=0; i<data.size(); i++){
                    if (data.get(i).eng.equals(searchingWord)) {expected = i;}
                    if (data.get(i).eng.charAt(pos) != elementOfSearchingWord.charAt(0)) {data.remove(i); i--;};
                }
                pos++;
                showAll();
                System.out.println("-------------------------------------------");
            }
            if (data.size() == 1)System.out.println("Xong! " + data.get(0).eng + ": " + data.get(0).vie);
             else if (expected != -1) System.out.println("Xong! " + data.get(expected).eng + ": " + data.get(expected).vie);
             else System.out.println("Khong tim thay tu ban muon tim!");
            data.clear();
            getDataFromFile();
    }

    public void editWord(){
             Scanner scr = new Scanner(System.in);
             String wordNeedsToEdit, newMeaning;
             int n = data.size();
             boolean isEdited = false;
             System.out.println("Nhap tu can sua: ");
             wordNeedsToEdit = scr.nextLine();
             for(int i = 0; i < n; i++){
                 if (data.get(i).eng.equals(wordNeedsToEdit)){
                     System.out.println("Nhap nghia moi: ");
                     newMeaning = scr.nextLine();
                     data.get(i).vie = newMeaning;
                     isEdited = true;
                     exportToFile();
                     System.out.println("Sua thanh cong!");
                     break;
                 }
             }
             if (!isEdited) System.out.println("Khong tim thay tu can sua!");
    }
}
