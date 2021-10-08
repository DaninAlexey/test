package lesoon3;

import java.util.Arrays;

public class lesson3 {
    public static void main(String[] args) {
//  Здесь и далее закоментированны строки созданые для проверки правильности работы методов
//        FirstdzRun();
//        SeconddzRun();
//        ThirddzRun();
//        FourthdzRun(12)
//        int[] arr = FifthdzRun(12, 98);
//        int[] arr = {-3, -7, -2, 0, -4, -16};
//        SixthdzRun(arr);
//        boolean res;
//        res = SeventhdzAllRun(arr);
//        res = SeventhdzPolRun(arr);
//        System.out.println(res);
//        arr = EighthdzRun(arr, -9);
//        System.out.println(Arrays.toString(arr));
    }

    public static void FirstdzRun() {
        int[] arr = {0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0)
                arr[i] = 1;
            else arr[i] = 0;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void SeconddzRun() {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void ThirddzRun() {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr.length; i++)
            if (arr[i] < 6)
                arr[i] *= 2;
        System.out.println(Arrays.toString(arr));
    }

    public static void FourthdzRun(int size) {
        int[][] arr = new int[size][size];
        for (int i = 0; i < arr.length; i++) {
            arr[i][i] = 1;
            arr[i][size - 1 - i] = 1;
        }
        for (int j = 0; j < arr.length; j++)
            System.out.println(Arrays.toString(arr[j]));
    }

    public static int[] FifthdzRun(int len, int initialValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++)
            arr[i] = initialValue;
        return arr;
    }

    public static void SixthdzRun(int[] arr) {
        int maxn = arr[0];
        int minn = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxn)
                maxn = arr[i];
            if (arr[i] < minn)
                minn = arr[i];
        }
        System.out.println("Минимальное число = " + minn);
        System.out.println("Максимальное число = " + maxn);
    }

    //К седьмому заданию есть вопрос к условию. Могут ли элементы массива быть отрицательными? В примерах таких нет.
    //На всякий случай написал два метода.

    // Первый только для положительных значений массвива, но выполняется всего за один цикл.
    public static boolean SeventhdzPolRun(int[] arr) {
        int leftpoz = 0;
        int rightpoz = arr.length - 1;
        int leftsum = 0;
        int rightsum = 0;
//   Прохожу элементы массив не по порядку, а начиная с краев и заканчивая в центре.
//   Пользуюсь тем, что при отсутствии отрицательных значений суммы справа и слева могут только расти.
//   Увеличиваю меньшую.
        for (int i = 0; i < arr.length; i++) {
            if (rightsum > leftsum) {
                leftsum += arr[leftpoz];
                leftpoz++;
            } else {
                rightsum += arr[rightpoz];
                rightpoz--;
            }
        }
        return (leftsum == rightsum);
    }

    //Вторрой вариант для любых целых значений, но за 2 цикла.
    public static boolean SeventhdzAllRun(int[] arr) {
        //
        int sum = 0;
        //Считаем сумму всех элементов массива
        for (int val : arr) {
            sum += val;
        }
        System.out.println("сумма равняется " + sum);
        //Следущая проверка не обязательна, но помогает в некоторых вариантах избежать выполнение второго цикла.
        if (sum % 2 != 0)
            return false;
        int sum1 = 0;
        //Полный перебор возможных сумм элементов массива с левой стороны. Ищем ту которая равна половине общей суммы.
        for (int i = 0; i < arr.length - 1; i++) {
            sum1 += arr[i];
            if (sum == sum1 * 2)
                return true;
        }
        //Если полным перебором ничего найти не удалось значит нужной суммы нет.
        return false;
    }


    public static int[] EighthdzRun(int[] arr, int n) {
        // На случай когда сдигаем на большее число позиций чем длина массива
        n %= arr.length;
        // Дальше избавляемся от отрицательных значений n
        if (n < 0)
            n += arr.length;
        int temp;
        // Проверяю в какую сторону двигать быстрее
        if (arr.length > n * 2)
            // сдвиг элементов вправо на n позиций.
            for (int i = 0; i < n; i++) {
                temp = arr[arr.length - 1];
                for (int j = arr.length - 1; j > 0; j--)
                    arr[j] = arr[j - 1];
                arr[0] = temp;
            }
        else
            //Сдвиг элементов влево
            for (int i = 0; i < arr.length - n; i++) {
                temp = arr[0];
                for (int j = 1; j < arr.length; j++)
                    arr[j - 1] = arr[j];
                arr[arr.length - 1] = temp;
            }
        return arr;
    }

}
