package tycoongame.game.managers;

import java.util.ArrayList;
import java.util.List;

import tycoongame.game.save.SaveGameManager;
import tycoongame.records.Date;
import tycoongame.records.Log;
import tycoongame.records.Time;

public class LogManager {

    private List<Log> logs;

    public LogManager (List<Log> logs)
    {
        this.logs = logs;
    }

    public void addLog (double newBalance , double previousBalance , String message)
    {
        Date date = new Date(1);
        Time time = new Time();

        Log log = new Log(date, time, newBalance, previousBalance, message);
        logs.add(log);
        save();
        
    }

    public void save() 
    {
        SaveGameManager save = new SaveGameManager();
        List<String> data = new ArrayList<String>();

        for (Log log : logs)
            data.add(log.getSaveData());
        
        save.save(data, "res\\assets\\saves\\account\\account.txt");
    }

    /**
     * @return the logs
     */
    public List<Log> getLogs() {
        return logs;
    }

    public double getLastBal() 
    {
        Log lastLog = this.logs.get(this.logs.size() - 1);
        return lastLog.getNewBalance();
	}

    public int getLastDay() 
    {
        Log lastLog = this.logs.get(this.logs.size() - 1);
        return lastLog.getDay();
    }
    
}