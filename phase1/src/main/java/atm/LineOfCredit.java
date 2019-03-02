package atm;

public class LineOfCredit extends Account {

    public LineOfCredit(User owner) {
       super(owner);
    }

    @Override
    public String toString() {
        return "Line of Credit Account";
    }

}
