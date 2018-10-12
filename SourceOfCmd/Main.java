import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Dictionary dic = new Dictionary();
        Scanner sc = new Scanner(System.in);
        int command;
        char ctn = 'c'; //continue
        dic.insertFromFile();
        while (ctn != 'k'){
            System.out.println("---------Tu dien Anh-Viet commandline---------");
            System.out.println("Ban muon lam gi?");
            System.out.println("1. Them 1 tu.");
            System.out.println("2. Xoa 1 tu.");
            System.out.println("3. Sua nghia tu da co.");
            System.out.println("4. Danh sach tu.");
            System.out.println("5. Tra cuu.");
            System.out.print("Lua chon cua ban: ");
            command = sc.nextInt();
            sc.nextLine(); //đọc dấu enter còn trong bộ nhớ đệm
            if (command == 1){
                System.out.println("Nhap tu tieng Anh: ");
                String anh = sc.nextLine();
                System.out.println("Nhap nghia tieng Viet:");
                String viet = sc.nextLine();
                dic.insertFromCommandline(new Word(anh, viet));
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
             } else if (command == 4){
                 dic.showAll();
               }
                else if (command == 5){
                 dic.search();
                }
            System.out.print("Ban muon tiep tuc khong? (c/k) ");
             ctn = sc.next().charAt(0);
            System.out.println("------------------------------------");
        }
        System.out.println("Cam on ban da su dung!");
    }
}