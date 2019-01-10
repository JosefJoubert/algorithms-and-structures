public class Diary
{
	/*Use this class to test your implementation.  This file will be overwritten for marking purposes.*/
	
	static int compare(int testCase, String s1, String s2, int mark)
	{
		System.out.println("Test Case: "+testCase+" testing if "+s1+" == "+s2);
		
		if(s1.equals(s2))
		{
			System.out.println("Awarded 1 mark, mark so far is: "+mark);
			return 1;
		}
		
		return 0;
	}
	
	static int trivialInserts(int currentMark)
	{
		System.out.println("Trivial inserts: \n===========");
		int mark = 0;
		//Test 1 insert Jan 1
		
		Calendar c = new Calendar();
		
		c.addAppointment("Jan", 1, "A", 4);
		
		Appointment a =  c.getAppointment("Jan", 1);
		
		mark+= compare(1,"A",a.getDescription(),mark);
		mark+= compare(2,"4",a.getDuration()+"",mark);
		
		
		//Test 2 insert Jun 5
		c.addAppointment("Jun", 5, "B", 3);
		a =  c.getAppointment("Jun", 5);
		
		mark+= compare(3,"B",a.getDescription(),mark);
		mark+= compare(4,"3",a.getDuration()+"",mark);
		
// 		Test 3 insert April 9
		c.addAppointment("Apr", 9, "C", 2);
		a =  c.getAppointment("Apr", 9);
		
		mark+= compare(5,"C",a.getDescription(),mark);
		mark+= compare(6,"2",a.getDuration()+"",mark);
		
		System.out.println("Mark for trivial inserts: "+mark+"\n===============");
		return mark;
		
	}
	
	static int advancedInserts()
	{
		System.out.println("Advanced inserts: \n===========");
		int mark = 0;
		int i = 0;//Test case counter
		int nameCounter = 0;
		int fromName = 0;
		
		
		Calendar c = new Calendar();
		
// 		String months[] = new String[]{"Feb","Mar","Apr","Jun","Aug"};
// 		String names[] = new String[]{"A","B","C","D","E","F","G","H","I","J"};
		
		//1
		for(int j = 1; j < 4; ++j)
			c.addAppointment(months[j], 3, names[nameCounter++], 4);
		
		c.addAppointment(months[5], 3, names[nameCounter++], 4);
		
		Appointment a = c.getAppointment(months[0],3);
		
		
		while(a!=null)
		{
			
			mark+= compare(i,names[fromName++],a.getDescription(),mark);
			a = a.right;
			++i;
		}
		
		//2
		c.addAppointment(months[3],7,names[nameCounter++],5);
		c.addAppointment(months[3],9,names[nameCounter++],5);
		
		
		a = c.getAppointment(months[3],3);
		
		while(a!=null)
		{
			mark+= compare(i,names[fromName++],a.getDescription(),mark);
			a = a.down;
			++i;
		}
		
		//3
		for(int j = 0; j < 4; ++j)
			c.addAppointment(months[7],15,names[nameCounter++],j+1);
		
		
		a = c.getAppointment(months[7],3);
		
		while(a!=null)
		{
			mark+= compare(i,names[fromName++],a.getDescription(),mark);
			a = a.down;
			++i;
		}
		
		System.out.println("Mark for advanced inserts: "+mark+"\n===============");
		
		return mark;
	}
	
	
	
	public static void main(String[] args)
	{
		//Write code to test your implementation here.
		
		int mark = Diary.trivialInserts(0);
	}
	
	static String months[] = new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	
	static String names[] = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P"};
	
}