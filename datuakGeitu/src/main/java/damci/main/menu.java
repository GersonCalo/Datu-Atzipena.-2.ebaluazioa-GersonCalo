package damci.main;

import java.util.Scanner;

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
        importatuMenuaFiltroa(opcion);
        
        /*switch (opcion) {
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
        }*/
    }

    public static void importatuMenuaFiltroa(String opcion){
        String fitxategiMota = opcion;

        System.out.println("\n\nFILTROA IMPORTATU");
        System.out.println("Zer nazio inportatuko diren aukeratu nahi duzu?");
        System.out.println("1-bia   2-ez");
        String filtroa = scanner.nextLine();

        switch (filtroa) {
            case "1":
                //menu para escoger las naciones
                break;
            case "2":
                //importar todos los registros
                break;
            default:
                System.out.println("ez duzu aukera zuzena aukeratu ");
                break;
            }
    }





    public static void exportatuMenua(){
        System.out.println("\n\nDATUAK EXPORTATU");
        System.out.println("ze fitxategi mota exportatu nahi duzu?");
        System.out.println("1-CSV   2-XML   3-JSON");
        String opcion = scanner.nextLine();
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
