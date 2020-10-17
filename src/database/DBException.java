package database;

public class DBException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DBException() {
		super();
	}
	
	public DBException(String message) {
		super(message);
	}
	
}
