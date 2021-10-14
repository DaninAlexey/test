package lesson4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static int size;
    private static int dotWins;
    private static final char DOT_EMPTY = '•';
    private static final char DOT_X = 'X';
    private static final char DOT_0 = 'O';
    private static char[][] map;
    public static Scanner scan = new Scanner(System.in);
    public static Random rand = new Random();
    private static int bestX;
    private static int bestY;

    public static void main(String[] args) {
        initSizeManual();
        if (size == 3)
            dotWins = 3;
        else
            initDotWinsManual();
        System.out.printf("Размер равен %d, Фишек для победы %d \n\n", size, dotWins);
        initMap();
        do {
            printMap();
            humanTurn();
            if (checkWin(DOT_X)) {
                printMap();
                System.out.println("Человек победил");
                break;
            }
            if (isMapFull()) {
                printMap();
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            if (checkWin(DOT_0)) {
                printMap();
                System.out.println("Победил компьютер");
                break;
            }
            if (isMapFull()) {
                printMap();
                System.out.println("Ничья");
                break;
            }
        }
        while (true);
        System.out.println("Игра окончена");
    }

    private static void initSizeManual() {
        do {
            System.out.println("Введите размер карты, от 3 до 9");
            testScanInt();
            size = scan.nextInt();
            if (testnoNeedInt(size, 3, 9))
                System.out.println("Не верное значение размера карты, пожалуйста повторите ввод");
        } while (testnoNeedInt(size, 3, 9));
        System.out.println("Размер равен " + size);
    }

    private static void initDotWinsManual() {
        do {
            System.out.printf("Введите количество фишек для победы, от 3 до %d\n", Math.min(5, size));
            testScanInt();
            dotWins = scan.nextInt();
            if (testnoNeedInt(dotWins, 3, Math.min(5, size)))
                System.out.println("Не верное значение количества фишек для победы, пожалуйста повторите ввод");
        } while (testnoNeedInt(dotWins, 3, Math.min(5, size)));

    }

    private static void testScanInt() {
        while (!scan.hasNextInt()) {
            System.out.println("Вы ввели не число, пожалуйста повторите ввод");
            scan.next();
        }
    }

    private static boolean testnoNeedInt(int num, int mini, int maxi) {
        return num < mini || num > maxi;
    }


    private static void initMap() {
        map = new char[size][size];
        for (int i = 0; i < size; i++)
            Arrays.fill(map[i], DOT_EMPTY);
    }

    private static void printMap() {
        for (int i = 0; i <= size; i++)
            System.out.print(i + " ");
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < size; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            testScanInt();
            x = scan.nextInt() - 1;
            testScanInt();
            y = scan.nextInt() - 1;
            if (testnoNeedInt(x, 0, size - 1) || testnoNeedInt(y, 0, size - 1))
                System.out.println("Координаты выходят за рамки карты, пожалуйста повторите ввод");
            else if (noFreeCell(x, y))
                System.out.println("Координаты заняты, пожалуйста повторите ввод");
        } while (testnoNeedInt(x, 0, size - 1) || testnoNeedInt(y, 0, size - 1) || noFreeCell(x, y));
        map[y][x] = DOT_X;
    }

    private static boolean noFreeCell(int x, int y) {
        return map[y][x] != DOT_EMPTY;
    }

    private static boolean isMapFull() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (map[i][j] == DOT_EMPTY)
                    return false;
        return true;
    }

    private static boolean checkWin(char symbol) {
        int check1, check2, check3, check4;
        //Проверка победы по строкам и столбцам
        for (int i = 0; i < size; i++) {
            check1 = 0;
            check2 = 0;
            for (int j = 0; j < size; j++) {
                //Проверка победы по строкам
                if (map[i][j] == symbol) {
                    check1++;
                    if (check1 == dotWins)
                        return true;
                } else
                    check1 = 0;
                //Проверка победы по столбцам
                if (map[j][i] == symbol) {
                    check2++;
                    if (check2 == dotWins)
                        return true;
                } else
                    check2 = 0;
            }
        }
        //Проверка победы по диагоналям
        for (int i = 0; i <= size - dotWins; i++) {
            check1 = 0;
            check2 = 0;
            check3 = 0;
            check4 = 0;
            for (int j = 0; j < size - i; j++) {

                //Проверка победы по прямым диагоналям от главной в правую сторону
                if (map[i + j][j] == symbol) {
                    check1++;
                    if (check1 == dotWins)
                        return true;
                } else
                    check1 = 0;

                //Проверка победы по прямым диагоналям от главной вниз
                //К сожалению главные диагонали проверяются дважды
                if (map[j][j + i] == symbol) {
                    check2++;
                    if (check2 == dotWins)
                        return true;
                } else
                    check2 = 0;

                //Проверка победы по обратной диагонали от главной в правую сторону
                if (map[size - 1 - j - i][j] == symbol) {
                    check3++;
                    if (check3 == dotWins)
                        return true;
                } else
                    check3 = 0;

                //Проверка победы по обратной диагонали от главной вверх
                if (map[size - 1 - j][j + i] == symbol) {
                    check4++;
                    if (check4 == dotWins)
                        return true;
                } else
                    check4 = 0;
            }
        }
        return false;
    }

    private static void aiTurn() {
        if (searchBestMove()) {
            map[bestX][bestY] = DOT_0;
            System.out.printf("Компьютер походил в точку %d - %d\n\n", bestY + 1, bestX + 1);
        } else
            randomMove();
    }

    // В этом методе расписан поиск лучшего хода. Возвращает true если ход найден и false если нет
    // Координаты лучшего хода хранятся в полях bestX b bestY
    private static boolean searchBestMove() {
        if (searchOneTurnWin(DOT_0))
            return true;
        if (searchOneTurnWin(DOT_X))
            return true;
        if (searchTwoTurnWin(DOT_0))
            return true;
        return searchTwoTurnWin(DOT_X);
    }

    // метод проверяет есть ли победа если поставить symb по координатам x-y
    // перед вызовом обязательна проверка на то что координаты x-y пусты.
    private static boolean oneTurnWin(int x, int y, char symb) {
        map[x][y] = symb;
        if (checkWin(symb)) {
            map[x][y] = DOT_EMPTY;
            return true;
        } else {
            map[x][y] = DOT_EMPTY;
            return false;
        }
    }

    // метод перебирает все возможные ходы и проверяет есть ли победа в один ход
    private static boolean searchOneTurnWin(char symb) {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (map[i][j] == DOT_EMPTY && oneTurnWin(i, j, symb)) {
                    bestX = i;
                    bestY = j;
                    return true;
                }
        return false;
    }

    //Проверка победы в два хода по координатам x-y. Для этого после первого хода должно быть как минимум 2 выигрывающих в 1 ход продолжения.
    //Соперник не сможет заблокировать оба.
    private static boolean twoTurnWin(int x, int y, char symb) {
        int check = 0;
        map[x][y] = symb;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                if (map[i][j] == DOT_EMPTY && oneTurnWin(i, j, symb)) {
                    check++;
                    if (check == 2) {
                        map[x][y] = DOT_EMPTY;
                        return true;
                    }
                }
            }
        map[x][y] = DOT_EMPTY;
        return false;
    }


    // Перебор всех возможных ходов. Поиск победы в 2 хода.
    private static boolean searchTwoTurnWin(char symb){
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (map[i][j] == DOT_EMPTY && twoTurnWin(i, j, symb)) {
                    bestX = i;
                    bestY = j;
                    return true;
                }
        return false;
    }

    // метод делает случайный ход ИИ
    private static void randomMove() {
        int x;
        int y;
        do {
            x = rand.nextInt(size);
            y = rand.nextInt(size);
        } while (noFreeCell(x, y));
        System.out.printf("Компьютер походил в точку %d - %d\n", x + 1, y + 1);
        System.out.println("Не знаю куда ходить");
        map[y][x] = DOT_0;
    }
}