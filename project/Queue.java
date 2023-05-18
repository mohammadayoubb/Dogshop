package project;

class QNode<T> {
    T item;
    QNode<T> next;

    public QNode(T item) {
        this.item = item;
        this.next = null;
    }
}

class Queue<T> {
    QNode<T> front, rear;
    int size;

    public int getSize() {
        return size;
    }

    public Queue() {
        this.front = this.rear = null;
        size = 0;
    }

    T peek() {
        return this.front.item;
    }

    void enqueue(T item) {

        QNode<T> temp = new QNode<T>(item);

        if (this.rear == null) {
            this.front = this.rear = temp;
            return;
        }

        this.rear.next = temp;
        this.rear = temp;
        size++;
    }

    T dequeue() {
        if (this.front == null)
            return null;

        QNode<T> temp = this.front;
        this.front = this.front.next;
        if (this.front == null)
            this.rear = null;
        size--;
        return temp.item;
    }
}