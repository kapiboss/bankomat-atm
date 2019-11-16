

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Set<BankAccount> set = new HashSet<>();
        File file = new File(".//data.txt");
        Scanner sc = new Scanner(file);
        List<String> list = new ArrayList<>();
        while (sc.hasNextLine()){
            list.add(sc.next());
        }
        for(int i=0;i<list.size();i+=3) {
            String accountNumber = list.get(i);
            String pinNumber = list.get(i+1);
            int balance = Integer.parseInt(list.get(i+2));
            BankAccount acc = new BankAccount(accountNumber,pinNumber,balance);
            set.add(acc);
        }
        BankAccount current = null;
        while(true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Wprowadz numer konta: ");
            String accountNumber = input.nextLine();
            for(BankAccount a:set) {
                if(a.getAccountNumber().equals(accountNumber)) {
                    current = a;
                }
            }
            if(current==null) {
                System.out.println("Konto nie znalezione!");
                continue;
            }
            break;
        }
        while(true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Wprowadz numer PIN: ");
            String pinNumber = input.nextLine();
            if(current.getPinNumber().equals(pinNumber)) break;
            System.out.println("Nieprawidlowy numer PIN!");
        }

        String menu = "1. Wplac\n2. Wyplac\n3. Saldo\n4. Wyjdz";
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        while(!exit){
            System.out.println(menu);
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Wprowadz kwote: ");
                    int amount=0;
                    System.out.println("Wprowadz kwote: ");
                    amount = input.nextInt();
                    while(amount<=0) {
                        System.out.println("Wprowadz poprawna kwote!");
                        amount = input.nextInt();
                    }
                    current.makeDeposit(amount);
                    break;
                case 2:
                    System.out.println("Wprowadz kwote: ");
                    amount = input.nextInt();
                    while(amount<=0) {
                        System.out.println("Wprowadz poprawna kwote");
                        amount = input.nextInt();
                    }
                    while(amount>current.getBalance()){
                        System.out.println("Zbyt malo srodkow na koncie, wprowadz inna kwote1");
                        amount = input.nextInt();
                    }
                    current.withdrawFunds(amount);
                    break;
                case 3:
                    System.out.println("Obecny stan konta: "+current.getBalance());
                    break;
                case 4:
                    exit = true;
                    break;
            }
        }
    }
}

