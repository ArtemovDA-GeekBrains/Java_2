package ru.geekbrains.java_2.hw_5;

/*
1. Необходимо написать два метода, которые делают следующее:
1) Создают одномерный длинный массив, например:

static final int size = 10000000;
static final int h = size / 2;
float[] arr = new float[size];

2) Заполняют этот массив единицами;
3) Засекают время выполнения: long a = System.currentTimeMillis();
4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
5) Проверяется время окончания метода System.currentTimeMillis();
6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);

Отличие первого метода от второго:
Первый просто бежит по массиву и вычисляет значения.
Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.

Пример деления одного массива на два:

System.arraycopy(arr, 0, a1, 0, h);
System.arraycopy(arr, h, a2, 0, h);

Пример обратной склейки:

System.arraycopy(a1, 0, arr, 0, h);
System.arraycopy(a2, 0, arr, h, h);

Примечание:
System.arraycopy() – копирует данные из одного массива в другой:
System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение, откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)
По замерам времени:
Для первого метода надо считать время только на цикл расчета:

for (int i = 0; i < size; i++) {
arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
}

Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.
*/

public class Main {

    public class MyThread extends Thread {
        float[] arr;
        int arg;

        public MyThread(float[] arr, int arg) {
            this.arr = arr;
            this.arg = arg;
        }

        @Override
        public void run() {

            for (int i = 0; i < arr.length; i++, arg++)
                arr[i] = (float) (arr[i] * Math.sin(0.2f + arg / 5) * Math.cos(0.2f + arg / 5) * Math.cos(0.4f + arg / 2));
        }
    }

    static final int size = 10000000;
    static final int h = size / 2;

    public void method_1()
    {
        //1) Создают одномерный длинный массив, например:
        float[] arr = new float[size];

        //2) Заполняют этот массив единицами;
        for (int i = 0; i < arr.length; i++) arr[i] = 1;

        //3) Засекают время выполнения:
        long a = System.currentTimeMillis();

        //4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
        for (int i = 0; i < arr.length; i++)
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

        //5) Проверяется время окончания метода System.currentTimeMillis();
        //6) В консоль выводится время работы:
        System.out.printf("Время работы метода 1 = %d\n", (System.currentTimeMillis() - a));

//      Проверка значений результирующего массива
//        for (int i = 0; i < arr.length; i++)
//            System.out.printf("%f ", arr[i]);
//        System.out.println();
    }

    public void method_2()
    {
        //1) Создают одномерный длинный массив, например:
        float[] arr = new float[size];

        //2) Заполняют этот массив единицами;
        for (int i = 0; i < arr.length; i++) arr[i] = 1;

        //3) Засекают время выполнения:
        long a = System.currentTimeMillis();

        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        //Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.
        Thread t1 = new Thread(new MyThread(a1, 0));
        Thread t2 = new Thread(new MyThread(a2, h));

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.out.printf("Время работы метода 2 = %d\n", (System.currentTimeMillis() - a));

//      Проверка значений результирующего массива
//        for (int i = 0; i < arr.length; i++)
//            System.out.printf("%f ", arr[i]);
//        System.out.println();
    }

    public static void main(String[] args) {

        Main lesson_5 = new Main();

        System.out.println("Метод 1");
        lesson_5.method_1();

        System.out.println("Метод 2");
        lesson_5.method_2();
    }
}
