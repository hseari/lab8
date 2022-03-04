package com.company;

import java.util.*;
import java.util.regex.*;

class Fraction {
    private int num;
    private int den;

    //первая дробь
    public Fraction(int num, int den) throws Exception {
        if (num < 0 || den <= 0) {
            throw new Exception("Некорректный параметр");
        } else {
            this.num = num;
            this.den = den;
        }
    }

    //вторая дробь
    public Fraction() {
        this.num = 1;
        this.den = 1;
    }

    //сумма
    public static String sum(int num, int den, int num2, int den2) {
        int n = num * den2 + num2 * den;
        int d = den * den2;
        int div = Just(n, d);
        n /= div;
        d /= div;
        return "Cумма: " + n + "/" + d;
    }


    //вычитание
    public static String diff(int num, int den, int num2, int den2) {
        int n = num * den2 - num2 * den;
        int d = den * den2;
        int div = Just(n, d);
        n /= div;
        d /= div;
        return "Разность: " + n + "/" + d;
    }

    //умножение
    public static String imp(int num, int den, int num2, int den2) {
        int n = num * num2;
        int d = den * den2;
        int div = Just(n, d);
        n /= div;
        d /= div;
        return "Умножение: " + n + "/" + d;
    }

    //деление
    public static String del(int num, int den, int num2, int den2) {
        int n = num * den2;
        int d = den * num2;
        int div = Just(n, d);
        n /= div;
        d /= div;
        return "Частное: " + n + "/" + d;
    }


    public String toString() {
        return num + "/" + den;
    }

    public static int Just(int a, int b) {
        do {
            if (a > b)
                a %= b;
            else b %= a;
        } while (a * b > 0);
        return a + b;
    }
}


public class Main {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);
        System.out.print("Введите выражение: ");

        String exp = in.nextLine();
        Pattern p = Pattern.compile("-?[0-9]+/-?[1-9]+([+]|-|[*]|:)-?[0-9]+/-?[1-9]+");
        Matcher m = p.matcher(exp);
        while (!exp.matches(p.pattern())) {
            System.out.println("Выражение может содержать только цифры, знаки операций и /, без пробелов.");
            System.out.print("Введите пароль еще раз: ");
            exp = in.next();
        }

        String[] frac = exp.split("([+*:-]|/)");
        int num = Integer.parseInt(frac[0].trim());
        int den = Integer.parseInt(frac[1].trim());
        int num2 = Integer.parseInt(frac[2].trim());
        int den2 = Integer.parseInt(frac[3].trim());

        if (exp.contains("+")) {
            Fraction f = new Fraction(num, den); //сложение
            System.out.println(f.sum(num, den, num2, den2));
        } else if (exp.contains("-")) {
            Fraction t = new Fraction(num, den); //вычитание
            System.out.println(t.diff(num, den, num2, den2));
        } else if (exp.contains("*")) {
            Fraction t = new Fraction(num, den); //умножение
            System.out.println(t.imp(num, den, num2, den2));
        } else if (exp.contains(":")) {
            Fraction t = new Fraction(num, den); //деление
            System.out.println(t.del(num, den, num2, den2));
        }
        in.close();
    }
}

