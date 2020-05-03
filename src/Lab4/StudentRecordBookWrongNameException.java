package Lab4;

public class StudentRecordBookWrongNameException extends RuntimeException {
    public StudentRecordBookWrongNameException(String errorMessage) {
        super(errorMessage);
    }
    public StudentRecordBookWrongNameException() {
        super();
    }
}

