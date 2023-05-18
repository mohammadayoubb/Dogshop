package project;

class SNode<T> {
    T item;
    SNode<T> next;

    public SNode(T item) {
        this.item = item;
        this.next = null;
    }
}

public class Stack<T> {

    SNode<T> top;
    int size;

    Stack() {
        this.top = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void push(T item) {
        SNode<T> temp = new SNode<T>(item);

        temp.item = item;
        temp.next = top;
        top = temp;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public T peek() {
        if (!isEmpty()) {
            return top.item;
        } else {
            System.out.println("Stack is empty");
            return null;
        }
    }

    public T pop() {
        if (top == null) {
            return null;
        }
        T toReturn = top.item;
        top = (top).next;

        return toReturn;
    }
}
