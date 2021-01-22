/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author fanss
 */
public class FileUtility {

    public static final String FILE_TO_SAVE_GAME = "savedgame.bin";

    public static void saveGameToFile(File file, Object o) throws IOException {
        ObjectOutputStream oout = null;

        try {

            oout = new ObjectOutputStream(new FileOutputStream(file));
            oout.writeObject(o);

        } finally {
            if (oout != null) {
                oout.close();
            }
        }
    }

    public static Object retrieveGameFromFile(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream oin = null;

        try {

            oin = new ObjectInputStream(new FileInputStream(file));
            return oin.readObject();

        } finally {
            if (oin != null) {
                oin.close();
            }
        }
    }

}
