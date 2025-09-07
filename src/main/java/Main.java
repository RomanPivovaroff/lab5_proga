import java.util.ArrayList;
import java.util.Iterator;

class Scratch {
    public static void main(String[] args) {
        Iterable<Integer> it =
                new ArrayList<Integer>() {
                    {
                        this.add(1);
                        this.add(2);
                        this.add(3);
                    }
                };
        for (Iterator<Integer> itt = it.iterator(); itt.hasNext();) {
            Integer e = itt.next();
            System.out.println(e);
        }
    }
}
