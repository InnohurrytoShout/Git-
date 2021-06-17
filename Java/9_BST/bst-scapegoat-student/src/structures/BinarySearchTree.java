package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements
		BSTInterface<T> {
	protected BSTNode<T> root;

	public boolean isEmpty() {
		return root == null;
	}

	public int size() {
		return subtreeSize(root);
	}

	protected int subtreeSize(BSTNode<T> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + subtreeSize(node.getLeft())
					+ subtreeSize(node.getRight());
		}
	}

	public boolean contains(T t) {
		// TODO
		if (t == get(t)){
			return true;
		}
		return false;
	}


	public boolean remove(T t) {
		if (t == null) {
			throw new NullPointerException();
		}
		boolean result = contains(t);
		if (result) {
			root = removeFromSubtree(root, t);
		}
		return result;
	}

	protected BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
		// node must not be null
		int result = t.compareTo(node.getData());
		if (result < 0) {
			node.setLeft(removeFromSubtree(node.getLeft(), t));
			return node;
		} else if (result > 0) {
			node.setRight(removeFromSubtree(node.getRight(), t));
			return node;
		} else { // result == 0
			if (node.getLeft() == null) {
				return node.getRight();
			} else if (node.getRight() == null) {
				return node.getLeft();
			} else { // neither child is null
				T predecessorValue = getHighestValue(node.getLeft());
				node.setLeft(removeRightmost(node.getLeft()));
				node.setData(predecessorValue);
				return node;
			}
		}
	}

	private T getHighestValue(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getData();
		} else {
			return getHighestValue(node.getRight());
		}
	}

	private BSTNode<T> removeRightmost(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getLeft();
		} else {
			node.setRight(removeRightmost(node.getRight()));
			return node;
		}
	}

	public T get(T t) {
		// TODO
		if (t == null){
			throw new NullPointerException("");
		}
		Iterator value = inorderIterator();
		while (value.hasNext()){
			if (t.equals(value.next())){
				return t;
			}
		}
		return null;
	}


	public void add(T t) {
		if (t == null) {
			throw new NullPointerException();
		}
		root = addToSubtree(root, new BSTNode<T>(t, null, null));
	}

	protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) {
		if (node == null) {
			return toAdd;
		}
		int result = toAdd.getData().compareTo(node.getData());
		if (result <= 0) {
			node.setLeft(addToSubtree(node.getLeft(), toAdd));
		} else {
			node.setRight(addToSubtree(node.getRight(), toAdd));
		}
		return node;
	}

	@Override
	public T getMinimum() {
		// TODO
		BSTNode<T> temp = root;
		if (root != null){
			while (temp.getLeft() != null){
				temp = temp.getLeft();
			}
			return temp.getData();
		}
		return null;
	}


	@Override
	public T getMaximum() {
		// TODO
		BSTNode<T> temp = root;
		if (root != null){
			while (temp.getRight() != null){
				temp = temp.getRight();
			}
			return temp.getData();
		}
		return null;
	}


	@Override
	public int height() {
		// TODO
		return height(root);
	}

	private int height(BSTNode<T> node){
		if (node == null){
			return -1;
		}
		int leftdepth = height(node.getLeft());
		int rightdepth = height(node.getRight());
		if (leftdepth > rightdepth){
			return (leftdepth + 1);
		}else{
			return (rightdepth + 1);
		}
	}


	public Iterator<T> preorderIterator() {
		// TODO
		Queue<T> queue = new LinkedList<T>();
		preorderTraverse(queue, root);
		return queue.iterator();
	}

	private void preorderTraverse(Queue<T> queue, BSTNode<T> node){
		if (node != null){
			queue.add(node.getData());
			preorderTraverse( queue, node.getLeft());
			preorderTraverse( queue, node.getRight());
		}
	}

	public Iterator<T> inorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		inorderTraverse(queue, root);
		return queue.iterator();
	}


	private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			inorderTraverse(queue, node.getLeft());
			queue.add(node.getData());
			inorderTraverse(queue, node.getRight());
		}
	}

	public Iterator<T> postorderIterator() {
		// TODO
		Queue<T> queue = new LinkedList<T>();
		postorderTraverse(queue, root);
		return queue.iterator();
	}

	private void postorderTraverse(Queue<T> queue, BSTNode<T> node){
		if (node != null){
			postorderTraverse(queue, node.getLeft());
			postorderTraverse(queue, node.getRight());
			queue.add(node.getData());
		}
	}

	@Override
	public boolean equals(BSTInterface<T> other) {
		// TODO
		if (other == null){
			throw new NullPointerException("");
		}
		if (other.size() != this.size()){
			return false;
		}
		if (other.size() == 1 && this.size() == 1){
			if (other.getRoot().getData().equals(this.getRoot().getData())){
				return true;
			}
		}
		Iterator<T> i = this.inorderIterator();
		Iterator<T> j = other.inorderIterator();
		Iterator<T> i1 = this.preorderIterator();
		Iterator<T> j1 = other.preorderIterator();
		Iterator<T> i2 = this.postorderIterator();
		Iterator<T> j2 = other.postorderIterator();
		while (i.hasNext()){
			if (!i.next().equals(j.next())){
				return false;
			}
			if (!i1.next().equals(j1.next())){
				return false;
			}
			if(!i2.next().equals(j2.next())){
				return false;
			}
		}
		return true;
	}


	@Override
	public boolean sameValues(BSTInterface<T> other) {
		// TODO
		if (other == null){
			throw new NullPointerException("");
		}
		if (other.size() != this.size()){
			return false;
		}
		if (other.size() == 1 && this.size() == 1){
			if( other.getRoot().getData() == this.getRoot().getData()){
				return true;
			}
		}
		boolean a = true;
		Iterator<T> i = this.inorderIterator();
		Iterator<T> j = other.inorderIterator();
		Iterator<T> i1 = this.preorderIterator();
		Iterator<T> j1 = other.preorderIterator();
		Iterator<T> i2 = this.preorderIterator();
		Iterator<T> j2 = other.preorderIterator();
		while (i.hasNext()){
			if (!i.next().equals(j.next()) && !i1.next().equals(j1.next()) && !i2.next().equals(j2.next())){
				a = false;
			}
		}
		return a;
	}

	@Override
	public boolean isBalanced() {
		// TODO
		if ( root == null || (size() >= (int) Math.pow(2, height()) && size() < (int) Math.pow(2, height() + 1))){
			return true;
		}
		return false;
	}

	@Override
    @SuppressWarnings("unchecked")

	public void balance() {
		// TODO
		T[] sortedArray = (T[]) new Comparable[size()];
		Iterator value = inorderIterator();
		for (int i = 0; value.hasNext(); i++){
			sortedArray[i] = (T) value.next();
		}
		root = sortedArray2BST(0, sortedArray.length - 1, sortedArray);
	}

	protected BSTNode<T> sortedArray2BST(int lower, int higher, T[] array){
		if (lower > higher){
			return null;
		}
		int mid = (lower + higher) / 2;
		BSTNode<T> node = new BSTNode<T>(array[mid], null, null);
		node.setLeft(sortedArray2BST(lower, mid - 1, array));
		node.setRight(sortedArray2BST(mid + 1, higher, array));
		return node;
	}

	@Override
	public BSTNode<T> getRoot() {
        // DO NOT MODIFY
		return root;
	}

	public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
		// header
		int count = 0;
		String dot = "digraph G { \n";
		dot += "graph [ordering=\"out\"]; \n";
		// iterative traversal
		Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
		queue.add(root);
		BSTNode<T> cursor;
		while (!queue.isEmpty()) {
			cursor = queue.remove();
			if (cursor.getLeft() != null) {
				// add edge from cursor to left child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getLeft().getData().toString() + ";\n";
				queue.add(cursor.getLeft());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}
			if (cursor.getRight() != null) {
				// add edge from cursor to right child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getRight().getData().toString() + ";\n";
				queue.add(cursor.getRight());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}

		}
		dot += "};";
		return dot;
	}

	public static void main(String[] args) {
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			BSTInterface<String> tree = new BinarySearchTree<String>();
			for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
				tree.add(s);
			}
			Iterator<String> iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.preorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.postorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();

			System.out.println(tree.remove(r));

			iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
		}

		BSTInterface<String> tree = new BinarySearchTree<String>();
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			tree.add(r);
		}
		System.out.println(tree.size());
		System.out.println(tree.height());
		System.out.println(tree.isBalanced());
		tree.balance();
		System.out.println(tree.size());
		System.out.println(tree.height());
		System.out.println(tree.isBalanced());
	}
}