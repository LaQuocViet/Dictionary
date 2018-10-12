import java.io.*;
import java.util.*;

public class Dictionary extends Word{

    protected List<Word> data = new ArrayList<Word>();

    //hiện thị tất cả các từ co trong dữ liệu
    public void showAll(){
        int n = data.size();
        for(int i=0; i<n; i++){
            System.out.print(data.get(i).eng);
            for(int j=0; j<25-data.get(i).eng.length(); j++)
                System.out.print(" ");
            System.out.println(data.get(i).vie);
        }
    }

    //đọc dữ liệu từ file data.txt, lưu ý: mỗi dòng trong file phải có dạng english/tieng viet
    public void insertFromFile(){
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

    //ghi dữ liệu hiện tại vào file data.txt, lưu ý: ghi đè, không thêm vào cuối
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

    //so sánh 2 từ tiếng Anh, từ nào đứng sau theo bảng chữ cái sẽ lớn hơn
    public int compare(Word o1, Word o2) {
        int w1Length = o1.eng.length();
        int w2Length = o2.eng.length();
        int min = w2Length;
        if (w1Length < w2Length) min = w1Length;
        for(int i = 0; i < min; i++){
            if ((int)o1.eng.charAt(i) > (int)o2.eng.charAt(i)) return 1;
            else if ((int)o1.eng.charAt(i) < (int)o2.eng.charAt(i)) return -1;
        }
        if (w1Length > min) return 1;
        else if (w2Length > min) return -1;
        return 0;
    }


    //thêm 1 từ vào vị trí sao cho dữ liệu vẫn theo trật tự chữ cái
    public void insertFromCommandline(Word word){
        boolean duplicate = false;
        int n = data.size();
        for(Word i : data){
            if (i.eng.equals(word.eng)) {duplicate = true; break;}
        }
        if (duplicate == true){
            System.out.println("Tu ban muon them da co trong tu dien!");
        }else{
        if (compare(word,data.get(0)) == -1){
            data.add(0,word);
        }else if (compare(word,data.get(n-1)) == 1){
             data.add(n,word);
            } else for(int i = 0; i < n-1; i++){
            if (compare(word,data.get(i)) == 1 && compare(word,data.get(i+1)) == -1){
                data.add(i+1, word);
                break;
            }
        }
        exportToFile();
        System.out.println("Da them tu \"" + word.eng + "\"");
        }
    }
    //xóa 1 từ
    public boolean deleteAWord(String word){
        int n = data.size();
        for (int i=0; i<n; i++){
            if (data.get(i).eng.equals(word)) {data.remove(i); exportToFile(); return true;}
        }
        return false;
    }
    //tìm kiếm theo từ tiếng anh, có thế nhập vào 1 hoặc kí tự, kết thúc khi phát hiện dấu '.', hoặc khi tìm thấy từ, hoặc khi không tìm thấy từ
    public void search(){
            String searchingWord = "";
            String elementOfSearchingWord;
            int pos = 0, expected = -1;
            boolean stop = false;
            int length;
            Scanner scr = new Scanner(System.in);
            while (data.size()>0 && data.size()!=1 && !stop){
                System.out.print("tu can tim: " + searchingWord);
                elementOfSearchingWord = scr.nextLine();
                length = elementOfSearchingWord.length();
                if (elementOfSearchingWord.charAt(0) == '.' ) break;
                if (expected != -1){data.remove(expected); expected = -1;}
                if (elementOfSearchingWord.charAt(length-1) == '.' ){
                    stop = true;
                    elementOfSearchingWord = elementOfSearchingWord.substring(0,length-1);
                    length--;
                }
                searchingWord += elementOfSearchingWord;
                for(int i=0; i<data.size(); i++){
                    if (data.get(i).eng.length() < searchingWord.length()) {data.remove(i); i--;}
                     else if (data.get(i).eng.equals(searchingWord)) {expected = i;}
                      else for(int j=0; j<length; j++){
                         if (data.get(i).eng.charAt(pos+j) != elementOfSearchingWord.charAt(j)) {data.remove(i); i--; break;}
                    }
                }
                pos+=length;
                showAll();
                System.out.println("-------------------------------------------");
            }
            if (data.size() == 1)System.out.println("Xong! " + data.get(0).eng + ": " + data.get(0).vie);
             else if (expected != -1) System.out.println("Xong! " + data.get(expected).eng + ": " + data.get(expected).vie);
             else System.out.println("Khong tim thay tu ban muon tim!");
            data.clear();
            insertFromFile() ;
    }

    //sửa nghĩa của 1 từ đã có trong từ điển
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

