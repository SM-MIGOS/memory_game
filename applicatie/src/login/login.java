package login;

import conn.conn;
import user.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

// HIER HEB IK EEN LOGIN CLASS GEMAAKT - SURAJ MEGHOE
public class login
{
    // DIT IS DE LOGIN VAN DE USER - SURAJ MEGHOE
    // VARIABLE DECLARATIE - SURAJ MEGHOE
    public static String user_usernaam1;
    public static String user_password1;

    //HIER HEB IK EEN METHOD GEMAAKT OM DE GEGEVENS VAN DE USER TE KRIJGEN  - SURAJ MEGHOE
    public static String[] getloginInfo() {
        Scanner input = new Scanner(System.in);
        System.out.println("Voer uw UserName in:");
        user_usernaam1 = input.nextLine();

        System.out.println("Voer uw password in:");
        user_password1 = input.nextLine();

        return new String[]{user_usernaam1, user_password1};
    }

    // HIER HEB IK EEN METHOD GEMAAKT WAAR DE LOGIN PLAATS VIND - SURAJ MEGHOE
    public static void login2(String[] log) throws Exception {
        java.sql.Connection con = conn.getConnection();
        String sql = "select * from users where user_usernaam=?and user_password=?";
        PreparedStatement sm = con.prepareStatement(sql);
        sm.setString(1, log[0]);
        sm.setString(2, log[1]);
        ResultSet result = sm.executeQuery();

        // HIER IS ER EEN IF ELSE STATEMENT OM AAN TE GEVEN OF DE LOGIN SUCCESVOL OF NIET IS - SURAJ MEGHOE
        if (result.next()) {
            System.out.println("\nlogin succesvol\n");
            game2();
        } else {
            System.out.println("\nlogin is niet succesvol\n");
        }
    }

    // METHOD GEMAAKT OM EEN 2E MENU TE WEERGEVEN NADAT DE PERSOON INGELOGD IS IN HET SYSTEEM - SURAJ MEGHOE
    public static void Menu2() {
        System.out.println("Maak een selectie om verder te kunnen gaan");
        System.out.println("1. Start spel");
        System.out.println("2. Overzicht genereren top 10 spelers");
        System.out.println("3. Gegevens weizigen");
        System.out.println("0. terug");
    }

    static boolean exit = false;

    public static void game2() {
        while (!exit) {
            Menu2();
            int keuze = UserMenu2();
            corebase2(keuze);
        }
    }

    public static int UserMenu2() {
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
    // MENU NA LOGIN - SURAJ MEGHOE
    public static void corebase2(int keuze) {
        switch (keuze) {
            case 0:
                exit = true;
                System.out.println("Prettige dag verder");
                break;
            case 1:
                System.out.println("test1");
                break;
            case 2:
                System.out.println("test2");
                try {
                    view();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;

            case 3:

                String[] log = login.getupdateInfo();
                try {
                    login.wijzigen(log);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                System.out.println("error");
        }
    }
// DECLARATIE USER GEGEVENS
    public static String user_usernaam3;
    public static String user_password3;
    public static String user_naam3;
    public static String user_achternaam3;
    public static String user_geboortedatum3;

    // METHOD GEMAAKT VOOR HET BEWERKEN VAN USER GEGEVENS - SURAJ MEGHOE
    // HIER KRIJGT HIJ USER INPUT - SURAJ MEGHOE
    public static String[] getupdateInfo()
    {
        Scanner input = new Scanner(System.in);

        System.out.println("Voer uw naam in:");
        user_naam3= input.nextLine();

        System.out.println("Voer uw achternaam in:");
        user_achternaam3 = input.nextLine();

        System.out.println("Voer uw geboortedatum in (jaar-maand-dag):");
        user_geboortedatum3 = input.nextLine();

        System.out.println("Voer uw UserName in:");
        user_usernaam3 = input.nextLine();

        System.out.println("Voer uw password in:");
        user_password3 = input.nextLine();

        return new String[]{user_usernaam3, user_password3, user_naam3,user_achternaam3,user_geboortedatum3 };
    }

    // METHOD WAAR DE WIJZIGING VAN DE USER IN DE DATABASE GAAT - SURAJ MEGHOE
    public static void wijzigen(String[] log) throws Exception {
        java.sql.Connection con = conn.getConnection();
        String sql = "Update users set user_usernaam=?,user_password=?,user_naam=?,user_achternaam=?,user_geboortedatum=? WHERE user_usernaam=? ";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, log[0]);
        stmt.setString(2, log[1]);
        stmt.setString(3, log[2]);
        stmt.setString(4, log[3]);
        stmt.setString(5, log[4]);
        stmt.setString(6, user_usernaam1);
        int rowsUpdated = stmt.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("Uw gegevens zijn geüpdatet");
        }


    }

    // METHOD OM DE TOP 10 SPELERS WEER TE GEVEN - SURAJ MEGHOE
    public static void view() throws Exception {
        {
            java.sql.Connection con = conn.getConnection();
            String sql = "SELECT * FROM score join users on score.user_id = users.user_id WHERE score >10";
            PreparedStatement sm = con.prepareStatement(sql);
            ResultSet result = sm.executeQuery();

            while(result.next())
            {
                String naam= result.getString("user_naam");
                String achternaam= result.getString("user_achternaam");
                String score= result.getString("score");

                System.out.println("Naam: " + naam + " Achternaam: " + achternaam + " Score: " + score);

            }

        }
    }
}
