package demo_01;

import javax.swing.*;
import java.net.URL;
import java.util.Objects;

/**
 * ClassName: Images
 * Description:
 * <p>
 * Datetime: 2024/10/26 13:59
 * Author: YuYuIT
 * Version: 1.0
 */
public class Images {
    public static URL topUrl = Images.class.getResource("/demo_01/images/top.png");
    public static ImageIcon top = new ImageIcon(Objects.requireNonNull(topUrl));

    public static URL bottomUrl = Images.class.getResource("/demo_01/images/bottom.png");
    public static ImageIcon bottom = new ImageIcon(Objects.requireNonNull(bottomUrl));

    public static URL leftUrl = Images.class.getResource("/demo_01/images/left.png");
    public static ImageIcon left = new ImageIcon(Objects.requireNonNull(leftUrl));

    public static URL rightUrl = Images.class.getResource("/demo_01/images/right.png");
    public static ImageIcon right = new ImageIcon(Objects.requireNonNull(rightUrl));

    public static URL bodyUrl = Images.class.getResource("/demo_01/images/body.png");
    public static ImageIcon body = new ImageIcon(Objects.requireNonNull(bodyUrl));

    public static URL foodUrl = Images.class.getResource("/demo_01/images/food.png");
    public static ImageIcon food = new ImageIcon(Objects.requireNonNull(foodUrl));

    public static URL headerUrl = Images.class.getResource("/demo_01/images/header.png");
    public static ImageIcon header = new ImageIcon(Objects.requireNonNull(headerUrl));
}
