package tycoongame.game.factory;

import java.util.List;

public class NameFactory {

    private List<String> stockNames;
    private List<String> remaingNames;
    
    public void load ( String filename )
    {
        this.stockNames = FileReader.readTextFile(filename);
        for (String name : stockNames)
            this.remaingNames.add ( name.toString() );
    }

    public String getName ()
    {
        int index = (int) (Math.random() * remaingNames.size());
        String name = remaingNames.get(index);
        remaingNames.remove(index);
        
        return name;
    }

}