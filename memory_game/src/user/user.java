package user;

import menu.menu;
import conn.conn;
import login.login;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

// IK HEB EEN CLAS USER GEMAAKT (DIT ZIJN DE PERSONEN DIE HET SPEL ZULLEN SPELEN) - SURAJ MEGHOE
public class user {

    // DECLARATIE VAN DE USER VARIABLES - SURAJ MEGHOE
    public static String user_usernaam;
    public static String user_password;
    public static String user_naam;
    public static String user_achternaam;
    public static String user_geboortedatum;

    //HIER HEB IK EEN METHOD GEMAAKT OM DE GEGEVENS VAN DE USER TE KRIJGEN  - SURAJ MEGHOE
    // GEBRUIK GEMAAKT VAN EEN SCANNER - SURAJ MEGHOE
    public static String[] getUserInfo() {
        Scanner input = new Scanner(System.in);

        System.out.println("Voer uw naam in:");
        user_naam = input.nextLine();

        System.out.println("Voer uw achternaam in");
        user_achternaam = input.nextLine();

        System.out.println("Voer uw UserName in:");
        user_usernaam = input.nextLine();

        System.out.println("Voer uw password in:");
        user_password = input.nextLine();

        System.out.println("Voer uw geboortedatum in (jaar-maand-dag)");
        user_geboortedatum = input.nextLine();

        // HIER HEB IK EEN FORMAT GEBRUIKT OM DE JUISTE DATE IN DE DATABASE TE PLAATSEN - SURAJ MEGHOE
        try {
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(user_geboortedatum);
        } catch (ParseException e) {
            System.out.println("U heeft uw geboortedatum niet goed ingevuld");
            System.out.println("Probeer het nog eens");
            user_geboortedatum = input.nextLine();
        }



        return new String[]{user_usernaam, user_password, user_naam, user_achternaam, user_geboortedatum};
    }
    // HIER HEB IK EEN METHOD GEMAAKT WAAR DE USER DE MOGELIJKHEID KRIJGT OM ZICH TE REGISTREREN - SURAJ MEGHOE
    public static void UserInsert(String[] user) throws Exception {
        java.sql.Connection con = conn.getConnection();
        // INSERT QUERY - SURAJ MEGHOE
        String sql = "insert into users (user_usernaam,user_password,user_naam,user_achternaam,user_geboortedatum) values (?,?,?,?,?);";
        PreparedStatement sm = con.prepareStatement(sql);
        sm.setString(1, user[0]);
        sm.setString(2, user[1]);
        sm.setString(3, user[2]);
        sm.setString(4, user[3]);
        sm.setString(5, user[4]);
        // EXECUTE JE QUERY - SURAJ MEGHOE
        int i = sm.executeUpdate();
        // SLUIT CONNECTION - SURAJ MEGHOE
        conn.CloseDatabaseConnection(con);
        System.out.printf("%s %s Successvol toegevoegd", user[2], user[3]);
    }



}
