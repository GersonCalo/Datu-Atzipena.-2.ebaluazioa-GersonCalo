package damci;

import java.util.Scanner;

import damci.main.csv.CSVToMongo;
import damci.main.csv.CSVToMongoNazioak;

public class menu {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("DATUAK ATZITU");
        System.out.println("Zer egin  nahi duzu?");
        System.out.println("1-Datuak importatu.");
        System.out.println("2-Datuak exportatu.");

        String opcion = scanner.nextLine();

        switch (opcion) {
            case "1":
                importatuMenua();
                break;
            case "2":
                exportatuMenua();
                break;

            default:
                System.out.println("ez duzu aukera zuzena aukeratu ");
                break;
        }
        scanner.close();
    }
//INPORTAZIOAK
    public static void importatuMenua() {
        System.out.println("\n\nDATUAK IMPORTATU");
        System.out.println("ze fitxategi mota inportatu nahi duzu?");
        System.out.println("1-CSV   2-XML   3-JSON");
        String opcion = scanner.nextLine();

        System.out.println("\n\nFILTROA");
        System.out.println("Zer nazio importatuko diren aukeratu nahi duzu?");
        System.out.println("1-Bai   2-Ez");
        String filtroa = scanner.nextLine();

        if(filtroa.equals("1")){
            System.out.println("Sartu nazioak koma batez banaduta mesedez.\n" + //
                    "Adibidea: Spain, Algeria, Angola");
            String listaNazioak = scanner.nextLine();
            String[] nazioakBanatuta = listaNazioak.split(",");
            /*for(int i =0 ; i <nazioakBanatuta.length;i++){
                System.out.println(nazioakBanatuta[i]);
            }*/
            switch (opcion) {
                case "1":
                    CSVToMongoNazioak.main(nazioakBanatuta);
                    break;
                case "2":
                    System.out.println(opcion);
                    break;
                case "3":
                    System.out.println(opcion);
                    break;
                default:
                    System.out.println("ez duzu aukera zuzena aukeratu ");
                    break;
            }

        }else{

            switch (opcion) {
                case "1":
                    CSVToMongo.main(null);
                    break;
                case "2":
                    System.out.println(opcion);
                    break;
                case "3":
                    System.out.println(opcion);
                    break;
                default:
                    System.out.println("ez duzu aukera zuzena aukeratu ");
                    break;
            }
        }
        
    }

    





    public static void exportatuMenua(){
        System.out.println("\n\nDATUAK EXPORTATU");
        System.out.println("ze fitxategi mota exportatu nahi duzu?");
        System.out.println("1-CSV   2-XML   3-JSON");
        String opcion = scanner.nextLine();

        System.out.println("\n\nFILTROA");
        System.out.println("Zer nazio exportatuko diren aukeratu nahi duzu?");
        System.out.println("1-Bai   2-Ez");
        String filtroa = scanner.nextLine();

        if(filtroa.equals("1")){
            System.out.println("Sartu nazioak koma batez banaduta mesedez.\n" + //
                    "Adibidea: Spain, Algeria, Angola");
            String listaNazioak = scanner.nextLine();
            String[] nazioakBanatuta = listaNazioak.split(",");
            for(int i =0 ; i <nazioakBanatuta.length;i++){
                System.out.println(nazioakBanatuta[i]);
            }
            switch (opcion) {
                case "1":
                    System.out.println(opcion);
                    break;
                case "2":
                    System.out.println(opcion);
                    break;
                case "3":
                    System.out.println(opcion);
                    break;
                default:
                    System.out.println("ez duzu aukera zuzena aukeratu ");
                    break;
            }

        }else{

            switch (opcion) {
                case "1":
                    System.out.println(opcion);
                    break;
                case "2":
                    System.out.println(opcion);
                    break;
                case "3":
                    System.out.println(opcion);
                    break;
                default:
                    System.out.println("ez duzu aukera zuzena aukeratu ");
                    break;
            }
        }
        
    }
}
