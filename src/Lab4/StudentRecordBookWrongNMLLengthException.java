package Lab4;

public class StudentRecordBookWrongNMLLengthException extends RuntimeException {
    public StudentRecordBookWrongNMLLengthException(String errorMessage) {
        super(errorMessage);
    }

    public StudentRecordBookWrongNMLLengthException() {
        super();
    }
}