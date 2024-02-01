package homeworktwo;

public class MyList {
    public Node head;

    public boolean isEmpty(){

        return head == null;
    }

    public void sortedAdd(Node node, Node iterator, Node prev){

        if (iterator == null){
            prev.next = node;
            return;
        }

        // if element is smaller than val, addFront
        if (node.frequency < iterator.frequency){
            node.next = iterator;
            prev.next = node;
            return;
        }

        sortedAdd(node, iterator.next, prev.next);
    }
    public void sortedAdd(Node node){

        if (head == null){
            head = node;
            return;
        }

        if (head.frequency > node.frequency){
            node.next = head;
            head = node;
            return;
        }

        sortedAdd(node, head.next, head);
    }

    public int count(){ // for pophead
        int count = 0;

        if (head == null){
            return count;
        }

        Node iterator = head;
        while (iterator != null){
            count++;
            iterator = iterator.next;
        }

        return count;
    }

    public Node popHead(){
        if (isEmpty()){
            return null;
        }

        Node node = head;
        head = this.head.next;
        return node;
    }

}
