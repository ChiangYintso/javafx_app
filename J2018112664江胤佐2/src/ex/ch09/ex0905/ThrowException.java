package ex.ch09.ex0905;

class AaaException extends Exception {

}

class BbbException extends Exception {

}

public class ThrowException {
	public static void main(String[] args) {
		int x = 1;
		try {
			if (x > 0) {
				throw new AaaException();
			} else {
				throw new BbbException();
			}
		} catch (AaaException e) {
			e.printStackTrace();
		} catch (BbbException e) {
			e.printStackTrace();
		}
	}
}
