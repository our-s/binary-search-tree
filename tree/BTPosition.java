package tree;

public interface BTPosition<E> extends Position<E> {
    void setElement(E e);
    BTPosition<E> getLeft();
    BTPosition<E> getRight();
    BTPosition<E> getParent();
    void setLeft(BTPosition<E> l);
    void setRight(BTPosition<E> r);
    void setParent(BTPosition<E> p);
}
