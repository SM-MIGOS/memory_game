package menu;

import user.user;
import conn.conn;
import login.login;
import menu.menu;

import java.sql.Connection;
import java.util.Scanner;

// HIER HEB IK DE MENU CLASS GEMAAKT - SURAJ MEGHOE
public class menu {

    // HIER HEB IK EEN METHOD GEMAAKT DIE DE MENU WEERGEEFT VAN HET SPEL - SURAJ MEGHOE
    public static void Menu() {
        System.out.println("\nWelkom bij Memory Game");
        System.out.println("Maak een selectie om verder te kunnen gaan");
        System.out.println("1. Registratie");
        System.out.println("2. Inloggen");
        System.out.println("3. Instructie");
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
        while (keuze < 0 || keuze > 3) {
            try {
                System.out.print("\nSchrijf uw keuze hier");
                keuze = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("U heeft niet goed gekozen");
            }
        }

        return keuze;
    }

    // METHOD HIER GEMAAKT DIE DE KEUZES AANGEEFT DOOR HET GEBRUIK MAKEN VAN EEN SWITCH STATEMENT - SURAJ MEGHOE
    // HET GEEFT AAN WAT ER ZAL GEBEUREN ALS DE GEBRUIKER ZIJN KEUZE MAAKT - SURAJ MEGHOE
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
                String[] stud = login.getloginInfo();
                try {
                    login.login2(stud);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;

            case 3:
                instr();
                break;
            default:
                System.out.println("error");
        }
    }
    // HIER HEB IK EEN METHOD GEMAAKT DIE INSTRUCTIES VAN HET SYSTEEM AAN DE GEBRUIKER GEEFT - SURAJ MEGHOE
    // DEZE WORDT OPGEROEPEN IN DE MENU SWITCH HIER BOVEN  - SURAJ MEGHOE
    public static void instr()
    {
        System.out.println("");
        System.out.println("Welkom bij onze spel.");
        System.out.println("U krijgt de mogelijkheid om uw zelf te registreren of in te loggen.");
        System.out.println("Wanneer u ingelogd ben, krijgt u de volgende menu:");
        System.out.println("1. Start spel                         -> Dit brengt u naar onze spel.");
        System.out.println("2. Instructies spel                   -> Hier krijgt u verdere instructies van het spel zelf.");
        System.out.println("3. Overzicht genereren top 10 spelers -> U kunt hier de top 10 spelers van het spel zien.");
        System.out.println("4. Gegevens weizigen                  -> Wanneer u uw gegevens wens te weizigen kunt u het hier doen.");
        System.out.println("0. Beëindigen                         -> Dit gaat het spel geheel afsluiten.");
        System.out.println("Wij hopen dat wij u genoeg informatie hebben verschaf.");
        System.out.println("Heel veel succes!");
        System.out.println("");
    }


}
