package visitor;

public interface Visitable {
    /**
     * Accept visit from visitor v.
     */
    void accept(Visitor v);
}
