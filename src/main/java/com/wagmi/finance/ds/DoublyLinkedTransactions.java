package main.java.com.wagmi.finance.ds;

/*
 TODO[Student]: Doubly linked list of transactions
 - Implement tail-insert `add`, `deleteById`, `findById`, `reverse`, `toArray`.
 - Maintain `head`, `tail`, and `size` correctly across all operations.
 - After each edit, run `DoublyLinkedTransactionsTest` (or `./scripts/test-run.sh`).
*/

import main.java.com.wagmi.finance.model.Transaction;

public class DoublyLinkedTransactions {

    private static class Node {
        Transaction value;
        Node prev;
        Node next;

        Node(Transaction value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public void add(Transaction tx) {
        // stub: implement insertion at tail
        Node temp= new Node(tx);
       if(head==null){
           head = temp;
           tail=temp;
       }
       else{
           tail.next=temp;
           temp.prev=tail;
           tail=temp;
       }
       size++;
    }

    public boolean deleteById(String id) {
        // stub: implement search and delete
     if(head== null || id==null){
         return false;
     }
     Node current = head;
     while (current!= null){
         if (current.value.getId().equals(id)){
            if ( current==head){
                head=current.next;
                if(head != null) {
                    head.prev = null;
                } else {
                    tail=null;
                }
            }
            else if (current== tail) {
                tail = current.prev;
                if ( tail != null) {
                    tail.next = null;
                }
            }
            else {
                current.prev.next = current.next;
                current.next.prev = current.prev;
            }
            size--;
            return true;
         }
         current = current.next;
     }
    return false;
    }

    public Transaction findById(String id) {
        // stub: linear search
        if (id == null) {
            return null;
        }
        Node current = head;
        while (current != null) {
            if (current.value != null) {
                String currentId = current.value.getId();
                if (id.equals(currentId)) {
                    return current.value;
                }
            }
            current = current.next;
        }
        return null;
    }

    public void reverse() {
        // stub: reverse pointers
       Node current= head;
       Node temp = null;
       while( current != null) {
           temp = current.prev;
           current.prev = current.next;
           current.next=temp;
           current = current.prev;
       }
       if ( temp != null){
           head = temp.prev;
       }
    }

    public int size() {
        return size;
    }

    public Transaction[] toArray() {
        // stub: iterate from head
        Transaction[] arr=new Transaction[size];
        Node current = head;
        int index=0;
        while (current != null) {
            arr[ index++] = current.value;
            current= current.next;
        }
        return arr;
    }
}
