package ex.ch11.ex1102;

public class ThreadB implements Runnable {
	int count = 1;
	int num;
	public ThreadB(int newNum) {
		// TODO Auto-generated constructor stub
		num = newNum;
		System.out.println("创建线程");
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("线程" + num + ": 计数" + count);
			++count;
			if (count >= 3) break;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread a1 = new Thread(new ThreadB(1));
		Thread a2 = new Thread(new ThreadB(2));
		Thread a3 = new Thread(new ThreadB(3));
		
		a1.start();
		a2.start();
		a3.start();
		System.out.println("主方法main()结束");
	}
}
