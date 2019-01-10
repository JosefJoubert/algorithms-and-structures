/*Complete this class to implement a fully functional sparse table.  Read the comments to determine what each aspect of the class is supposed to do.
You must add any additional features (methods, references) which may aid you in your
task BUT you are not allowed to remove or change the names or properties of any of the features you where given.

Importing Java's built in data structures will result in a mark of 0.*/

public class Calendar
{
	Appointment[] months;
	Appointment[] days;
	public Calendar()
	{
		/*You may implement this constructor to suit your needs, or you may add additional constructors.  This is the constructor which will be used for marking*/
		months = new Appointment[12];
		days = new Appointment[30];
	}
	private int getimonth(String month) {
		
		month = month.toLowerCase();
		int imonth;
		if (month.equals("jan")) imonth = 0;
		else if(month.equals("feb")) imonth = 1;
		else if(month.equals("mar")) imonth = 2;
		else if(month.equals("apr")) imonth = 3;
		else if(month.equals("may")) imonth = 4;
		else if(month.equals("jun")) imonth = 5;
		else if(month.equals("jul")) imonth = 6;
		else if(month.equals("aug")) imonth = 7;
		else if(month.equals("sep")) imonth = 8;
		else if(month.equals("oct")) imonth = 9;
		else if(month.equals("nov")) imonth = 10;
		else if(month.equals("dec")) imonth = 11;
		else imonth = -1;
		return imonth;
	}
	
	/*Insertion*/
	public void addAppointment(String month, int day, String description, int duration)
	{
		day--;
		int imonth = getimonth(month);
		if (imonth == -1) {
			System.out.println("Error");
			return;
		}
		Appointment node = new Appointment();
		node.setDescription(description);
		node.setDuration(duration);
		node.month = imonth;
		node.day = day;
		
		if (months[imonth] == null) {
			months[imonth] = node;
		}
		else {
			Appointment temp = months[imonth];
			if (temp.day == day){
				while (temp.back != null) temp = temp.back;
				temp.back = node;
				return;
			}
			if (temp.day > day){
				node.down = temp;
				months[imonth] = node;
			}
			else{
				while (temp.down != null){
					if (temp.day == day){
						while (temp.back != null) temp = temp.back;
						temp.back = node;
						return;
					}
					if (temp.down.day > day) {
						node.down = temp.down;
						temp.down = node;
						break;
					}
					temp = temp.down;
				}		
				if (temp.down == null) temp.down = node;
			}
		}
		if (days[day] == null)	{
			days[day] = node;
		}
		else {
			Appointment temp = days[day];
			if (temp.month > imonth) {
				node.right = temp;
				days[day] = node;
			}
			else{
				while (temp.right != null) {
					if(temp.right.month > imonth) {
						node.right = temp.right;
						temp.right = node;
					}
					temp = temp.right;
				}
				if (temp.right == null) temp.right = node;
			}
		}
				
		
		
	}
	
	/*Deletion methods*/
	public Appointment deleteAppointment(String month, int day)
	{
		/*Delete the first appointment at the given month and day combination and return the deleted appointment.
		If no such appointment exists, return null.*/
		day--;
		Appointment node = find(month,day);
		if (node == null) return null;
		int imonth = getimonth(month);
		if (months[imonth] == node) {
			if (node.back != null) {
				months[imonth] = node.back;
				node.back.down = node.down;
			}
			else months[imonth] = node.down;
		}
		
		else {
			Appointment prevmonth = months[imonth];
			while (prevmonth.down != node) prevmonth = prevmonth.down;
			if (node.back != null) {
				prevmonth.down = node.back;
				node.back.down = node.down;
			}
			else {
				prevmonth.down = node.down;
			}
		}
		if (days[day] == node) {
			if (node.back != null) {
				node.back.right = node.right;
				days[day] = node.back;
			}
			else days[day] = node.right;
		}
		else {
			Appointment prevday = days[day];
			while (prevday.right != node) prevday = prevday.right;
			if (node.back != null) {
				prevday.right = node.back;
				node.back.right = node.right;
			}
			else {
				prevday.right = node.right;
			}
		}
		
		return node;
	}
	
	private Appointment find(String month, int day){
		if (days[day] == null)  return null;
		int imonth = getimonth(month);
		if (months[imonth] == null)  return null;
		Appointment node = months[imonth];
		while (node.day != day) {
			node = node.down;
			if (node == null) return null;
			if (node.day > day) return null;
		}
		return node;
		
	}
	
	public Appointment deleteAppointment(String month, int day, String description)
	{
		/*Delete the first appointment at the given month and day combination  with the description and return the deleted appointment.
		If no such appointment exists, return null.*/
		day--;
		Appointment node = find(month,day);
		if (node == null) return null;
		int imonth = getimonth(month);
		if (months[imonth] == node) {
			if (node.back != null) {
				if (node.getDescription().equals(description)) {
					months[imonth] = node.back;
					node.back.down = node.down;
				}
				else {
					Appointment temp = node.back,prev = node;
					while (temp != null) {
						if (temp.getDescription().equals(description)) {
							prev.back = temp.back;
							break;
						}
						prev = temp;
						temp = temp.back;
					}
					if (temp == null) return null;
					return node;
				}
			}
			else {
				if (node.getDescription().equals(description)) months[imonth] = node.down;
				else return null;
			}
		}
		
		else {
			Appointment prevmonth = months[imonth];
			while (prevmonth.down != node) prevmonth = prevmonth.down;
			if (node.back != null) {
				if (node.getDescription().equals(description)) {
					prevmonth.down = node.back;
					node.back.down = node.down;
				}
				else {
					Appointment temp = node.back,prev = node;
					while (temp != null) {
						if (temp.getDescription().equals(description)) {
							prev.back = temp.back;
							return node;
						}
						prev = temp;
						temp = temp.back;
					}
					if (temp == null) return null;
				}
			}
			else {
				if(node.getDescription().equals(description)){
					prevmonth.down = node.down;
				}
				else return null;
			}
		}
		if (days[day] == node) {
			if (node.back != null) {
				node.back.right = node.right;
				days[day] = node.back;
			}
			else days[day] = node.right;
		}
		else {
			Appointment prevday = days[day];
			while (prevday.right != node) prevday = prevday.right;
			if (node.back != null) {
				prevday.right = node.back;
				node.back.right = node.right;
			}
			else {
				prevday.right = node.right;
			}
		}
		
		return node;
	}
	
	/*Clearing Methods*/
	public void clearMyMonth(String month)
	{
		/*All appointements for the given month should be deleted.
		If the month has no appointments, simply do nothing.*/
		int imonth = getimonth(month);
		if (months[imonth] == null) return;
		months[imonth] = null;
		Appointment node,prev = null;
		for (int i=0;i<30;i++){
			node = days[i];
			while (node != null) {
				if(node.month == imonth){
					if (prev == null) days[i] = node.right;
					else prev.right = node.right;
				}
				if (node.month > imonth) break;
				prev = node;
				node = node.right;
			}
			prev = null;
		}
	}
	
	public void clearMyDays(int day)
	{
		/*All appointements for the given day should be deleted.
		If the day has no appointments, simply do nothing.*/
		day--;
		if (days[day] == null) return;
		days[day] = null;
		Appointment node,prev=null;
		for (int i = 0;i<12;i++) {
			node = months[i];
			while(node != null) {
				if(node.day == day){
					if (prev == null) months[i] = node.down;
					else prev.down = node.down;
				}
				if(node.day > day) break;
				prev = node;
				node = node.down;
			}
			prev = null;
		}
	}
	
	public void clearMyYear()
	{
		/*Delete all appointments from the calendar.*/
		for (int i = 0;i <30;i++) {
			days[i] = null;
		}
		for (int i = 0;i <12;i++) {
			months[i] = null;
		}
	}
	
	
	/*Query methods*/
	public Appointment getAppointment(String month, int day)
	{
		/*Return the head appointment of the month and day combination.  If no such appointment exists, return null*/
		day--;
		return find(month,day);
	}
	
	public Appointment getMonthAppointment(String month)
	{
		/*Return the head appointment for the month passed as a parameter.
		If no such appointment exists, return null*/
		return months[getimonth(month)];
	}
	
	public Appointment getDayAppointment(int day)
	{
		/*Return the head appointment for the day passed as a parameter.
		If no such appointment exists, return null*/
		day--;
		return days[day];
	}
	
	
	
	
	
}