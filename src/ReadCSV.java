import javax.swing.*;
import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadCSV {

    private ArrayList<LocalEleStat> stats = new ArrayList<>(); // creates am arraylist of LocalEleStat classes
    private String [] headings; // array of headings

    public ReadCSV(File f) // constructor taking in a file
    {
         try{
            Scanner sc = new Scanner(f);

            sc.nextLine(); //skip first heading
            headings = sc.nextLine().split(","); //add second row to headings

            while (sc.hasNextLine())
            {
                try {
                    stats.add(new LocalEleStat(sc.nextLine())); // adds the line to the arraylist
                }
                catch (IllegalArgumentException ex)
                {
                    // do nothing
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();;
        }
    }

    public ArrayList<LocalEleStat> getStats() //  returns the arraylist LocalEleStat
    {
        return stats;
    }
    public String [] getHeadings() // returns the headings
    {
        return headings;
    }

    public void addStat(LocalEleStat s) // adds a stat to the arraylist
    {
        stats.add(s);
    }

    public void removeStat(String s)
    {
        int i = -1;
        for(LocalEleStat stat : stats) // looks through each element in the arraylist
        {
            if(stat.getNo().equals(s)) // if the number matches the input number then break the loop and set i to the index of the element
            {
                i = stats.indexOf(stat);
                break;
            }
        }

        if(i != -1)
        {
            stats.remove(i); // removes the element at i
        }
    }

    public void writeFile()
    {
        try {

            System.out.println("here");
            File f = new File("output"+ (int)((Math.random()*1000)) + ".csv"); // creates a unique name everytime the program is closed

            System.out.println(f.toString());
            f.createNewFile(); // creates a new file
            PrintWriter pw = new PrintWriter(f);

            for(LocalEleStat stat : stats) // adds each element of the arraylist to the file
            {
                pw.println(stat.toCSV());
            }

            pw.close(); // saves the file when it is finished
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
