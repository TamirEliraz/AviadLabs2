package lab3;

public class ListTestInteger extends ListTest<Integer> {
    private static int counter = 0;
    
    @Override
    public Integer getParameterInstance() {
        return counter++;
    }
}