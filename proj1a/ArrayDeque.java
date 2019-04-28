public class ArrayDeque<T>{

    /*需要实现的几个特征：

    1.Circular;
        1) start end(初始化为0，只有当add或者remove的时候会改变)
        2) minusOne, plusOne
            为了方便实现：到array最后就跳到第一个，到第一个再往前跳到最后一个

    2.Resizing;
        1) expand(空间不够时，拓展array的size)
        2) contract(占有率不足阈值时，压缩array的size)
            更新后start都是0;

    3.Generic;

    */
    private int start;
    private int end;
    private int size;
    private T[] items;



    public ArrayDeque(){
        /*注意generic的初始化语法*/
        start = 0;
        end = 0;
        size = 0;
        items = (T[])new Object[8];
    }

    public ArrayDeque(ArrayDeque other){

    }

    public void addFirst(T item){
        if(items.length == size){
            expand();
        }
        start = minusOne(start);
        items[start] = item;
        size += 1;
    }

    public void addLast(T item){
        if(items.length == size){
            expand();
        }
        end = plusOne(end);
        items[end] = item;
        size += 1;

    }



    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public int size() {
        return size;
    }

    public T removeFirst(){
        if(size == 0){
            return null;
        }
        if(size <= items.length / 4){
            contract();
        }
        T res = items[start];
        items[start] = null;
        start = plusOne(start);
        size -= 1;
        return res;
    }

    public T removeLast(){
        if(size == 0){
            return null;
        }
        if(size <= items.length / 4){
            contract();
        }
        T res = items[end];
        items[end] = null;
        end = plusOne(end);
        size -= 1;
        return res;
    }

    /* The start of the index is 1 */
    public T get(int index){
        if(index + start >= items.length){
            return items[index + start - items.length];
        }
        else{
            return items[index + start];
        }
    }

    public void printDeque(){
        String printout = new String();
        for(int i = start; i != end; i = plusOne(i)){
            printout += String.valueOf(items[i]);
            printout += " ";
        }
        printout += String.valueOf(items[end]);
        System.out.println(printout);
    }

    private void expand(){
        T[] tmp = (T[]) new Object[items.length * 2];
        if(start > end){
            System.arraycopy(items, start, tmp, 0, items.length - start);
            System.arraycopy(items, 0, tmp, items.length - start, size + start - items.length);
        }
        else{
            System.arraycopy(items, start, tmp, 0, size);
        }
        items = tmp;
        start = 0;
        end = size - 1;
    }

    private void contract(){
        if(items.length < 15){
            return;
        }
        T[] tmp = (T[]) new Object[items.length / 2];
        if(start > end){
            System.arraycopy(items, start, tmp, 0, items.length - start);
            System.arraycopy(items, 0, tmp, items.length - start, size + start - items.length);
        }
        else{
            System.arraycopy(items, start, tmp, 0, size);
        }
        items = tmp;
        start = 0;
        end = size - 1;
    }

    private int minusOne(int i){
        if(i == 0){
            return items.length - 1;
        }
        else{
            return i - 1;
        }
    }

    private int plusOne(int i){
        if(i == items.length - 1){
            return 0;
        }
        else{
            return i + 1;
        }
    }
}

