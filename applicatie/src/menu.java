import user.user;
import conn.conn;

import java.sql.Connection;
import java.util.Scanner;

// HIER HEB IK DE MENU CLASS GEMAAKT - SURAJ MEGHOE
public class menu {

    // HIER HEB IK EEN METHOD GEMAAKT DIE DE MENU WEERGEEFT VAN HET SPEL - SURAJ MEGHOE
    public static void Menu() {
        System.out.println("Welkom bij Memory Game");
        System.out.println("Maak een selectie om verder te kunnen gaan");
        System.out.println("1. Registratie");
        System.out.println("2. Inloggen");
        System.out.println("0. Beëindigen");
    }

    static boolean exit = false;

    // HIER HEB IK DE METHOD VAN DE MENU GEMAAKT - SURAJ MEGHOE
    // VANUIT HIER GAAT ALLES WERKEN - SURAJ MEGHOE
    public static void game() {
        while (!exit) {
            Menu();
            int keuze = UserMenu();
            corebase(keuze);
        }
    }

    // HIER HEB IK EEN METHOD WAAR DE USER ZIJN KEUZE MAAKT - SURAJ MEGHOE
    public static int UserMenu() {
        Scanner scan = new Scanner(System.in);
        int keuze = -1;
        while (keuze < 0 || keuze > 2) {
            try {
                System.out.print("\nSchrijf uw keuze hier");
                keuze = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("U heeft niet goed gekozen");
            }
        }
        return keuze;
    }

    // METHOD HIER GEMAAKT DIE AANGEEFT DOOR HET GEBRUIK MAKEN VAN EEN SWITCH STATEMENT - SURAJ MEGHOE
    // WAT ER MOET GEBEUREN ALS EEN USER HEEFT GEKOZEN - SURAJ MEGHOE
    public static void corebase(int keuze) {
        switch (keuze) {
            case 0:
                exit = true;
                System.out.println("Prettige dag verder");
                break;
            case 1:
                String[] studInfo = user.getUserInfo();
                try {
                    user.UserInsert(studInfo);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                String[] stud = user.getloginInfo();
                try {
                    user.login(stud);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                System.out.println("error");
        }
    }

}
