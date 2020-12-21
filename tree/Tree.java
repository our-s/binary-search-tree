package tree;

public interface Tree<E> {
    int size();
    boolean isEmpty();
//  public Iterator<E> iterator();
//  public Iterable<tree.Position <E> > positions();
E replace(Position<E> p, E e) throws InvalidPositionException;
    Position<E> root() throws EmptyTreeException;
    Position<E> parent(Position<E> p)
            throws InvalidPositionException, BoundaryViolationException;
    Iterable<Position<E> > children(Position<E> p)
            throws InvalidPositionException;
    boolean isInternal(Position<E> p) throws InvalidPositionException;
    boolean isExternal(Position<E> p) throws InvalidPositionException;
    boolean isRoot(Position<E> p) throws InvalidPositionException;
}