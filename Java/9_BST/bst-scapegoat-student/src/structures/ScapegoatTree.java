package structures;

import java.util.Iterator;

public class ScapegoatTree<T extends Comparable<T>> extends
		BinarySearchTree<T> {
	private int upperBound;


	@Override
	public void add(T t) {
		// TODO
		if (t == null) {
			throw new NullPointerException();
		}
		BSTNode<T> node = new BSTNode<>(t, null, null);
		root = addToSubtree(root, node);
		upperBound++;
		if ( height() > (Math.log10(upperBound) /  Math.log10((double) 3 / 2))){
			for(int i = size(); i > 0; i--){
				if (3 * (subtreeSize(node) - 1) > 2 * subtreeSize(node)){
					BinarySearchTree other = new BinarySearchTree();
					other.root = node;
					other.balance();
					if( node.getParent().getLeft() != null && node.getParent().getLeft().getData() == node.getData()){
						node.getParent().setLeft(other.root);
					}else{
						node.getParent().setRight(other.root);
					}
					break;
				}
				node = node.getParent();
			}
		}
	}


	@Override
	public boolean remove(T element) {
		// TODO
		boolean result = super.remove(element);
		if (upperBound > 2 * size()){
			balance();
			upperBound = size();
		}
		return result;
	}
}
