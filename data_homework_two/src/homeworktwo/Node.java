package homeworktwo;

public class Node{
    public String symbol;
    public int frequency;
    public Node left;
    public Node right;
    public Node next;

    public Node(String symbol, int frequency){
        this.symbol = symbol;
        this.frequency = frequency;
        this.left = this.right = null;
    }
}