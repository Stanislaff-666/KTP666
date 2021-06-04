import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
public class FractalExplorer
// позволит вам исследовать различные области фрактала, путем его создания, отображения через
// графический интерфейс Swing и обработки событий, вызванных
// взаимодействием приложения с пользователем.
{
	private int displaySize;
	// для обновления отображения в разных методах в процессе вычисления фрактала
	private JImageDisplay image;
	// Будет использоваться ссылка на базовый класс для отображения других видов фракталов в будущем.
	private FractalGenerator fractal;
	// указывающий диапазона комплексной плоскости, которая выводится на экран
	private Rectangle2D.Double range;
// конструктор, который принимает значение
//размера отображения в качестве аргумента, затем сохраняет это значение в
//соответствующем поле, а также инициализирует объекты диапазона и
//фрактального генератора. 
	public FractalExplorer(int displaySize)
	{
		this.displaySize=displaySize;
		this.fractal = new Mandelbrot();
        this.range = new Rectangle2D.Double(0, 0, 0, 0);
        fractal.getInitialRange(this.range);
	}
// инициализирует графический интерфейс
	public void createAndShowGUI()
	{
		JFrame frame=new JFrame("Fractal Explorer");
		image=new JImageDisplay(displaySize, displaySize);
		JButton reset=new JButton("reset");
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
            	fractal.getInitialRange(range);
            	drawFractal();
        	}
		});
		image.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
        	{
	            int x = e.getX();
	            int y = e.getY();
	            double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, x);
	            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, y);
	            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
	            drawFractal();
        	}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(image,BorderLayout.CENTER);
        frame.add(reset,BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
//Данные операции правильно разметят содержимое окна, сделают его
//видимым и затем запретят изменение размеров окна.
	}
	//Этот метод должен циклически проходить через каждый пиксель в отображении
	private void drawFractal(){
        for(int i = 0; i < displaySize; i++){
            for(int j = 0; j < displaySize; j++){
                double xCoord = FractalGenerator.getCoord(range.x,range.x + range.width, displaySize, i);
                double yCoord = FractalGenerator.getCoord(range.y,range.y + range.height, displaySize, j);
                int iter = fractal.numIterations(xCoord,yCoord);
                // Если число итераций равно -1 (т.е. точка не выходит за границы,
				//установите пиксель в черный цвет (для rgb значение 0)
                if (iter == -1)image.drawPixel(i,j,0);
                // значение цвета варьируется от 0 до 1, получается плавная последовательность цветов от
				//красного к желтому, зеленому, синему, фиолетовому и затем обратно к красному
                else {
                    float hue = 0.7f + (float)iter / 200f;
                    image.drawPixel(i,j,Color.HSBtoRGB(hue,1f,1f));
                }
            }
        }
        image.repaint();
    }
    public static void main(String[] args) 
    {
    	FractalExplorer fractalexp=new FractalExplorer(750);
    	fractalexp.createAndShowGUI();
    	fractalexp.drawFractal();


    }

}