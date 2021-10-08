package lesson2;

public class lesson2 {
    public static void main(String[] args) {

        //Проверка работы методов
        PolSumPrint(-1);
        MultiPrint("Hello World!", 5);
        System.out.println("NeedSum " + NeedSum(7, 3));
        System.out.println("OtrSumBool " + OtrSumBool(0));
        int year = 195;
        if (Visocos(year))
            System.out.println("Год " + year + " високосный");
        else
            System.out.println("Год " + year + " не високосный");
        //конец проверки методов
    }

    public static boolean NeedSum(int a, int b) {
        if (a + b >= 10 && a + b <= 20)
            return true;
        else
            return false;
    }

    public static void PolSumPrint(int a) {
        if (a >= 0)
            System.out.println("Число положительное");
        else
            System.out.println("Число отрицательное");
    }

    public static boolean OtrSumBool(int a) {
        if (a >= 0)
            return false;
        else
            return true;
    }

    public static void MultiPrint(String str, int n) {
        for (int i = 0; i < n; i++)
            System.out.println(str);
    }

    public static boolean Visocos(int year) {
        if (year % 400 == 0)
            return true;
        else if (year % 4 == 0 && year % 100 != 0)
            return true;
        else
            return false;
    }
}
