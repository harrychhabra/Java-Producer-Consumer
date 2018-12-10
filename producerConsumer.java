
public class producerConsumer extends Thread{
	
	// Flag for number of produced items at any instance
	static private int mutex = 0;
	// Value flag: incremented by producer and decrement by consumer
	static private int value = 0;
	// buffer size
	static private int bufferSize = 5;
	
	// Producer function
	private void produce()
	{
		while(true)
		{
			// if max capacity is reached, wai
			if(mutex == bufferSize) continue;
			// using the following block as a single entity
			synchronized(this)
			{
				value++;
				System.out.println("Value produced: " + value);
				// increment 
				mutex++;
			}
		}
	}
	
	// consumer function
	private void consume()
	{
		while(true)
		{
//			if nothing is produced
			if(mutex <= 0) continue;
			synchronized(this) {
				value--;
				System.out.println("Value consumed: " + value);
				// decrement
				mutex--;
			}
		}
	}
	
	// acts a Runnable for thread t1
	private static class runnable1 implements Runnable{
		public void run() {
			// calling producer function
			producerConsumer p1 = new producerConsumer();
			p1.produce();
		}
	}
	
//	acts as Runnable for thread t2
	private static class runnable2 implements Runnable{
		public void run() {
			// calling consumer function
			producerConsumer p2 = new producerConsumer();
			p2.consume();
		}
	}
	
//	main function
	public static void main(String []args) {
		
		// Creating thread t1, for calling producer function
		Runnable r1 = new runnable1();
		Thread t1 = new Thread(r1);
		t1.start();
		
		// Creating thread t2 for calling consumer function
		Runnable r2 = new runnable2();
		Thread t2 = new Thread(r2);
		t2.start();
	}
}
