package filesystem;

import structures.Queue;
import structures.UnboundedQueueInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;


/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem. A level-order traversal is equivalent to a breadth-
 * first search.
 */
public class LevelOrderIterator extends FileIterator<File> {
	public Queue<File> FI;
	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * @param rootNode
	 * @throws FileNotFoundException if the rootNode does not exist
	 */

	public LevelOrderIterator(File rootNode) throws FileNotFoundException {
        	// TODO 1
			if ( !rootNode.exists()){ throw new FileNotFoundException(" File Not Found");}
			FI = new Queue<File>();
			FI.enqueue(rootNode);
	}
	
	@Override
	public boolean hasNext() {
        	// TODO 2
			if (!FI.isEmpty()){ return true;}
            return false;
	}

	@Override
	public File next() throws NoSuchElementException {
        	// TODO 3
			if ( !hasNext()){ throw new NoSuchElementException();}
			File newFile = FI.dequeue();
			if ( newFile.listFiles() != null) {
				File[] temp = newFile.listFiles();
				Arrays.sort(temp);
				for (int i = 0; i < temp.length; i++){
					FI.enqueue(temp[i]);
				}
			}
			return newFile;
	}

	@Override
	public void remove() {
		// Leave this one alone.
		throw new UnsupportedOperationException();		
	}

}
