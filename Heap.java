/*
Complete your details...
Name and Surname: JH Joubert
Student/staff Number: 12048918
*/




public class Heap
{
	public static void main(String[] args)
	{
		/*Test your implementation in this file*/
		LeftistDHeap<Integer> temp = new LeftistDHeap<Integer>(3);
		temp.enqueue(4);
		temp.enqueue(5);
		temp.enqueue(6);
		temp.enqueue(7);
		temp.enqueue(22);
		temp.enqueue(11);
		temp.enqueue(8);
		temp.enqueue(50);
		temp.enqueue(19);
		System.out.println(temp.breadthFirstSearch());
		LeftistDHeap<Integer> temp2 = new LeftistDHeap<Integer>(5);
		temp2.enqueue(3);
		temp2.enqueue(77);
		temp2.enqueue(11);
		temp2.enqueue(10);
		temp2.enqueue(15);
		System.out.println(temp2.breadthFirstSearch());
		temp.combine(temp2);
		System.out.println(temp.breadthFirstSearch());
	}
}