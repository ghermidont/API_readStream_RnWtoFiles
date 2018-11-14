import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class AppStream1 {

    public static void main(String[] args) throws IOException {

        //vars declaration and initialization
        final String ADDRESS = "http://ip-api.com/csv";
        URL u;
        String ip;
        String ipFromList;
        String countryFromFile;
        BufferedReader brCountry = new BufferedReader(new FileReader("COUNTRY.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("COUNTRY.txt"));
        BufferedReader br = new BufferedReader(new FileReader("IP.txt"));

        //Initialize the "ipList" ArrayList
        ArrayList<String> ipList = new ArrayList<>();

        // Read from IP.txt file.
        try {

            //Add content to the "ipList" ArrayList
            while ((ip = br.readLine()) != null) {
                ipList.add(ip);
            }

            // Extract IPs from ipList ArrayList.
            for (int n = 0; n < ipList.size(); n++) {
                ipFromList = ipList.get(n);

                u = new URL(ADDRESS + "/" + ipFromList);

                // Read from stream
                Scanner in = new Scanner(u.openStream());
                String data = in.nextLine();

                //Content to write to COUNTRY.txt
                bw.write("IP: " + ipFromList + " is from ");

                int commas = 0;
                for (int index = 0; index < data.length(); index++) {
                    if (data.charAt(index) == ',') {
                        commas++;
                    }
                    if (commas == 1 && data.charAt(index) != ',') {
                        bw.write(data.charAt(index));
                    }
                }

                bw.write("\n");

            }

            bw.close();

            // Read from COUNTRY.txt
            while ((countryFromFile = brCountry.readLine()) != null) {
                System.out.println(countryFromFile);
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }





//HW JSON/XML
//github.com/toddmotto/public-apis
    /* 2 example
       String message = "Hello String Data Type! String is a Class in Java";

       if(message.toLowerCase().contains("String".toLowerCase())){//here we transfer all messages to lower case and compare them. in order to let the use input any case.

           System.out.println("The message contains \" String \" !");

       }
       */

 /* 1 Example
 Scanner in = new Scanner(System.in);
        String food = ""; // compilator print int the back ground - new String("");

        System.out.println( "Enter food: ");
        food = in.next();

        if ( food.equals( "Pizza")){
            System.out.println( "COST: 75 lei");
        }
        */

    }
}