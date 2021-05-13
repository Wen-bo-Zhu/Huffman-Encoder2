package ;

public class HuffmanNode implements Comparable{
	public String letter;
	public Integer frequency;
	public HuffmanNode left, right;
	
	public HuffmanNode(String letter, Integer frequency){
		this.letter = letter;
		this.frequency = frequency;
		left = null;
		right = null;
	}
	
	public HuffmanNode(HuffmanNode left, HuffmanNode right){
		this.letter = left.letter + right.letter;
		this.frequency = left.frequency + right.frequency;
		this.left = left;
		this.right = right;
	}
	
	public int compareTo(Object o){
		HuffmanNode huff = (HuffmanNode) o;
		return this.frequency.compareTo(huff.frequency);
	}
	
	public String toString(){
		return "<"+this.letter+", "+this.frequency+">";
	}

}
