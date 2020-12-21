package tree;

public interface BinaryTree<E> extends Tree<E> {
    Position<E> left(Position<E> v)
            throws InvalidPositionException, BoundaryViolationException;
    Position<E> right(Position<E> v)
            throws InvalidPositionException, BoundaryViolationException;
    boolean hasLeft(Position<E> v) throws InvalidPositionException;
    boolean hasRight(Position<E> v) throws InvalidPositionException;
}
