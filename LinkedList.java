/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
		public static void main(String[] args) {
			LinkedList list = new LinkedList();
			System.out.println("Adding blocks to the list...");
			list.addFirst(new MemoryBlock(0, 10));
			list.addFirst(new MemoryBlock(10, 20));
			System.out.println("List after adding two blocks: " + list);
	
			System.out.println("\nGetting block at index 0:");
			System.out.println(list.getBlock(0)); 
			list.addLast(new MemoryBlock(30, 40));
			System.out.println("\nList after adding block to the end:");
			System.out.println(list);
			System.out.println("\nRemoving block at index 1...");
			list.remove(1); 
			System.out.println("List after removal: " + list);
			System.out.println("\nList size: " + list.getSize());
		}
	
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(	"index must be between 0 and size");
		}
		int count = 0;
		Node current = this.first;
		while (current != null) {
			if (count == index) {
				return current;
			}
			count++;
			current = current.next;
		}
		return null;
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		if (index < 0 || index > this.size) {
			throw new IllegalArgumentException("Index out of bounds");
		}
		Node newNode = new Node(block);
		if (index == 0) {
			if (this.size == 0) {
				this.first = newNode;
				this.last = newNode;
			} else {
				newNode.next = this.first;
				this.first = newNode;
			}
		} 
		else if (index == this.size) {
			this.last.next = newNode;
			this.last = newNode;
		} 
		else {
			Node previous = getNode(index - 1);
			newNode.next = previous.next;
			previous.next = newNode;
		}
		this.size++;
	}
	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
			Node newNode = new Node(block);
			if (this.size == 0) {
				this.first = newNode;
				this.last = newNode;
			} else {
				this.last.next = newNode;
				this.last = newNode;
			}
			this.size++;
		}
	
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		Node newNode = new Node(block);
		if (this.size == 0) {
			this.first = newNode;
			this.last = newNode;
		} else {
			newNode.next = this.first;
			this.first = newNode;
		}
		this.size++;
	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	
	//public MemoryBlock getBlock(int index) {
	//	if (size == 0) {
	//	throw new IllegalArgumentException("TERROR IllegalArgumentException: List is empty. Index must be between 0 and size.");
	//}
	//		if (index < 0 || index >= size) {
	//	throw new IllegalArgumentException("ERROR IllegalArgumentException: Index must be between 0 and size.");
	//	}
	//	return getNode(index).block;
	//}
	
	public MemoryBlock getBlock(int index) {
		if (size==0) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		return getNode(index).block;
	}
	
	
	
	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		Node current = this.first;
		int index = 0;
		while (current != null) {
			if (current.block.equals(block)) {
				return index;
			}
			current = current.next;
			index++;
		}
		return -1;
	}
	

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		if (node == null) {
			throw new NullPointerException("ERROR NullPointerException: Cannot remove a null node.");
		}
		Node previous = null;
		Node current = first;
		while (current != null && !node.block.equals(current.block)) {
			previous = current;
			current = current.next;
		}
		if (current == null) {
			throw new IllegalArgumentException("ERROR IllegalArgumentException: Node not found in the list.");
		}
		if (previous == null) { 
			first = first.next;
			if (size == 1) {
				last = null;
			}
		} else { 
			previous.next = current.next;
			if (current.next == null) {
				last = previous;
			}
		}
		size--;
	}
	
	
	
	

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */

	//public void remove(int index) {
	//	if (size == 0) {
	//		throw new IllegalArgumentException("ERROR IllegalArgumentException: List is empty.");
	//	}
	//	if (index < 0 || index >= size) {
	//		throw new IllegalArgumentException("ERROR IllegalArgumentException: Position must be between 0 and size - 1.");
	//	}
	//	if (index == 0) {
	//		if (size == 1) { 
	//			first = null;
	//			last = null;
	//		} else {
	//			first = first.next;
	//		}
	///	} else {
		//	Node previous = getNode(index - 1);
		//	Node toRemove = previous.next;
		//	previous.next = toRemove.next;
		//	if (toRemove.next == null) { 
		//		last = previous;
		//	}
	//	}
	//	size--;
//	}
	public void remove(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		Node current = first; 
		Node prev = null;
		if (index == 0) {
			first = current.next;
			if (first == null) {  
				last = null;
			}
			size--;
			return;
		}
		int counter = 0;

		while (current != null && counter != index) {
			prev = current;
			current = current.next;
			counter++;
		}
		if (current == null) {
			return;
		}
		if (index == size - 1) {
			prev.next = null; 
			last = prev; 
		} else {
			prev.next = current.next;
		}
		size--;
	}
	
	
	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	
	//public void remove(MemoryBlock block) {
	//	int position = indexOf(block);
	//	if (position < 0) {
	//		throw new IllegalArgumentException("ERROR IllegalArgumentException: Block not found. Position must be between 0 and size.");
	//	}
	//	remove(position);
	//}
	//public void remove(MemoryBlock block) {
	//	if (block == null) {
	//		throw new IllegalArgumentException("ERROR IllegalArgumentException: Block cannot be null.");
	//	}
	//
	//	int position = indexOf(block);
	//
	//	if (position < 0) {
	//		throw new IllegalArgumentException("ERROR IllegalArgumentException: Block not found. Position must be between 0 and size.");
	//	}
	
	//	remove(position);
	//}
	public void remove(MemoryBlock block) {
		if (indexOf(block) == -1) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
		Node current = first; 
		Node prev = null; 
		if (block.equals(first.block)) {
			first = first.next;
			if (first == null) {  
				last = null;
			}
			size--;
			return;
		}
		while (current != null) {
			if (block.equals(current.block)) {
				if (current == last) {
					last = prev;
					prev.next = null;
				} else {
					prev.next = current.next;
				}
				size--;
				return;
			}
			prev = current;
			current = current.next;	
			}
	}
	

	
	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		String s = "";
		Node current = first;
		while (current != null) {
		s = s + current.block + " ";
		current = current.next;
		}
		return s;
		}
}