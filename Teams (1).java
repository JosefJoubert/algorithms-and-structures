
import java.util.ArrayList;
import java.util.Collections;


public class Teams {
    
    public static ArrayList<Integer> team1 = new ArrayList<Integer>();
    public static ArrayList<Integer> team2 = new ArrayList<Integer>();
    public static int minDiff;
    public static boolean[] myTeam;
    public static int[]members;

    
    public Teams(int[] iArr)
    {
        minDiff = Integer.MAX_VALUE;
        members = iArr;
        myTeam = new boolean[iArr.length];

    }
    
    public void solve(int index)
    {
        /*insert code here*/
    }
    
    public void updateLists()
    {
        ArrayList<Integer> tempLeft = new ArrayList<Integer>();
        ArrayList<Integer> tempRight = new ArrayList<Integer>();
        int i = 0;
        
        for(boolean item:myTeam)
        {
            if(item == true)
            {
                tempLeft.add(members[i]);
            }
            else
            {
                tempRight.add(members[i]);
            }
            i++;
        }
        team1 = tempLeft;
        team2 = tempRight;
    }
    
    public void print()
    {
        Collections.sort(team1);
        Collections.sort(team2);
        System.out.println(team1);
        System.out.println(team2);
        System.out.println("Difference between sets is: " + minDiff);
    }
    
    public int getDiffTeamStrength()
    {
        int team1Sum,team2Sum;
        int i = team1Sum = team2Sum = 0;
        
        for(boolean item:myTeam)
        {
            if(item == true)
            {
                team1Sum += members[i];
            }
            else
            {
                team2Sum += members[i];
            }
            i++;
        }
        return Math.abs(team1Sum - team2Sum);   
    }  
}
