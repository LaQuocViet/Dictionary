import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Dictionary dic = new Dictionary();
        Scanner sc = new Scanner(System.in);
        int command;
        char ctn = 'c';
        dic.getDataFromFile();
        while (ctn != 'k'){
            System.out.println("Ban muon lam gi?");
            System.out.println("1. Them 1 tu.");
            System.out.println("2. Xoa 1 tu.");
            System.out.println("3. Sua nghia tu da co.");
            System.out.println("4. Tra cuu.");
            command = sc.nextInt();
            sc.nextLine();
            if (command == 1){
                System.out.println("Nhap tu tieng Anh: ");
                String anh = sc.nextLine();
                System.out.println("Nhap nghia tieng Viet:");
                String viet = sc.nextLine();
                dic.addWord(new Word(anh, viet));
            }
             else if (command == 2){
                System.out.println("Nhap tu tieng Anh can xoa: ");
                String tuCanXoa = sc.nextLine();
                if (!dic.deleteAWord(tuCanXoa)) {
                    System.out.println("Khong tim thay tu can xoa!");
                } else System.out.println("Da xoa tu \"" + tuCanXoa +"\"");
            }

             else if (command == 3){
                 dic.editWord();
            }
              else if (command == 4){
                 dic.search();
            }
            System.out.print("Ban muon tiep tuc khong? (c/k) ");
             ctn = sc.next().charAt(0);
            System.out.println("------------------------------------");
        }
        System.out.println("Cam on ban da su dung!");
    }
}