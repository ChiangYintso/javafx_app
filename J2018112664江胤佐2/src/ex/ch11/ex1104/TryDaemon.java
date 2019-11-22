package ex.ch11.ex1104;

public class TryDaemon {
	public static void main(String[] args) {
		Thread t = new Thread(new DaemonThread());
		t.setDaemon(true);
		t.start();
	}
}

class DaemonThread implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("线程正在运行");
		}
	}
	
}