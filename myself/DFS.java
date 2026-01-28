package myself;
import java.util.Stack;

public class DFS {
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

        Stack<Integer> list = new Stack<>();
        list.push(0);
        Integer dinhKe;
        Integer dinhHienTai = 0;
        Integer dinhKetThuc = 7;
        Integer dinhBatDau = 0;
        Boolean ketThuc = true;
        Boolean[] dinhDaDuyet = {true, true, true, true, true, true, true, true, true};
        dinhDaDuyet[dinhBatDau] = false;

        while (ketThuc) {
            if (!list.isEmpty()) {
                dinhHienTai = list.pop();
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
                    if (dinhKe == dinhKetThuc) {
                        ketThuc = false;
                    }
                    else if (!list.contains(dinhKe)) {
                        list.add(dinhKe);
                        dinhDaDuyet[i] = false;
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
    }

    private static char getChar(Integer number) {
        return (char)(number + 65);
    }
}
