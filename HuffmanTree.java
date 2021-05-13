package ;

public class HuffmanTree {
	public HuffmanNode root;
	
	public HuffmanTree(HuffmanNode huff){
		this.root = huff;
	}

	
	public void printLegend(){
		printLegend(root,"");
	}
	
	public void printLegend(HuffmanNode t, String s){
		if (t.letter.length()>1){
			printLegend(t.left,s + "1");
			printLegend(t.right, s + "0");
		}else if (t.letter.length()==1){
			System.out.println(t.letter+"="+s+" ");
		}
	}
	
	public static BinaryHeap legendToHeap(String legend){
		BinaryHeap<HuffmanNode> q = new BinaryHeap<HuffmanNode>(legend.length());
		String[] res = legend.split(" ");
		for (int i = 0; i< res.length;i++){
			if (res[i].matches("[0-9]*")){
				HuffmanNode nn = new HuffmanNode(res[i],Integer.valueOf(res[i+1]));
				q.insert(nn);
			}
		}
		return q;
	}
	
	public static HuffmanTree createFromHeap(BinaryHeap b){
		while (b.getSize()>1){
			HuffmanNode x = (HuffmanNode) b.deleteMin();
			HuffmanNode y = (HuffmanNode) b.deleteMin();
			HuffmanNode n = new HuffmanNode(x,y);
			b.insert(n);
		}
		HuffmanTree result = new HuffmanTree((HuffmanNode) b.deleteMin());
		return result;
	}

}
