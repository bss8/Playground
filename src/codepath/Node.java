package codepath;

public class Node {
    Node prev;
    Node next;
    int data;

    Node(int data) {
        this.prev = null;
        this.next = null;
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(50);
        sb.append("[ ");
        sb.append(this.data);
        sb.append(" ]");
        Node tmpNext = this.next;

        while (next != null) {
            sb.append(" -> ");
            sb.append("[ ");
            sb.append(next.data);
            sb.append(" ]");
            next = next.next;
        }
        sb.append(" -> |||"); // null terminal symbol
        next = tmpNext; // need to restore next ptr after mutating
        return sb.toString();
    }

    public boolean equals(Node n) {
        if (data != n.data)
            return false;

        if (next == null)
            return n.next == null;

        return next.equals(n.next);
    }
}
