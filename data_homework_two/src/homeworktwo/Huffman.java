package homeworktwo;

import java.io.*;
import java.util.*;

public class Huffman {

    public static void source2letter(String sourcePath, String letterPath){
        ArrayList<Character> uniqueLetters = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(sourcePath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(letterPath))) {

            int c;
            while ((c = br.read()) != -1) {
                char character = (char) c;
                if (Character.isLetter(character)) {
                    char lowerCaseChar = Character.toLowerCase(character);
                    if (!uniqueLetters.contains(lowerCaseChar)) {
                        uniqueLetters.add(lowerCaseChar);
                    }
                }
            }

            for (char letter : uniqueLetters) {
                bw.write(letter);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeFile(String filePath, String content){
        try (PrintWriter writer = new PrintWriter(filePath, "UTF-8")) {
            writer.println(content);
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String file2string(String filePath){
        String content = "";

        File file = new File(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = br.readLine()) != null) {
                for (int i=0; i < line.length(); i++){
                    char currentChar = line.charAt(i);
                    content += currentChar;
                }
            }
            br.close();
        }
        catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    public static Map<Character, Integer> letter2map(String filePath){
        Map<Character, Integer> charMap = new HashMap<Character, Integer>(); // store characters and occurrence counts
        File file = new File(filePath);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                for (int i=0; i < line.length(); i++){
                    char currentChar = line.charAt(i);
                    int value = charMap.getOrDefault(currentChar, -1);

                    if (value == -1) //If the character has not been seen before
                        charMap.put(currentChar, 0);
                    else{
                        charMap.put(currentChar, charMap.get(currentChar) + 1);
                    }
                }
            }

            br.close();
        }
        catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }

        return charMap;
    }

    public static MyList map2list(Map<Character, Integer> map){

        MyList list = new MyList();

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            String symbol = String.valueOf(entry.getKey());
            int frequency = ( (int) entry.getValue() );

            Node node = new Node(symbol, frequency);

            list.sortedAdd(node);
        }

        return list;
    }

    public static void main(String[] args) {

        String PATH_LETTER = "src/letter.txt";
        String PATH_SOURCE = "src/source.txt";
        String PATH_ENCODED = "src/encoded.txt";
        String PATH_DECODED = "src/decoded.txt";

        //-------------------------------------

        Map<Character, Integer> letterMap = letter2map(PATH_SOURCE);
        MyList letterList = map2list(letterMap);


        source2letter(PATH_SOURCE,PATH_LETTER);

        //-------------------------------------

        HuffmanTree letterTree = new HuffmanTree(letterList);

        //-------------------------------------

        letterList = map2list(letterMap);
        while (letterList.count() > 0){
            Node node = letterList.popHead();
            String symbol = node.symbol;

            System.out.println("encoded " + symbol + " is " + letterTree.encode(symbol));
        }

        //-------------------------------------

        String source = file2string(PATH_SOURCE);
        String encodedSource = letterTree.encode(source);
        System.out.println(encodedSource);
        writeFile(PATH_ENCODED, encodedSource);

        //-------------------------------------

        String encoded = file2string(PATH_ENCODED);
        String decoded = letterTree.decode(encoded);
        System.out.println(decoded);
        writeFile(PATH_DECODED, decoded);

        //-------------------------------------
    }

}