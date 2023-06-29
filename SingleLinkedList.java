
public class SingleLinkedList {
	Node head;
    Node sorted;
	
	public void add(Object data) { //sll adding process.
		if(head==null) {
			Node newNode= new Node(data); //If the head is empty, adding the data to the head by creating a new node.
			head=newNode;
		}
		else {
			Node temp=head;
			while(temp.getLink()!=null) { //If the head is not empty, the data is added by creating a new node by going to the end of the sll.
				temp=temp.getLink();
			}
			Node newNode=new Node(data);
			temp.setLink(newNode);
			
		}
	}
	
	public int size() {
		if(head==null) {
			return 0;
		}
		else {
			int count=0;
			Node temp=head;
			while (temp!=null) { // if the head is not empty, It is looped until sll is null and nodes are counted.
				temp=temp.getLink();
				count++;
			}
			return count;
		}
	}
	
	public void display() {
		if(head==null) {
			System.out.println("List is empty.");
			
		}else {
			Node temp=head;
			while(temp!=null) { // printing the node's datas to the screen.
				System.out.print(temp.getData()+"  ");
				temp=temp.getLink();
			}
		}
	}
	public void printListhighscore()
    {
		int counter = 0;
        Node temp = head;
        while (temp != null & counter < 10) {
            System.out.println(temp.getData() + " "); //The difference from the display() method is that the elements are printed one below the other. And only prints first 10 elements.
            temp = temp.getLink();
            counter++;
        }
    }
	
	public void remove(Object dataToDelete) {
		if(head==null) {
			System.out.println("Linked list is empty.");
		}else {
			while((int)head.getData()==(int)dataToDelete)
				head=head.getLink();
			Node temp=head;
			Node previous=null;
			while(temp!=null) {
			if((int)temp.getData()==(int)dataToDelete) {
				previous.setLink(temp.getLink());
				temp=previous;
			}
			previous=temp;
			temp=temp.getLink();
		  }
		}
	}
	public boolean search(Object data) {
		boolean flag = false;
		if (head == null)
			System.out.println("linked list is empty");
		else {
			Node temp = head;
			while (temp != null) {
				if (data == temp.getData())
					flag = true;
					temp = temp.getLink();
				}
		}
		return flag;
	}
	
	public void deleteNode(int key) //deletes only the node that matches the entered data
    {
        Node temp = head, prev = null;
 
        // If head node itself holds the key to be deleted
        if (temp != null && (int)temp.getData() == key) {
            head = temp.getLink(); // Changed head
            return;
        }
 
        // Search for the key to be deleted, keep track of the previous node as we need to change temp.getlink()
        while (temp != null && (int)temp.getData() != key) {
            prev = temp;
            temp = temp.getLink();
        }
 
        // If key was not present in linked list
        if (temp == null)
            return;
 
        // Unlink the node from linked list
        prev.setLink(temp.getLink());
    }
	int count(int search_for) //Counts how many times the entered data is repeated.
    {
        Node temp = head;
        int count = 0;
        while (temp != null) {
            if ((int)temp.getData() == search_for)
                count++;
            temp = temp.getLink();
        }
        return count;
    }
	 
	public void sortList() //sort method for highscore sll.
    {
        Node current = head, index = null;  
        Object temp;  
          
        if(head == null) {  
            return;  
        }  
        else {  
            while(current != null) {  
                index = current.getLink();  
                while(index != null) {  
                    //If current node's data is greater than index's node data, swap the data between them  
                    if(Integer.parseInt(current.getData().toString().split(" ")[1]) < Integer.parseInt(index.getData().toString().split(" ")[1])) {  
                        temp = (Object)current.getData();  
                        current.setData(index.getData());  
                        index.setData(temp);  
                    }  
                    index = index.getLink();  
                }  
                current = current.getLink();  
            }      
        }  
    } 
	
	
	
	public int find10thElement() {//Returns the score of the last placed player for highscore.
		int counter = 0;
		Node temp = head;
		String data = null;
		while (temp !=  null && counter < 10) {
			data = (String) temp.getData();
			temp = temp.getLink();
			counter++;
		}
		
		return Integer.parseInt(data.toString().split(" ")[1]);
	}
	 
}
