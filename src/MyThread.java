import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: Dima
 * Date: 16.09.13
 * Time: 11:12
 * To change this template use File | Settings | File Templates.
 */

public class MyThread extends Thread {
    private BufferedImage image;
    private int startRow;
    private int rowNumber;

    public MyThread(BufferedImage image, int startRow, int rowNumber) {
        this.startRow = startRow;
        this.rowNumber = rowNumber;
        this.image = image;
    }

    @Override
    public void run() {
        int rgb;
        Color color;
        for (int j = this.startRow; j < this.startRow + this.rowNumber; j++) {
            for (int i = 0; i < this.image.getWidth(); i++) {
                rgb = image.getRGB(i, j);
                color = new Color(rgb, true);
                color = new Color(255 - color.getRed(),
                        255 - color.getGreen(),
                        255 - color.getBlue());
                image.setRGB(i, j, color.getRGB());
            }
        }
    }
}
