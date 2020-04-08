package tycoongame.game.save;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import tycoongame.records.Date;
import tycoongame.records.Log;
import tycoongame.records.Time;

public class SaveGameManager {

    public void save(List<String> data, String filename) {
        String single = condense(data);
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(single);
            writer.close();
        } catch (IOException e) {
            System.err.println("SaveGameManager: Saving game to file " + filename + " -FAILED\n\n");
            e.printStackTrace();
        }
    }

    public void resetLog ()
    {
        String filename = "res\\assets\\saves\\account\\account.txt";
        try {
            FileWriter writer = new  FileWriter(filename);
            Log log = new Log(new Date(1), new Time(), 1000.0, 0.0, "Initial Balance");
            writer.write(log.getSaveData());
            writer.close();
        } catch (IOException e) {
            System.err.println("SaveGameManager: Resteting save at file " + filename + " -FAILED\n\n");
            e.printStackTrace();        
        }
    }

    public void reset(String filename) 
    {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            System.err.println("SaveGameManager: Resteting save at file " + filename + " -FAILED\n\n");
            e.printStackTrace();
        } 
	}

    private String condense(List<String> data) {
        String single = "";
        for (String s : data) {
            single += s;
            single += "\n";
        }
        return single;
    }

}