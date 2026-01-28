package myself;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class BFS {
    public static void main(String[] args) {
        Integer[][] matrix = {
            {0,1,1,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,1},
            {0,0,0,0,1,0,0,0,0},
            {0,0,0,0,0,1,1,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
        };
        Map<Integer, Integer> parent = new HashMap<>();
        Queue<Integer> list = new LinkedList<>();
        Stack<Integer> display = new Stack<>();
        list.add(0);
        parent.put(0, null);
        Integer dinhKe;
        Integer dinhHienTai = 0;
        Integer dinhKetThuc = 7;
        Integer dinhBatDau = 0;
        Boolean ketThuc = true;
        Boolean[] dinhDaDuyet = {true, true, true, true, true, true, true, true, true};
        dinhDaDuyet[dinhBatDau] = false;

        while (ketThuc) {
            if (!list.isEmpty()) {
                dinhHienTai = list.poll();
                if (dinhHienTai == dinhKetThuc) {
                    ketThuc = false;
                }
            } else {
                for (int i = 0; i < dinhDaDuyet.length; i++) {
                    if (dinhDaDuyet[i]) {
                        dinhHienTai = i;
                        dinhDaDuyet[i] = false;
                        break;
                    }
                }
            }
            
            System.out.print(getChar(dinhHienTai) + "|");
            
            for (int i = 0; i < matrix[dinhHienTai].length; i++) {
                if (matrix[dinhHienTai][i] == 1) {
                    dinhKe = i;
                   
                    if (!list.contains(dinhKe)) {
                        list.add(dinhKe);
                        dinhDaDuyet[i] = false;
                        parent.put(dinhKe, dinhHienTai);
                    }
                    System.out.print(getChar(dinhKe) + " ");
                }
            }
            System.out.print("|");
            for (Integer item : list) {
                System.out.print(getChar(item) + " ");
            }
            System.out.println();
        }

        System.out.print("Duong di ngan nhat: ");
        if (parent.containsKey(dinhKetThuc)) {
            Integer start = dinhKetThuc;
            display.add(dinhKetThuc);
            while (parent.get(start) != null) {
                display.add(parent.get(start));
                start = parent.get(start);
            }

            while (!display.isEmpty()) {
                System.out.print(getChar(display.pop()) + " ");
            }
        } else 
        {
            System.out.print("None");
        }
    }

    private static char getChar(Integer number) {
        return (char)(number + 65);
    }
}
