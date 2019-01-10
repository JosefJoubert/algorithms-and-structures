/*You must complete this class such that it can be used as nodes in a 3D sparse table.
Read the comments to determine what each aspect of the class is supposed to do.
You may add any additional features (methods, references) which may aid you in your
task BUT you are not allowed to remove or change the names or properties of any of the features you where given.

Importing Java's built in data structures will result in a mark of 0.*/

public class Appointment
{
	public Appointment()
	{
		/*You may implement this constructor to suit your needs, or you may add additional constructors.  This is the constructor which will be used for marking*/ 
		
	}
	
	
	public void setDescription(String desc)
	{
		/*Implement this method to set the description for this appointment*/
		description = desc;
	}
	
	
	public void setDuration(int dur)
	{
		/*Implement this method to set the duration of this appointment*/
		duration = dur;
	}
	
	public String getDescription()
	{
		/*This method returns the description of this appointment*/
		return description;
	}
	
	
	public int getDuration()
	{
		/*This method returns the duration of this appointment*/
		return duration;
	}
	
	public int day;
	public int month;
	public Appointment back;//The next appointment (back) of this appointment on the same date
	public Appointment right;//The next appointment (right) of this appointment in the same week.
	public Appointment down;//The next appointment (down) of this appointment in the same month.
	
	//Appointment particulars:
	private String description;//A description for this appointment.
	private int duration;//The number of hours that the appointment will last.
	
}
