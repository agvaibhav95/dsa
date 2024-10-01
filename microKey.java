import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadPoolDesign {
//execute() --> accepting only runnable tasks no callable tasks
//stop() --> calls the interrupt
//shutdown() --> no new task is added in the queue
//getInstance -- if nothing is specified then take cores size
//terminate --> terminate all the tasks by clearing the queue of tasks and shutdown is set to false.
//stop() -> it interrupts the thread.
	
	//resource
	//non blocking queue should be taken as i have very large no of task it will giv Out of Memory Error.
	//list of threads it will be given by user if not specified i will take as many cores of cpu
	//flag if shutdown calls then set false and throw error if new task is added after this call.
	//ArrayBlockingQueue<Runnable> tasks;
	BlockingQueue<Runnable> tasks;
	AtomicBoolean shutdown;
	List<ThreadsThreadPool> threads;
	ThreadPoolDesign(int threads){
		this.tasks=new ArrayBlockingQueue(100);
		this.shutdown=new AtomicBoolean(true);
		this.threads=new ArrayList<ThreadsThreadPool>();
		for(int i=0;i<threads;i++) {
			ThreadsThreadPool t=new ThreadsThreadPool(tasks, shutdown);
			t.start();
			this.threads.add(t);
		}
		
	}
	public ThreadPoolDesign getInstance() {
		 return new ThreadPoolDesign(Runtime.getRuntime().availableProcessors());
	}
	
	public void execute(Runnable task) {
		if(shutdown.get() == true) {
			tasks.add(task);
		
		}
		else {
			throw new IllegalStateException("Cannot add task after thread pool shutdown");
		}
	}
	
	public void stop() { // method name same as awaitterminate() method
		if(shutdown.get()) {
			 throw new RuntimeException("stop cannot be called before shutdown");
		}
		for(ThreadsThreadPool thread:threads) {
			if(thread.isAlive()) {
			thread.interrupt();}
		}
	}
	public void terminate() {
		tasks.clear();
		shutdown();
	}
	
	public void shutdown() {
		shutdown.compareAndSet(true,false);
	}

public void awaitterminate(long timeout) {
	if(shutdown.get()) {
		 throw new RuntimeException("stop cannot be called before shutdown");
	}
long currtime=System.currentTimeMillis();
while(System.currentTimeMillis()-currtime<=timeout) {
	for(ThreadsThreadPool thread:threads) {
		if(thread.isAlive()) {
		thread.interrupt();}
	}
}
}
}


class ThreadsThreadPool extends Thread{
	BlockingQueue<Runnable> tasks;
	AtomicBoolean shutdown;
	public ThreadsThreadPool(BlockingQueue<Runnable> tasks,AtomicBoolean shutdown) {
	this.shutdown=shutdown;
	this.tasks=tasks;
	}
	@Override
	public void run() {
		while(shutdown.get()) {
		if(!this.isInterrupted()) {
			{if(!tasks.isEmpty()) {
			tasks.poll().run();
			}
			
			}}}}
		
	
}
