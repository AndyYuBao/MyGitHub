package demo_01;

import javax.swing.*;
import java.awt.*;

/**
 * ClassName: MySnake
 * Description:
 * <p>      Here's a little game: Snake
 * Datetime: 2024/10/25 11:30
 * Author: YuYuIT
 * Version: 1.0
 */
public class MySnake {
    public static void main(String[] args) {
        //Create a window
        JFrame frame = new JFrame();
        //给窗体设置一个标题
        frame.setTitle("Snakes by YuYuIT");
        //Specify the position of the windows x and y, as well as the width and height values of the window
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        frame.setBounds((width - 800) / 2, (height - 800) / 2, 800, 800);
        //不允许拖拽改变大小
        frame.setResizable(false);
        //当点击窗口关闭按钮，执行操作是退出
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //添加画布
        frame.add(new MyPanel());
        //显示出来
        frame.setVisible(true);
    }
}
