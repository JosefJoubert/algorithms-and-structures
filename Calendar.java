/*Complete this class to implement a fully functional sparse table.  Read the comments to determine what each aspect of the class is supposed to do.
You must add any additional features (methods, references) which may aid you in your
task BUT you are not allowed to remove or change the names or properties of any of the features you where given.

Importing Java's built in data structures will result in a mark of 0.*/

public class Calendar
{
	public Calendar()
	{
		/*You may implement this constructor to suit your needs, or you may add additional constructors.  This is the constructor which will be used for marking*/ 
	}
	
	/*Insertion*/
	public void addAppointment(String month, int day, String description, int duration)
	{
		/*Insert an appointment at the given month and day combination.  Intialize the appointment with the remainder of the parameters.
		
		Duplicate appointments are allowed.*/
	}
	
	/*Deletion methods*/
	public Appointment deleteAppointment(String month, int day)
	{
		/*Delete the first appointment at the given month and day combination and return the deleted appointment.
		If no such appointment exists, return null.*/
				
		return null;
	}
	
	public Appointment deleteAppointment(String month, int day, String description)
	{
		/*Delete the first appointment at the given month and day combination  with the description and return the deleted appointment.
		If no such appointment exists, return null.*/
				
		return null;
	}
	
	/*Clearing Methods*/
	public void clearMyMonth(String month)
	{
		/*All appointements for the given month should be deleted.
		If the month has no appointments, simply do nothing.*/
	}
	
	public void clearMyDays(int day)
	{
		/*All appointements for the given day should be deleted.
		If the day has no appointments, simply do nothing.*/
	}
	
	public void clearMyYear()
	{
		/*Delete all appointments from the calendar.*/
	}
	
	
	/*Query methods*/
	public Appointment getAppointment(String month, int day)
	{
		/*Return the head appointment of the month and day combination.  If no such appointment exists, return null*/
		
		return null;
	}
	
	public Appointment getMonthAppointment(String month)
	{
		/*Return the head appointment for the month passed as a parameter.
		If no such appointment exists, return null*/
		return null;
	}
	
	public Appointment getDayAppointment(int day)
	{
		/*Return the head appointment for the day passed as a parameter.
		If no such appointment exists, return null*/
		return null;
	}
	
	
	
	
	
}