public class IndexIllegalException extends MiloException {
    public IndexIllegalException(int index) {
        super(String.format("Sorry! The index: %d you give me is illegal! Please try again!", index));
    }
}
