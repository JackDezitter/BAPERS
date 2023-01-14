//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package pers.dbutils;

import java.io.IOException;

public class DBRestore {
    public DBRestore() {
    }

    public static boolean Restore(String filename) {
        String path = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysql.exe";
        String restorePath = "C:\\Users\\spide\\OneDrive\\Documents\\UNI\\Year 2\\Team Project\\Assembly\\Final Comp\\TPGUI\\backups\\" + filename;
        String[] command = new String[]{path, "database", "-uroot", "-pTeam23", "-e", " source " + restorePath};
        System.out.println(command);
        Process p = null;

        try {
            Runtime runtime = Runtime.getRuntime();
            p = runtime.exec(command);
            int processComplete = p.waitFor();
            System.out.println(processComplete);
            System.out.println(restorePath);
            if (processComplete == 0) {
                System.out.println("Restored successfully");
                return true;
            } else {
                System.out.println("Could not restore");
            }
        } catch (InterruptedException var7) {
            var7.printStackTrace();
        } catch (IOException var8) {
            var8.printStackTrace();
        }
        return false;
    }
}
