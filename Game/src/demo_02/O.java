package demo_02;

/**
 * ClassName: O
 * Description:
 * <p>
 * Datetime: 2024/10/28 9:04
 * Author: YuYuIT
 * Version: 1.0
 */
public class O extends Tetromino{
    public O() {
        cells[0] = new Cell(0, 4, Tetris.O);
        cells[1] = new Cell(0, 5, Tetris.O);
        cells[2] = new Cell(1, 4, Tetris.O);
        cells[3] = new Cell(1, 5, Tetris.O);

        //共计有零种旋转状态
        states = new State[0];
    }
}
