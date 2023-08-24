package DHEC;

public class Test {

    public static void main(String[] args) {
        Curve curve = new Curve(2,1,5);
        Point beginPoint = new Point(0,1);
        Point temp = new Point(0, 1);
        for (int i = 0; i < 18; i++) {

            System.out.println(DHECLibrary.getPontN(i, beginPoint,temp, curve));
        }
    }
}

// нужно написать алгоритм быстрой сортировки
