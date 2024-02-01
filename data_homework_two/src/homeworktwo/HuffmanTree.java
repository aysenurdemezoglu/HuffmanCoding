package homeworktwo;

public class HuffmanTree {
    private Node root;

    public HuffmanTree(MyList list){

        Node currentRoot;
        while (true) {
            Node leftNode = list.popHead(); // Gets the first node with the lowest frequency
            Node rightNode = list.popHead(); // Gets the second node with the lowest frequency
            currentRoot = new Node(leftNode.symbol + rightNode.symbol, leftNode.frequency + rightNode.frequency);
            currentRoot.left = leftNode;
            currentRoot.right = rightNode;
            if (list.isEmpty())
                break;
            list.sortedAdd(currentRoot);
        }
        // The last node created is designated as the root of the tree
        root = currentRoot;
    }

    public String decode(String encodedSymbol){
        String decodedSymbol = "";
        Node iterator = root;
        for (char c : encodedSymbol.toCharArray()){ // symbols --> characters
            if (c == '0'){
                iterator = iterator.left;
            }
            else {
                iterator = iterator.right;
            }
            if (iterator.symbol.length() == 1){
                decodedSymbol += iterator.symbol; // add to solved symbol
                iterator = root; // Place the iterator back at the root of the tree
            }
        }

        return decodedSymbol;
    }

    public String encode(String symbol){
        String encodedSymbol = "";

        for (char c : symbol.toCharArray()){
            String character = String.valueOf(c);

            Node iterator = root;
            while(iterator != null){
                if (iterator.symbol.length() == 1)
                    break;
                if (iterator.left != null && iterator.left.symbol.contains(character)){
                    encodedSymbol += "0";
                    iterator = iterator.left;
                }
                else{
                    encodedSymbol += "1";
                    iterator = iterator.right;
                }

            }
        }

        return encodedSymbol;
    }

}
