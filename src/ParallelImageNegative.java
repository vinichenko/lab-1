import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Dima
 * Date: 16.09.13
 * Time: 11:03
 * To change this template use File | Settings | File Templates.
 */

public class ParallelImageNegative {
    public static void main(String[] args) {
        if (args.length == 2) {
            new ParallelImageNegative(Integer.parseInt(args[0]), args[1]);
        } else {
            System.out.println("Incorrect input!");
        }
    }

    public ParallelImageNegative(int threadNumber, String imagePath) {
        try {
            System.out.println("THREADS " + threadNumber);

            File file = new File(imagePath);
            BufferedImage image = ImageIO.read(file);

            int step = image.getHeight() / threadNumber;
            MyThread[] threadsList = new MyThread[threadNumber];

            Date startTime = new Date();

            for (int i = 0; i < threadNumber; i++) {
                if (i < threadNumber - 1) {
                    threadsList[i] = new MyThread(image, i * step, step);
                    threadsList[i].start();
                    threadsList[i].join();
                } else {
                    threadsList[i] = new MyThread(image, i * step, image.getHeight() - i * step);
                    threadsList[i].start();
                    threadsList[i].join();
                }
            }

            Date finishTime = new Date();
            System.out.println("TIME " + (finishTime.getTime() - startTime.getTime()) + "ms");

            String fileName = file.getName();
            int index = fileName.lastIndexOf('.');
            String outFileName = new StringBuffer(fileName).insert(index, "_neg").toString();
            File out = new File(file.getParent() + "\\" + outFileName);
            ImageIO.write(image, "jpeg", out);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
