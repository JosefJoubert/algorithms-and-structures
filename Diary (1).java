public class Diary
{
	static void print(Appointment app) {
		String out = "";
		out += app.getDescription();
		out += " [right: " + (app.right != null ? app.right.getDescription() + "]": "null]");
		out += " [down: " + (app.down != null ? app.down.getDescription() + "]": "null]");
		out += " [back: " + (app.back != null ? app.back.getDescription() + "]": "null]");
		System.out.println(out);
	}

	public static void main(String[] args)
	{
		Calendar c = new Calendar();

		System.out.println("----------------- INSERT MARCH ------------------");
		c.addAppointment("Mar",18,"March-18",5);
		Appointment a = c.getAppointment("Mar",18);

		c.addAppointment("Mar", 17, "March-17", 5);
		c.addAppointment("Mar", 17, "March-17(2)", 5);
		Appointment b = c.getAppointment("Mar",17);

		c.addAppointment("Mar", 20, "March-20", 5);
		Appointment cc = c.getAppointment("Mar",20);

		c.addAppointment("Mar", 19, "March-19", 5);
		c.addAppointment("Mar", 19, "March-19(2)", 5);
		c.addAppointment("Mar", 19, "March-19(3)", 5);
		Appointment d = c.getAppointment("Mar",19);
		print(b);
		print(a);

		print(d);
		print(cc);

		System.out.println();
		System.out.println("----------------- INSERT FEBRUARY ------------------");
		c.addAppointment("Feb", 19, "February-19", 5);
		Appointment e = c.getAppointment("Feb",19);


		c.addAppointment("Feb", 17, "February-17", 5);
		c.addAppointment("Feb", 17, "February-17(2)", 5);
		Appointment f = c.getAppointment("Feb",17);


		c.addAppointment("Feb", 18, "February-18", 5);
		c.addAppointment("Feb", 18, "February-18(2)", 5);
		c.addAppointment("Feb", 18, "February-18(3)", 5);
		Appointment g = c.getAppointment("Feb",18);

		print(f);
		print(g);
		print(e);


		System.out.println();
		System.out.println("----------------- INSERT APRIL ------------------");
		c.addAppointment("Apr", 19, "April-19", 5);
		Appointment h = c.getAppointment("Apr",19);

		c.addAppointment("Apr", 17, "April-17", 5);
		Appointment i = c.getAppointment("Apr",17);

		c.addAppointment("Apr", 18, "April-18", 5);
		c.addAppointment("Apr", 18, "April-18(2)", 5);
		c.addAppointment("Apr", 18, "April-18(3)", 5);
		Appointment j = c.getAppointment("Apr",18);

		c.addAppointment("Apr", 20, "April-20", 5);
		Appointment k = c.getAppointment("Apr",20);

		print(i);
		print(j);
		print(h);
		print(k);

		System.out.println();
		System.out.println("----------------- INSERT SEPTEMBER ------------------");
		c.addAppointment("Sep", 17, "September-17", 5);
		Appointment l = c.getAppointment("Sep",17);

		c.addAppointment("Sep", 18, "September-18", 5);
		Appointment m = c.getAppointment("Sep",18);

		c.addAppointment("Sep", 21, "September-21", 5);
		Appointment n = c.getAppointment("Sep",21);

		print(l);
		print(m);
		print(n);

		System.out.println();
		System.out.println("----------------- INSERT MISC ------------------");
		c.addAppointment("Feb", 21, "February-21", 5);
		Appointment o = c.getAppointment("Feb",21);

		c.addAppointment("Dec", 21, "December-21", 5);
		Appointment p = c.getAppointment("Dec",21);

		c.addAppointment("Jan", 21, "January-21", 5);
		Appointment q = c.getAppointment("Jan",21);

		c.addAppointment("Jan", 30, "January-30", 5);
		Appointment r = c.getAppointment("Jan",30);

		c.addAppointment("Jan", 1, "January-1", 5);
		c.addAppointment("Jan", 1, "January-1(2)", 5);
		Appointment s = c.getAppointment("Jan",1);

		print(o);
		print(p);
		print(q);
		print(s);
		print(r);

		System.out.println();
		System.out.println("----------------- FINAL TABLE ------------------");
		print(s);
		print(q);
		print(r);

		print(f);
		print(g);
		print(e);
		print(o);

		print(b);
		print(a);
		print(d);
		print(cc);

		print(i);
		print(j);
		print(h);
		print(k);

		print(l);
		print(m);
		print(n);

		print(p);

		System.out.println();
		System.out.println("----------------- DELETE MAR 17 ------------------");
		c.deleteAppointment("Mar", 17);
		f = c.getAppointment("Feb",17);
		b = c.getAppointment("Mar",17);
		a = c.getAppointment("Mar",18);
		i = c.getAppointment("Apr",17);

		print(f); print(b); print(a); print(i);

		System.out.println();
		System.out.println("----------------- DELETE FEB 18(2) ------------------");
		c.deleteAppointment("Feb", 18, "February-18(2)");
		f = c.getAppointment("Feb",17);
		g = c.getAppointment("Feb",18);
		e = c.getAppointment("Feb",19);
		a = c.getAppointment("Mar",18);

		print(f); print(g); print(e); print(a);


		System.out.println();
		System.out.println("----------------- DELETE FEB 18 ------------------");
		c.deleteAppointment("Feb", 18);
		f = c.getAppointment("Feb",17);
		g = c.getAppointment("Feb",18);
		e = c.getAppointment("Feb",19);
		a = c.getAppointment("Mar",18);

		print(f); print(g); print(e); print(a);

		System.out.println();
		System.out.println("----------------- DELETE FEB 17 ------------------");
		c.deleteAppointment("Feb", 17);
		f = c.getAppointment("Feb",17);
		g = c.getAppointment("Feb",18);
		e = c.getAppointment("Feb",19);
		b = c.getAppointment("Mar",17);

		print(f); print(g); print(e); print(b);

		System.out.println();
		System.out.println("----------------- DELETE FEB 17(2) ------------------");
		c.deleteAppointment("Feb", 17, "February-17(2)");
		g = c.getAppointment("Feb",18);
		e = c.getAppointment("Feb",19);
		b = c.getAppointment("Mar",17);
		print(g); print(e); print(b);
		//

		System.out.println();
		System.out.println("----------------- DELETE MAR 18 ------------------");
		c.deleteAppointment("Mar", 18);
		g = c.getAppointment("Feb",18);
		j = c.getAppointment("Apr",18);
		b = c.getAppointment("Mar",17);
		d = c.getAppointment("Mar",19);

		print(g); print(j); print(b); print(d);

		System.out.println();
		System.out.println("----------------- DELETE APR 17 ------------------");
		c.addAppointment("Jan", 17, "January-17", 5);
		c.deleteAppointment("Apr", 17);
		Appointment z = c.getAppointment("Jan", 17);
		b = c.getAppointment("Mar",17);
		l = c.getAppointment("Sep",17);
		j = c.getAppointment("Apr",18);

		print(z); print(b); print(l); print(j);

		System.out.println();
		System.out.println("----------------- DELETE APR 19 ------------------");
		c.deleteAppointment("Apr", 19);
		e = c.getAppointment("Feb",19);
		d = c.getAppointment("Mar",19);
		j = c.getAppointment("Apr",18);
		k = c.getAppointment("Apr",20);

		print(e); print(d); print(j); print(k);

		System.out.println();
		System.out.println("----------------- TABLE AFTER DELETES ------------------");

		//a = c.getAppointment("Mar",18);
		b = c.getAppointment("Mar",17);
		cc = c.getAppointment("Mar",20);
		d = c.getAppointment("Mar",19);
		e = c.getAppointment("Feb",19);
		//f = c.getAppointment("Feb",17);
		g = c.getAppointment("Feb",18);
		//h = c.getAppointment("Apr",19);
		//i = c.getAppointment("Apr",17);

		j = c.getAppointment("Apr",18);
		k = c.getAppointment("Apr",20);
		l = c.getAppointment("Sep",17);
		m = c.getAppointment("Sep",18);
		n = c.getAppointment("Sep",21);
		o = c.getAppointment("Feb",21);
		p = c.getAppointment("Dec",21);
		q = c.getAppointment("Jan",21);
		r = c.getAppointment("Jan",30);
		s = c.getAppointment("Jan",1);
		z = c.getAppointment("Jan", 17);

		print(s);
		print(z);
		print(q);
		print(r);

		print(g);
		print(e);
		print(o);

		print(b);
		print(d);
		print(cc);

		print(j);
		print(k);

		print(l);
		print(m);
		print(n);

		print(p);

		System.out.println();
		System.out.println("----------------- clearMonth = MARCH ------------------");
		c.clearMyMonth("Mar");
		s = c.getAppointment("Jan",1);
		z = c.getAppointment("Jan", 17);
		q = c.getAppointment("Jan",21);
		r = c.getAppointment("Jan",30);

		g = c.getAppointment("Feb",18);
		e = c.getAppointment("Feb",19);
		o = c.getAppointment("Feb",21);

		j = c.getAppointment("Apr",18);
		k = c.getAppointment("Apr",20);

		l = c.getAppointment("Sep",17);
		m = c.getAppointment("Sep",18);
		n = c.getAppointment("Sep",21);

		p = c.getAppointment("Dec",21);

		print(s); print(z); print(q); print(r); print(g); print(e); print(o);
		print(j); print(k); print(l); print(m); print(n); print(p);

		System.out.println();
		System.out.println("----------------- clearDay = 21 ------------------");
		c.clearMyDays(21);
		s = c.getAppointment("Jan",1);
		z = c.getAppointment("Jan", 17);
		r = c.getAppointment("Jan",30);

		g = c.getAppointment("Feb",18);
		e = c.getAppointment("Feb",19);

		j = c.getAppointment("Apr",18);
		k = c.getAppointment("Apr",20);

		l = c.getAppointment("Sep",17);
		m = c.getAppointment("Sep",18);
		print(s); print(z); print(r); print(g); print(e); print(j); print(k);
		print(l); print(m);

		System.out.println();
		System.out.println("----------------- getAppointment() ------------------");
		print(c.getAppointment("Apr", 18));
		print(c.getAppointment("Feb", 19));
		print(c.getAppointment("Jan", 1));
		print(c.getAppointment("Sep", 17));

		System.out.println();
		System.out.println("----------------- getMonthAppointment() ------------------");
		print(c.getMonthAppointment("Jan"));
		print(c.getMonthAppointment("Apr"));
		print(c.getMonthAppointment("Sep"));

		System.out.println();
		System.out.println("----------------- getDayAppointment() ------------------");
		print(c.getDayAppointment(18));
		print(c.getDayAppointment(17));
		print(c.getDayAppointment(20));

		System.out.println();
		System.out.println("----------------- clearMyYear() ------------------");
		c.clearMyYear();
		s = c.getAppointment("Jan",1);
		z = c.getAppointment("Jan", 17);
		r = c.getAppointment("Jan",30);

		g = c.getAppointment("Feb",18);
		e = c.getAppointment("Feb",19);

		j = c.getAppointment("Apr",18);
		k = c.getAppointment("Apr",20);

		l = c.getAppointment("Sep",17);
		m = c.getAppointment("Sep",18);
/*
		----------------- INSERT MARCH ------------------
March-17 [right: null] [down: March-18] [back: March-17(2)]
March-18 [right: null] [down: March-19] [back: null]
March-19 [right: null] [down: March-20] [back: March-19(2)]
March-20 [right: null] [down: null] [back: null]

----------------- INSERT FEBRUARY ------------------
February-17 [right: March-17] [down: February-18] [back: February-17(2)]
February-18 [right: March-18] [down: February-19] [back: February-18(2)]
February-19 [right: March-19] [down: null] [back: null]

----------------- INSERT APRIL ------------------
April-17 [right: null] [down: April-18] [back: null]
April-18 [right: null] [down: April-19] [back: April-18(2)]
April-19 [right: null] [down: April-20] [back: null]
April-20 [right: null] [down: null] [back: null]

----------------- INSERT SEPTEMBER ------------------
September-17 [right: null] [down: September-18] [back: null]
September-18 [right: null] [down: September-21] [back: null]
September-21 [right: null] [down: null] [back: null]

----------------- INSERT MISC ------------------
February-21 [right: September-21] [down: null] [back: null]
December-21 [right: null] [down: null] [back: null]
January-21 [right: February-21] [down: January-30] [back: null]
January-1 [right: null] [down: January-21] [back: January-1(2)]
January-30 [right: null] [down: null] [back: null]

----------------- FINAL TABLE ------------------
January-1 [right: null] [down: January-21] [back: January-1(2)]
January-21 [right: February-21] [down: January-30] [back: null]
January-30 [right: null] [down: null] [back: null]
February-17 [right: March-17] [down: February-18] [back: February-17(2)]
February-18 [right: March-18] [down: February-19] [back: February-18(2)]
February-19 [right: March-19] [down: February-21] [back: null]
February-21 [right: September-21] [down: null] [back: null]
March-17 [right: April-17] [down: March-18] [back: March-17(2)]
March-18 [right: April-18] [down: March-19] [back: null]
March-19 [right: April-19] [down: March-20] [back: March-19(2)]
March-20 [right: April-20] [down: null] [back: null]
April-17 [right: September-17] [down: April-18] [back: null]
April-18 [right: September-18] [down: April-19] [back: April-18(2)]
April-19 [right: null] [down: April-20] [back: null]
April-20 [right: null] [down: null] [back: null]
September-17 [right: null] [down: September-18] [back: null]
September-18 [right: null] [down: September-21] [back: null]
September-21 [right: December-21] [down: null] [back: null]
December-21 [right: null] [down: null] [back: null]

----------------- DELETE MAR 17 ------------------
February-17 [right: March-17(2)] [down: February-18] [back: February-17(2)]
March-17(2) [right: April-17] [down: March-18] [back: null]
March-18 [right: April-18] [down: March-19] [back: null]
April-17 [right: September-17] [down: April-18] [back: null]

----------------- DELETE FEB 18(2) ------------------
February-17 [right: March-17(2)] [down: February-18] [back: February-17(2)]
February-18 [right: March-18] [down: February-19] [back: February-18(3)]
February-19 [right: March-19] [down: February-21] [back: null]
March-18 [right: April-18] [down: March-19] [back: null]

----------------- DELETE FEB 18 ------------------
February-17 [right: March-17(2)] [down: February-18(3)] [back: February-17(2)]
February-18(3) [right: March-18] [down: February-19] [back: null]
February-19 [right: March-19] [down: February-21] [back: null]
March-18 [right: April-18] [down: March-19] [back: null]

----------------- DELETE FEB 17 ------------------
February-17(2) [right: March-17(2)] [down: February-18(3)] [back: null]
February-18(3) [right: March-18] [down: February-19] [back: null]
February-19 [right: March-19] [down: February-21] [back: null]
March-17(2) [right: April-17] [down: March-18] [back: null]

----------------- DELETE FEB 17(2) ------------------
February-18(3) [right: March-18] [down: February-19] [back: null]
February-19 [right: March-19] [down: February-21] [back: null]
March-17(2) [right: April-17] [down: March-18] [back: null]

----------------- DELETE MAR 18 ------------------
February-18(3) [right: April-18] [down: February-19] [back: null]
April-18 [right: September-18] [down: April-19] [back: April-18(2)]
March-17(2) [right: April-17] [down: March-19] [back: null]
March-19 [right: April-19] [down: March-20] [back: March-19(2)]

----------------- DELETE APR 17 ------------------
January-17 [right: March-17(2)] [down: January-21] [back: null]
March-17(2) [right: September-17] [down: March-19] [back: null]
September-17 [right: null] [down: September-18] [back: null]
April-18 [right: September-18] [down: April-19] [back: April-18(2)]

----------------- DELETE APR 19 ------------------
February-19 [right: March-19] [down: February-21] [back: null]
March-19 [right: null] [down: March-20] [back: March-19(2)]
April-18 [right: September-18] [down: April-20] [back: April-18(2)]
April-20 [right: null] [down: null] [back: null]

----------------- TABLE AFTER DELETES ------------------
January-1 [right: null] [down: January-17] [back: January-1(2)]
January-17 [right: March-17(2)] [down: January-21] [back: null]
January-21 [right: February-21] [down: January-30] [back: null]
January-30 [right: null] [down: null] [back: null]
February-18(3) [right: April-18] [down: February-19] [back: null]
February-19 [right: March-19] [down: February-21] [back: null]
February-21 [right: September-21] [down: null] [back: null]
March-17(2) [right: September-17] [down: March-19] [back: null]
March-19 [right: null] [down: March-20] [back: March-19(2)]
March-20 [right: April-20] [down: null] [back: null]
April-18 [right: September-18] [down: April-20] [back: April-18(2)]
April-20 [right: null] [down: null] [back: null]
September-17 [right: null] [down: September-18] [back: null]
September-18 [right: null] [down: September-21] [back: null]
September-21 [right: December-21] [down: null] [back: null]
December-21 [right: null] [down: null] [back: null]

----------------- clearMonth = MARCH ------------------
January-1 [right: null] [down: January-17] [back: January-1(2)]
January-17 [right: September-17] [down: January-21] [back: null]
January-21 [right: February-21] [down: January-30] [back: null]
January-30 [right: null] [down: null] [back: null]
February-18(3) [right: April-18] [down: February-19] [back: null]
February-19 [right: null] [down: February-21] [back: null]
February-21 [right: September-21] [down: null] [back: null]
April-18 [right: September-18] [down: April-20] [back: April-18(2)]
April-20 [right: null] [down: null] [back: null]
September-17 [right: null] [down: September-18] [back: null]
September-18 [right: null] [down: September-21] [back: null]
September-21 [right: December-21] [down: null] [back: null]
December-21 [right: null] [down: null] [back: null]

----------------- clearDay = 21 ------------------
January-1 [right: null] [down: January-17] [back: January-1(2)]
January-17 [right: September-17] [down: January-30] [back: null]
January-30 [right: null] [down: null] [back: null]
February-18(3) [right: April-18] [down: February-19] [back: null]
February-19 [right: null] [down: null] [back: null]
April-18 [right: September-18] [down: April-20] [back: April-18(2)]
April-20 [right: null] [down: null] [back: null]
September-17 [right: null] [down: September-18] [back: null]
September-18 [right: null] [down: null] [back: null]

----------------- getAppointment() ------------------
April-18 [right: September-18] [down: April-20] [back: April-18(2)]
February-19 [right: null] [down: null] [back: null]
January-1 [right: null] [down: January-17] [back: January-1(2)]
September-17 [right: null] [down: September-18] [back: null]

----------------- getMonthAppointment() ------------------
January-1 [right: null] [down: January-17] [back: January-1(2)]
April-18 [right: September-18] [down: April-20] [back: April-18(2)]
September-17 [right: null] [down: September-18] [back: null]

----------------- getDayAppointment() ------------------
February-18(3) [right: April-18] [down: February-19] [back: null]
January-17 [right: September-17] [down: January-30] [back: null]
April-20 [right: null] [down: null] [back: null]

----------------- clearMyYear() ------------------
Exception in thread "main" java.lang.NullPointerException
	at Calendar.getAppointment(Calendar.java:368)
	at Diary.main(Diary.java:337)
make: *** [run] Error 1

*/
	}
}