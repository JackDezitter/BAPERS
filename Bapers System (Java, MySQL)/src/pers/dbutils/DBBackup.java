package pers.dbutils;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DBBackup {
    Connection connection = DBConnection.getConnection();

    public DBBackup() {
    }

    public static boolean BackUp() {
        String path = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump.exe";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String filename = LocalDate.now().format(formatter) + ".sql";
        String command = path + " -u root -pTeam23 database -r backups\\" + filename;
        System.out.println(command);
        Process p = null;

        try {
            Runtime runtime = Runtime.getRuntime();
            p = runtime.exec(command);
            int processComplete = p.waitFor();
            System.out.println(processComplete);
            if (processComplete == 0) {
                System.out.println("Backup created successfully");
                return true;
            } else {
                System.out.println("Could not create the backup");
            }
        } catch (InterruptedException var7) {
            var7.printStackTrace();
        } catch (IOException var8) {
            var8.printStackTrace();
        }
        return false;
    }
}
