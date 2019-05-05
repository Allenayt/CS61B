public class OffByN implements CharacterComparator{

    private int number;

    public OffByN(int n){
        number = n;
    }

    @Override
    public boolean equalChars(char x, char y){
        if(x - y == number || x - y == -number){
            return true;
        }
        return false;
    }
}
