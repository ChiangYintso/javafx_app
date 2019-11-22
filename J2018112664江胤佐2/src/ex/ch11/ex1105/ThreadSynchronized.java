package ex.ch11.ex1105;

class Common {
	private char ch;
	private boolean available = false;
	synchronized char get() {
		while (!available) {
			try {
				wait();
			} catch (InterruptedException e) {
				
			}
		}
		available = false;
		System.out.println("消费的数据是" + ch);
		notify();
		
		return ch;
	}
	synchronized void put(char newCh) {
		while (available) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		ch = newCh;
		available = true;
		System.out.println("生产的数据是" + ch);
		notify();
	}
}

class Producer extends Thread {
	private Common comm;
	public Producer(Common comm) {
		this.comm =comm;
	}
	public void run() {
		char c;
		for (c = 'a'; c <= 'e'; ++c) {
			comm.put(c);
			
		}
		
	}
}

class Consumer extends Thread {
	private Common commom;
	public Consumer(Common common) {
		this.commom = common;
	}
	public void run() {
		for (int i = 0; i < 5; ++i) {
			char c = commom.get();
			
		}
	}
}

public class ThreadSynchronized {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Common comm = new Common();
		Producer p = new Producer(comm);
		Consumer c = new Consumer(comm);
		p.start();
		c.start();
	}

}
