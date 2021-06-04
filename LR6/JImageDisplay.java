import javax.swing.*;
import javax.swing.JComponent;
import java.awt.*;
import java.awt.image.BufferedImage;
class JImageDisplay extends JComponent
{
    // процесс - совокупность кода и данных разделяющих общее виртульное адресное пространство.хр.
    //пт-это одна единица исполняемого кода
//  управляет изображением, содержимое которого можно записать.
	public BufferedImage image;
	public JImageDisplay(int width, int heigth)
	{
		image=new BufferedImage(width, heigth,BufferedImage.TYPE_INT_RGB);
		super.setPreferredSize(new Dimension(width, heigth));

	}
// компонент просто выводит на экран данные изображения
	public void paintComponent(Graphics g)
	{
		g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
	}
	public void clearImage()// устанавливает все пиксели изображения в черный цвет 
    {
        for(int i = 0; i < image.getHeight(); i++)
        {
            for(int j = 0; i < image.getWidth(); i++)
            {
                image.setRGB(j,i,0);
            }
        }
    }
// устанавливает пиксель в определенный цвет
    public void drawPixel(int x, int y, int rgbColor)
    {
        image.setRGB(x, y, rgbColor);
    }
    public BufferedImage getBufferedImage()
    {
        return image;
    }

}