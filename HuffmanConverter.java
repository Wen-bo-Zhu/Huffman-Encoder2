package ;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class HuffmanConverter {
	public static final int NUMBER_OF_CHARACTERS = 256; 
	private String contents; // the contents of our message...
	private HuffmanTree huffmanTree; // the tree created from the msg
	private int count[];	// tracks how often each character occurs
	private String code[];	// the huffman code for each character
	private int uniqueChars = 0; 	// stores the # of uniq
	
	public HuffmanConverter(String input){
		this.contents = input;
		this.count = new int[NUMBER_OF_CHARACTERS];
		this.code = new String[NUMBER_OF_CHARACTERS];
	}

	public void recordFrequencies(){
		for (int i = 0; i < contents.length(); i++){
			char c = contents.charAt(i);
			int x = (int) c;
			count[x] += 1;
			code[x] = Character.toString(c);
		}
	}

	public void frequenciesToTree(){
		int val = 0;
		int counter = 0;
		for (int i = 0; i < count.length; i++){
			if (count[i]!=0){
				val += 1;
			}
		}
		HuffmanNode [] huffarray = new HuffmanNode[val];
		for (int j = 0; j < count.length; j++){
			if (count[j]!= 0){
				huffarray[counter] = new HuffmanNode(code[j],(Integer) count[j]);
				counter += 1;
			}
		}
		BinaryHeap q = new BinaryHeap(huffarray);
		q.printHeap();
		System.out.println("");
		huffmanTree = huffmanTree.createFromHeap(q);
		huffmanTree.printLegend();
	}
	
	public void treeToCode(){
		for (int i=0; i<code.length;i++){
			code[i] = "";
		}
		treeToCode(huffmanTree.root,"");
	}

	private void treeToCode(HuffmanNode t, String s){
		if (t.letter.length()>1){
			treeToCode(t.left,s + "1");
			treeToCode(t.right, s + "0");
		}else{
			code[(int)t.letter.charAt(0)] = s;
		}
	}

	public String encodeMessage(){
		String res = "";
		for (int i=0;i<contents.length();i++){
			char c = contents.charAt(i);
			int s = (int) c;
			res += code[s];
		}
		return res;
	}

	public static String readContents(String filename){
		String text = ""; 
		try { text = new String(Files.readAllBytes(Paths.get(filename))); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
		return text;
	}

	public String decodeMessage(String encodedStr){
		String res = "";
		String index = "";
		for(int i = 0; i < encodedStr.length();i++){
			index += encodedStr.charAt(i);
			for (int j = 0; j<code.length; j++){
				if ((index).equals(code[j])){
					res += (char) j;
					index="";
					break;
				}
			}
		}
		return res;
	}	
	
	public static void main(String args[]) throws Exception{
		HuffmanConverter obj = new HuffmanConverter(readContents(args[0]));
		obj.recordFrequencies();
		obj.frequenciesToTree();
		obj.treeToCode();
		String ecm = obj.encodeMessage();
		System.out.println();
		System.out.println("Huffman Encoding:");
		System.out.println(ecm);
		System.out.println();
		System.out.println("Message size in ASCII encoding:" + obj.contents.length()*8);
		System.out.println("Message size in Huffman coding:" + ecm.length());
		System.out.println();
		System.out.println(obj.decodeMessage(ecm));
	}
}