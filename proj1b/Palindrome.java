public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> lst = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++){
            lst.addLast(word.charAt(i));
        }
        return lst;
    }

    public boolean isPalindrome(String word){
        Deque lst = wordToDeque(word);
        return isPal(lst);

    }

    private boolean isPal(Deque list){
        if(list.size() <= 1){
            return true;
        }
        else{
            Object p = list.removeFirst();
            Object q = list.removeLast();
            if(p == q){
                return isPal(list);
            }
            else{
                return false;
            }
        }

    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque lst = wordToDeque(word);
        return isPal2(lst, cc);
    }

    private boolean isPal2(Deque list, CharacterComparator cc){
        if(list.size() <= 1){
            return true;
        }
        else{
            Object p = list.removeFirst();
            Object q = list.removeLast();
            if(cc.equalChars((char)p, (char)q)){
                return isPal2(list, cc);
            }
            else{
                return false;
            }
        }

    }
}