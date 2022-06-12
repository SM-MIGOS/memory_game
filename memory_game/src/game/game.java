package game;

import conn.conn;
import login.login;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.*;
import java.util.Random;
import java.util.Scanner;

// HIER IS ER EEN CLASS GEMAAKT GENAAMD GAME
public class game {

    // HIER ZIJN DE VARIABLEN GEDECLAREERD
    static boolean playing = true;
    static boolean boomboom = true;
    static int CountNo = 0;
    static int CountMatch = 0;
    static int CountAllow = 10;
    // ARRAY VAN HET SCHERM
    public static String[][] console = new String[4][5];
    // ARRAY VAN DE KAARTEN
    public static String[][] kaart = new String[4][5];
    public static Scanner scanner = null;

    public static int score = 0;

    // HIER IS ER EEN METHOD GEMAAKT OM HET SPEL D.M.V EEN FOR LOOP WEER TEGEVEN IN DE CONSOLE
    public static void scherm()
    {
        System.out.println("kolom    |  1  |  2  |  3  |  4  |  5  |");
        for (int k = 0; k < 4; k++) {
            System.out.print("Rij " + (k + 1) +"    | ");

            for (int l = 0; l < 5; l++) {
                System.out.print(console[k][l]);
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    // HIER IS ER EEN METHOD GEMAAKT OM DE KAARTEN WEER TE GEVEN IN HET SPEL
    // DIT IS GEDAAN D.M.V EEN ARRAYLIST
    // ER IS  HIER GEBRUIK GEMAAKT VAN DE RANDOM CLASS DIE DUS RANDOM KAARTEN UITDEELT
    public static void randomkaarten()
    {
        Random kaarten = new Random();
        ArrayList<String> symbols = new ArrayList<String>();
        symbols.add("!");
        symbols.add("@");
        symbols.add("#");
        symbols.add("$");
        symbols.add("%");
        symbols.add("^");
        symbols.add("&");
        symbols.add("*");
        symbols.add("(");
        symbols.add(")");
        symbols.add("!");
        symbols.add("@");
        symbols.add("#");
        symbols.add("$");
        symbols.add("%");
        symbols.add("^");
        symbols.add("&");
        symbols.add("*");
        symbols.add("(");
        symbols.add(")");

        int randomgetal;
        for (int k = 0; k < 4; k++) {
            for (int l = 0; l < 5; l++) {
                randomgetal = kaarten.nextInt(symbols.size());
                kaart[k][l] = symbols.get(randomgetal);
                symbols.remove(randomgetal);
            }

        }

    }
    // HIER IS ER EEN METHOD GEMAAKT VOOR HET OPTELLEN VAN DE BONUS IN DE SCORE
    public static void addBonus() {
        int a = (10 - CountNo) * 3;
        score += a;
    }

    public static boolean gameOver() {
//     System.out.println(Arrays.deepToString(console));
        for (int k = 0; k < 4; k++) {
            for (int l = 0; l < 5; l++) {
                if (console[k][l].equals(" - ") && CountNo != CountAllow) {
                    return false;
                }
            }
        }
        return true;
    }
// HIER IS ER EEN METHOD GEMAAKT OM TE CONTROLEREN OF DE GEBRUIKER 10 FOUTEN HEEFT GEMAAKT OF NIET
    public static void checkfout()
    {
        if (CountNo == CountAllow) {
            System.err.println("\nU heeft 10 keren fout gegist\nHet spel wordt gesloten");
            System.out.println("Uw score is nu " + score + ", u heeft helaas verloren " );
            System.out.println("");
            boomboom=false;
            try {
                insertscore();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            begin();
        }
        if (gameOver()) {
            addBonus();
        }
    }

// HIER IS ER EEN METHOD GEMAAKT MET ALLES DIE MET HET SPEL TE MAKEN HEEFT
    public static void spel(String[][] kaart)
    {
        while (boomboom) {
            if (!gameOver()) {
               // System.out.println(Arrays.deepToString(kaart));
                System.out.println("Vul hier de 1e rij in die u wenst te openen ");
                int rij1 = scanner.nextInt();
                System.out.println("Vul hier de 1e kolom in die u wenst te openen ");
                int kolom1 = scanner.nextInt();

                int antw1 = rij1 - 1;
                int antw2 = kolom1 - 1;
            try {
                if (!console[antw1][antw2].equals(" - ")) {
                    System.out.print("u heeft deze rij en kolom al ingevuld ");
                    System.out.println();

                    scherm();
                    continue;
                } else {
                    console[antw1][antw2] = " " + kaart[antw1][antw2] + " ";
                    scherm();
                }
            }
            catch (Exception e) {
                System.err.println("U heeft een getal ingevuld groter dan wat het moet zijn");
                System.out.println("Voor elk rij is deze (1-4)");
                System.out.println("Voor elk kolom is deze (1-5)");
                System.out.println("Het spel wordt herstart");
                System.out.println("");
                begin();
            }

                System.out.println("Vul hier de 2e rij in die u wenst te openen ");
                int rij2 = scanner.nextInt();
                System.out.println("Vul hier de 2e kolom in die u wenst te openen");
                int kolom2 = scanner.nextInt();

                int antw3 = rij2 - 1;
                int antw4 = kolom2 - 1;

                try {
                    if (!console[antw3][antw4].equals(" - ")) {
                        System.out.println("u heeft deze rij en kolom al ingevuld ");
                        console[antw1][antw2] = " - ";
                        System.out.println();

                        scherm();
                        continue;
                    } else {
                        console[antw3][antw4] = " " + kaart[antw3][antw4] + " ";

                        if (console[antw1][antw2].equals(console[antw3][antw4])) {
                            scherm();
                            score += 2;
                            CountMatch++;
                            System.out.println("U heeft dit goed gegist, Goedzo");
                        } else {
                            scherm();
                            System.out.println("Helaas, heeft u dit niet goed gegist");
                            CountNo++;
                            console[antw1][antw2] = " - ";
                            console[antw3][antw4] = " - ";
                            scherm();
                        }
                    }
                }
                catch (Exception e) {
                    System.err.println("U heeft een getal ingevuld groter dan wat het moet zijn");
                    System.out.println("Voor elk rij is deze (1-4)");
                    System.out.println("Voor elk kolom is deze (1-5)");
                    System.out.println("Het spel wordt herstart");
                    System.out.println("");
                    begin();
                }


            } else {
                System.out.println("Het spel is beëindigd");


                if(CountNo == CountAllow)
                {
                    System.out.println("U heeft verloren");
                }
                else
                {
                    System.out.println("uw score is " + score + " Gefeleciteerd u heeft gewonnen!!! ");
                }


                System.out.println("");
                try {
                    insertscore();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                begin();
                break;
            }
            checkfout();
        }
    }
// HIER IS ER EEN METHOD GEMAAKT VOOR HET BEGIN VAN HET SPEL
    // ER WORDT HIER RANDOM KAARTEN IN HET SPEL UITGEDEELD
    public static void begin() {
        scanner = new Scanner(System.in);
        while (playing) {
            System.out.println("Welkom bij het spel van ons.");
            System.out.println("Indien u het spel wilt starten toets 'ja'");
            System.out.println("Indien u het spel wilt beëindigd toets 'nee'");

            String nq = scanner.nextLine();
            if (!nq.equals("nee") && !nq.equals("ja") ) {
                System.out.println("U heeft een fout gemaakt bij het invoeren van 'ja' of 'nee'.");
                System.out.println("Probeer het nog eens");
                continue;
            }
            if (nq.equals("nee")) {
                System.out.println("Fijne dag verder");
                boomboom=false;
                break;
            } else if (nq.equals("ja")) {
                boomboom=true;
                randomkaarten();
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 5; l++) {
                        console[k][l] = " - ";
                    }
                }
                CountNo = 0;
                score = 0;
                scherm();
                spel(kaart);
                break;
            }

        }
    }
    static String use = login.getUsername();

    static LocalDate datum = LocalDate.now();

    // HIER IS ER EEN METHOD GEMAAKT OM DE SCORE VAN ELK GAME IN DE DATABASE TE PLAATSEN.
    public static void insertscore() throws Exception
    {
        java.sql.Connection con = conn.getConnection();
        String sql = "INSERT INTO score(user_id,score,datum) VALUES((SELECT user_id from users where user_usernaam = ?),?,?)";
        PreparedStatement sm = con.prepareStatement(sql);
        sm.setString(1, use);
        sm.setString(2, String.valueOf(score));
        sm.setString(3, String.valueOf(datum));
        int i = sm.executeUpdate();
        // SLUIT CONNECTION - SURAJ MEGHOE
        conn.CloseDatabaseConnection(con);
    }


}
