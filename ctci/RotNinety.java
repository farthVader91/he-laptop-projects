import java.util.*;

public class RotNinety {
    public static void swap(int[][] arr, int x1, int y1, int x2, int y2) {
        int tmp = arr[x1][y1];
        arr[x1][y1] = arr[x2][y2];
        arr[x2][y2] = tmp;
    }

    public static void rotate(int[][] arr) {
        int max, n = arr.length;
        for (int i = 0; i < n; i++) {
            max = n - i - 1;
            swap(arr, 0, max, n - 1, max);
            for(int j = 0; j < max; j++)
                swap(arr, i, j, j, max);
        }
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(arr);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++)
                System.out.print(arr[i][j] + " ");
            System.out.println();
        }
    }
}
