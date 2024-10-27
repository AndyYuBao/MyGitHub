package demo_01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * ClassName: MyPanel
 * Description:
 * <p>
 * Datetime: 2024/10/25 12:18
 * Author: YuYuIT
 * Version: 1.0
 */
public class MyPanel extends JPanel implements KeyListener, ActionListener {
    /*
    声明右侧蛇头和身体图片
    ImageIcon right = new ImageIcon("demo_01/images/right.png");
    ImageIcon body = new ImageIcon("demo_01/images/body.png");
    声明上、下、左侧蛇头图片
    ImageIcon top = new ImageIcon("demo_01/images/top.png");
    ImageIcon bottom = new ImageIcon("demo_01/images/bottom.png");
    ImageIcon left = new ImageIcon("demo_01/images/left.png");
    声明食物图片
    ImageIcon food = new ImageIcon("demo_01/images/food.png");
    */

    //声明一个初始值， 表示蛇的长度为3
    int len;
    //声明两个数组分别存放x和y坐标的位置
    int[] snakeX = new int[1024]; //最大值 = 宽度格子 * 高度格子
    int[] snakeY = new int[1024]; //最大值 = 宽度格子 * 高度格子

    //声明一个枚举类型变量，标识蛇头的方向
    Direction direction;

    //Declare a variable that marks whether the game has started or not,
    //when the value is true it means the game has started, otherwise the game has not started
    boolean isStart = false;

    //创建一个定时器对象
    Timer timer;

    //声明两个变量表示食物的坐标位置
    int foodX;
    int foodY;

    //定义一个积分
    int score;
    //加入一个变量，判断小蛇的死亡状态
    boolean isDie = false; //默认情况下小蛇没有死亡
    //声明一个随机变量random
    Random random = new Random();

    public void init() {
        //初始化蛇的长度
        len = 3;
        score = 0;
        //设定蛇的头部和身体的初始位置
        snakeX[0] = 100;
        snakeY[0] = 100;
        snakeX[1] = 75;
        snakeY[1] = 100;
        snakeX[2] = 50;
        snakeY[2] = 100;
        direction = Direction.RIGHT;
        //生成食物坐标
        foodX = (random.nextInt(30) + 1) * 25; //[25, 750]
        foodY = (random.nextInt(26) + 4) * 25; //[100, 725]
    }

    public MyPanel() {

        init();

        //Get keyboard events
        this.setFocusable(true);
        //Add a listener
        this.addKeyListener(this);

        //对定时器进行初始化动作
        timer = new Timer(100, this);
        //Start the timer
        timer.start();
    }

    //重写画组件的方法
    @Override
    protected void paintComponent(Graphics g) {
        //Call the parent class method to do some basic work
        super.paintComponent(g);
        //Set the background color
        this.setBackground(new Color(208, 220, 226));
        //画头部图片
        Images.header.paintIcon(this, g, 10, 10);
        //调节画笔颜色
        g.setColor(new Color(219, 226, 219));
        //Add a play area to the canvas
        g.fillRect(10, 70, 770, 685);

        //Add the right snake head
        //right.paintIcon(this, g, snakeX[0], snakeY[0]);
        //According to the enumerated variable direction value,
        //the judgment is made to display the snake head in which direction
        switch (direction) {
            case TOP -> Images.top.paintIcon(this, g, snakeX[0], snakeY[0]);
            case BOTTOM -> Images.bottom.paintIcon(this, g, snakeX[0], snakeY[0]);
            case LEFT -> Images.left.paintIcon(this, g, snakeX[0], snakeY[0]);
            case RIGHT -> Images.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        //添加两个身体
        for (int i = 1; i < len; i++) {
            Images.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        //The value isStart is used to determine whether the game is currently marked,
        //and if false, the message is displayed
        if (!isStart) {
            //Put on the start prompt and set the font color and font
            g.setColor(new Color(114, 98, 255));
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("PRESS THE SPACEBAR TO INDICATE THE GAME STARTS!", 100, 500);
        }
        //添加食物
        Images.food.paintIcon(this, g, foodX, foodY);
        //画积分
        g.setColor(new Color(140, 10, 10));
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("score : " + score, 620, 40);
        //画入死亡状态：
        if (isDie) {
            g.setColor(new Color(140, 10, 10));
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("The snake dies, the game stops, press the space bar to start again!", 100, 500);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        //Judging by this, the numeric value should be 32 when the spacer is pressed
        if (keyCode == KeyEvent.VK_SPACE) {
            if(isDie){
                init();
                isDie = false;
            } else{
                //The value that marks the state of the game is inverted
                isStart = !isStart;
                //Redraw the component
                repaint();
            }
        } else if (keyCode == KeyEvent.VK_UP) {
            direction = Direction.TOP;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            direction = Direction.BOTTOM;
        } else if (keyCode == KeyEvent.VK_LEFT) {
            direction = Direction.LEFT;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            direction = Direction.RIGHT;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //When isStart is true, the game starts, so move the snake
        if (isStart && !isDie) {
            //Move the body
            for (int i = len - 1; i > 0; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }
            //通过方向值direction进行判断，移动蛇头
            switch (direction) {
                case TOP -> {
                    //垂直向上移动
                    snakeY[0] -= 25;
                    if (snakeY[0] <= 100) {
                        snakeY[0] = 725;
                    }
                }
                case BOTTOM -> {
                    //垂直向下移动
                    snakeY[0] += 25;
                    if (snakeY[0] >= 725) {
                        snakeY[0] = 100;
                    }
                }
                case LEFT -> {
                    //水平向左移动
                    snakeX[0] -= 25;
                    if (snakeX[0] < 25) {
                        snakeX[0] = 750;
                    }
                }
                case RIGHT -> {
                    //If the snake moves horizontally to the right, the snake's head will have +25
                    snakeX[0] += 25;
                    //If the value of the current snake head exceeds 700, the x value starts from 0
                    if (snakeX[0] > 750) {
                        snakeX[0] = 25;
                    }
                }
            }
            //判断，当蛇头x和食物坐标x一致，并且蛇头y和食物坐标y一致，则表示吃到食物
            if (snakeX[0] == foodX && snakeY[0] == foodY) {
                //蛇的长度加1
                len++;
                //重新生成食物的坐标位置
                foodX = (random.nextInt(30) + 1) * 25; //[25, 750]
                foodY = (random.nextInt(26) + 4) * 25; //[100, 725]
                score += 10;
            }
            //死亡判定
            for (int i = 1; i < len; i++) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    //将死亡状态改为true
                    isDie = !isDie;
                }
            }
            //Redraw the component method
            repaint();
            //Restart the timer
            timer.start();
        }
    }
}


























