package tree;

class Node1{
	
	int data;
	Node1 left,right;
	
	public Node1(int data) {
		this.data=data;
		this.right=null;
		this.left=null;
	}
}
public class LevelOrder {
	
	Node1 root = null;
	
	public static int height(Node node) {
		if(node == null) {
			return 0;
		}
		return 1+Math.max(height(node.left),height(node.right));
	}
	public static void BFS(Node node) {
		int height=height(node);
		System.out.println(node.key);
		for(int i=1;i<height;i++) {
		
		BFSprint(node, i, 0);
			
		}
	}
	public static void BFSprint(Node node,int key,int height) {
		if(node ==null) {return;}
		if(height ==key) {
			System.out.println(node.key);
			return;
		}
		BFSprint(node.left, key, height+1);
		BFSprint(node.right, key, height+1);
	}
	public static void main(String[] a) {
		
		LevelOrder levelOrder = new LevelOrder();
		levelOrder.root = new Node1(1);
		levelOrder.root.left = new Node1(2);
		levelOrder.root.right = new Node1(3);
		levelOrder.root.left.left = new Node1(5);
		levelOrder.root.left.right= new Node1(4);
		levelOrder.root.right.right = new Node1(6);
		levelOrder.root.right.left = new Node1(7);
		
		System.out.println("height is -");
		int h=height(levelOrder.root);
		System.out.println(h);
		
		System.out.println("level order traversal -");
		BFS(levelOrder.root,h);
		
	}

}
 
