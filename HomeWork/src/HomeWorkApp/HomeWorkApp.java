package HomeWorkApp;

public class HomeWorkApp {
        public static void main(String[] args) {
            printThreeWords();
            checkSumSign();
            printColor();
            compareNumbers();
        }

        public static void printThreeWords()
        {
            System.out.print("Orange\nBanana\nApple\n");
        }

        public static void checkSumSign()
        {
            int a,b;
            a=3; b=-5;
            if (a+b>=0)
                System.out.println("Сумма положительная");
            else
                System.out.println("Сумма отрицательная");
        }

        public static void printColor()
        {
            int value=100;
            if (value<=0)
                System.out.println("Красный");
            else if (value<=100)
                System.out.println("Желтый");
            else
                System.out.println("Зеленый");
        }

        public static void compareNumbers()
        {
            int a=12;
            int b=15;
            if (a>=b)
                System.out.println(a+">="+b);
            else
                System.out.println(a+"<"+b);
        }
    }
