package third.gfl;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by Razzhyvyn Andrei on 04.03.2018.
 */
public abstract class ZipArchivator implements Zipper {

    @Override
    public void archiveInfo() {
        try(ZipInputStream zin = new ZipInputStream(new FileInputStream("text.zip")))
        {
            ZipEntry entry;
            String name;
            long size;
            while((entry=zin.getNextEntry())!=null){

                name = entry.getName(); // получим название файла
                size=entry.getSize();  // получим его размер в байтах
                System.out.println("Name: "+name+"; "+"Size: "+size+";");
            }
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void inputKey(){

    }

    public abstract void smartPrinter(String text);
}
