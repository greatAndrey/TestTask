package third.gfl;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by Razzhyvyn Andrei on 04.03.2018.
 */
public class Main extends ZipArchivator {

    @Override
    public void smartPrinter(String text) {

    }

    public static void main (String[] args){

        System.out.println("Please, enter file path");

        BufferedReader readerPath = new BufferedReader( new InputStreamReader(System.in));
        String inputQ = null;
        try {
            inputQ = readerPath.readLine();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


        File filename = new File(inputQ);
//        System.out.println("is File: "+filename.isFile());
//        System.out.println("is Existed: "+filename.exists());
        if (filename.exists() == false) {
            System.out.print("File path doesn't exists");
            System.exit(1);
        }
        System.out.println(filename.lastModified());

        System.out.println("Please, enter zip name");
        BufferedReader readerZip = new BufferedReader( new InputStreamReader(System.in));
        try {
            inputQ = readerZip.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Main test = new Main();
        test.archiveInfo();

    }
}


