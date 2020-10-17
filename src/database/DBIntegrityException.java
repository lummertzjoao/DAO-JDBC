package database;

public class DBIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DBIntegrityException() {
	}

	public DBIntegrityException(String message) {
		super(message);
	}
	
}
