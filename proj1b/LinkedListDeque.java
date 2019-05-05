public class LinkedListDeque<T> implements Deque<T>{

    /*需要实现的几个特征：

    1.Circular;

    2.Nested Class;

    3.Generic;

    */


    /*circular sentinel topology*/
    /*构造时要使用Private的辅助节点构造单独的sentinel作为开始, prev和next都指向自己*/
    private int size;
    private Node sentinel;


    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

        for(int i = 0; i < other.size(); i++){
            addLast((T) other.get(i));
        }

    }

    @Override
    public void addFirst(T item){
        Node tmp = sentinel.next;
        sentinel.next = new Node(sentinel, item, tmp);
        tmp.prev = sentinel.next;
        size += 1;
    }

    @Override
    public void addLast(T item){
        Node tmp = sentinel.prev;
        sentinel.prev = new Node(tmp, item, sentinel);
        tmp.next = sentinel.prev;
        size += 1;
    }

    @Override
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        Node tmp = sentinel.next;
        sentinel.next = tmp.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return tmp.data;
    }

    @Override
    public T removeLast(){
        if(size == 0){
            return null;
        }
        Node tmp = sentinel.prev;
        sentinel.prev = tmp.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return tmp.data;
    }

    @Override
    public T get(int index){
        if(size == 0 || index >= size || index < 0){
            return null;
        }
        Node ptr = sentinel;
        while(index >= 0){
            ptr = ptr.next;
            index -= 1;
        }
        return ptr.data;
    }

    public T getRecur(int index){
        if(size == 0 || index >= size || index < 0){
            return null;
        }
        else{
            return sentinel.next.getRecur(index);
        }
    }

    @Override
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        Node ptr = sentinel;
        String printout = new String();
        while(ptr.next != sentinel){
            ptr = ptr.next;
            printout += String.valueOf(ptr.data);
            printout += " ";
        }
        System.out.println(printout);
    }



    private class Node{
        private Node prev;
        private Node next;
        private T data;

        private Node(Node a, T item, Node b){
            prev = a;
            data = item;
            next = b;
        }

        private T getRecur(int x){
            if(x == 0){
                return data;
            }
            else{
                return next.getRecur(x - 1);
            }
        }
    }

}
