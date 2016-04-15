package test;
/*
 * Custom linked list class written which can perform all operations
 * we do on a linked list. Considering data to be integer for simplicity purpose.
 * To be more generic we can make define "data" as Object type so that any object
 * can be placed in linked list.
 */
public class MyLinkedList{
	private int data;
	private MyLinkedList next;
	
	//Constructor which takes data argument
	public MyLinkedList(int data){
		this.data = data;
	}
	
	//Method to set the data of the List node
	public void setData(int data){
		this.data = data;
	}
	
	//Method to get the data of the List node
	public int getData(){
		return data;
	}
	
	//Method to set next node for the List node
	public void setNext(MyLinkedList next){
		this.next = next;
	}
	
	//Method to get next node of the List node
	public MyLinkedList getNext(){
		return this.next;
	}
	
	/* Length of the list needs to be known before inserting or deleting
	 * nodes from the Linked List.
	 */
	
	// Method to get the length of the list
	
	public int ListLength(MyLinkedList headNode){
		int length = 0;
		MyLinkedList currentNode = headNode;
		while (currentNode!= null){
			length++;
			currentNode = currentNode.getNext();
		}
		return length;
	}
	
	/*
	 * Inserting a node in a Linked List can have three cases:
	 * 1) Inserting a new node before the head
	 * 2) Inserting a new node after the tail
	 * 3) Inserting a new node at the middle of the list
	 * 
	 * Explanation :
	 * 1) Inserting at the beginning : Only one "next" pointer needs
	 * to be modified. Update the "next" pointer of new node, to point to the current head and 
	 * update the "head" pointer to point to the new node.
	 * 2) Inserting at the end : "next" pointers of two nodes need to be modified. 
	 * New node's "next" pointer points to NULL and last node's next pointer points to the new node
	 * 3) Inserting in the middle: two "next" pointers need to be changed here as well
	 * (pos-1) node's "next" pointer should point to new node and new node's "next" pointer should point to 
	 * (pos+1) node.
	 */
	public MyLinkedList InsertInLinkedList(MyLinkedList headNode, MyLinkedList nodeToInsert, int position){
		if(headNode == null){
			return nodeToInsert;
		}
		
		//get size of the list otherwise
		int size = ListLength(headNode);
		
		if(position > size+1 || position <1){
			//Means invalid position is given. Print a note to the user.			
			System.out.println("Position of node to insert is invalid. The valid inputs are 1 to "+(size+1));
			return headNode;
		}
		
		if(position == 1){ //Inserting the node in the beginning
			nodeToInsert.setNext(headNode);
			return nodeToInsert;
		}else{ //Inserting the node in the middle or in the end
			MyLinkedList previousNode = headNode;
			int count = 1;
			while(count < position-1){
				previousNode = previousNode.getNext();
				count++;
			}
			MyLinkedList currentNode = previousNode.getNext();
			nodeToInsert.setNext(currentNode);
			previousNode.setNext(nodeToInsert);
		}
		return headNode;
	}
	
	/*
	 * Deleting a node also does have three cases as in insertion
	 * 1) Deleting the first node
	 * 2) Deleting the last node
	 * 3) Deleting an intermediate node
	 * 
	 * Explanation:
	 * 1) Deleting the first node : Create a temp node which will point to same node as that of head.
	 * Now, move the head nodes pointer to the next node and dispose the temporary node.
	 * 2) Deleting the last node : Traverse the list and while traversing maintain the previous node address.
	 * By the time we reach the end of the list, we will have two pointers one pointing to the tail node and 
	 * other pointing to the node before the tail node. Update previous node's "next" pointer to NULL. 
	 * Dispose the tail node.
	 * 3) Similar to the second case, maintain previous node while traversing the list. Once we found the node
	 * to be deleted, change the previous node's "next" pointer to "next" pointer of the node to be deleted.
	 * Dispose the current node to be deleted
	 */
	public MyLinkedList DeleteNodeFromLinkedList(MyLinkedList headNode, int position){
		//get size of the linked list
		int size = ListLength(headNode);
		
		if(position > size || position < 1){
			//invalid position given
			System.out.println("Position of node to delete is invalid. The valid inputs are 1 to "+size);
			return headNode;
		}
		
		if(position == 1){ //deleting from beginning
			MyLinkedList currentNode = headNode.getNext();
			headNode = null;
			return currentNode;
		}else{
			MyLinkedList previousNode = headNode;
			int count =1;
			while (count<position){
				previousNode = previousNode.getNext();
				count++;
			}
			MyLinkedList currentNode = previousNode.getNext();
			previousNode.setNext(currentNode.getNext());
			currentNode = null;
		}
		return headNode;
	}
	
	/*
	 * Deleting the list itself. 
	 * Store the current node in some object and free the current node. After freeing the current node,
	 * go to next node with temporary object and repeat the process for all the nodes.
	 */
	public void DeleteLinkedList(MyLinkedList head){
		MyLinkedList auxilaryNode, iterator=head;
		while(iterator!=null){
			auxilaryNode = iterator.getNext();
			iterator = null;
			iterator = auxilaryNode; //May be not necessary!
		}
	}
}
