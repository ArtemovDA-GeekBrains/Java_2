package ru.geekbrains.java_2.lesson_1;

import javax.swing.*;
import java.awt.*;

public abstract class Back extends JFrame {

    static float sumTime = 0;
    static final float CHANGE_TIME_SEC = 1f; //время через которое будет меняться фон (1 раз в секунду чтобы в глазах не рябило)

    public static void change(GameCanvas canvas, Graphics g, float deltaTime) {

        sumTime += deltaTime;

        if (sumTime >= CHANGE_TIME_SEC) {
            sumTime = 0f;

            Color color = new Color(
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255)
            );
            canvas.setBackground(color);
        }
    }
}
