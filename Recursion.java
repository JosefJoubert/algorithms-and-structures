
public class Recursion {
    
        public int recursion(int num)
        {
        if (num <= 6) return num;
        return recursion((num/2)-3) +1; 
    }

    
}
