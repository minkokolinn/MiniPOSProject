package util;

public class PosException extends RuntimeException{	//to stop the problem place, create custom exception
	
	private static final long serialVersionUID=1L;
	
	public PosException(String errorLog) {
		super(errorLog);
	}
}
