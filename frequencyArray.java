import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class PrinteveneOddd {
volatile int i=0;
public static void main(String[] args) {
	PrintNumbers pnum=new PrintNumbers();
	MyThread150 t=new MyThread150(pnum);
	MyThread151 t1=new MyThread151(pnum);
	t.start();
	t1.start();
	
}
}

 class MyThread150 extends Thread{
	 PrintNumbers pnum;
	 MyThread150(PrintNumbers pnum){
		 this.pnum=pnum;
	 }
@Override
public void run() {
	try {
		pnum.printeven();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}

 class MyThread151 extends Thread{
	 PrintNumbers pnum;
	 MyThread151(PrintNumbers pnum){
		 this.pnum=pnum;
	 }
@Override
public void run() {
try {
	pnum.printodd();
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
}

 class PrintNumbers{
	volatile int counter=0;
ReentrantLock lock=new ReentrantLock();
Condition odd=lock.newCondition();
Condition even=lock.newCondition();
	 public void printeven() throws InterruptedException {
		
		
			 	while(counter<100) {
			 		 synchronized (this) {
			 		 //lock.lock();
			 		if(counter %2 !=0) {
			 			//even.await();
			 			this.wait();
			 		}
			System.out.println(counter); 
			counter++;
			//odd.signal();
			//lock.unlock();
			this.notify();
			 	}
		}
	 }


	
	 
	 public void printodd() throws InterruptedException {
		// synchronized (this) {
			 while(counter<100) {
				 synchronized (this) {
			 
			//	 lock.lock();
				 if(counter%2 == 0) {
					 //odd.await();
					 this.wait();
				 }
					System.out.println(counter); 	
					counter++;
					//this.notify();
				/*
				 * try { this.wait(); } catch (InterruptedException e) { // TODO Auto-generated
				 * catch block e.printStackTrace(); }
				 */
					
					 	//}
		//even.signal();
			// lock.unlock();
				this.notify();
				 	}}
	// }
	 }

}
