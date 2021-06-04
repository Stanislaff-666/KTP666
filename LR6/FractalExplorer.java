import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.SwingWorker;
// позволит вам исследовать различные области фрактала, путем его создания, отображения через
// графический интерфейс Swing и обработки событий, вызванных
// взаимодействием приложения с пользователем.
public class FractalExplorer
{
	private int displaySize;
// для обновления отображения в разных методах в процессе вычисления фрактала
	private int rowRemaining;
	private JImageDisplay image;
// Будет использоваться ссылка на базовый класс для отображения других видов фракталов в будущем.
	private FractalGenerator fractal;
// указывающий диапазона комплексной плоскости, которая выводится на экран
	private Rectangle2D.Double range;
	public JComboBox comboBox;
	public JButton reset;
	public JButton save;
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
	public void createAndShowGUI()
	{
		Mandelbrot mandelbrot=new Mandelbrot();
		Tricorn tricorn=new Tricorn();
		BurningShip burningShip=new BurningShip();

		JFrame frame=new JFrame("Fractal Explorer");

		JPanel northpanel=new JPanel();
		JPanel southpanel=new JPanel();

		JLabel label=new JLabel("Fractal");

		
		comboBox = new JComboBox();
		comboBox.addItem(mandelbrot);
		comboBox.addItem(tricorn);
		comboBox.addItem(burningShip);

		image=new JImageDisplay(displaySize, displaySize);

		reset=new JButton("reset");
		save=new JButton("save");

		//обработчик комбобокса 
		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				JComboBox am=(JComboBox) e.getSource();
				fractal=(FractalGenerator) am.getSelectedItem();
				drawFractal();
			}
		});
		//

		//обработчик кнопки reset
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
            	fractal.getInitialRange(range);
            	drawFractal();
        	}
		});
		//

		//обработчик кнопки save
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				JButton button=(JButton) e.getSource();
            	JFileChooser fileChooser = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                fileChooser.setFileFilter(filter);
                fileChooser.setAcceptAllFileFilterUsed(false);
                if(fileChooser.showSaveDialog(button.getParent())== JFileChooser.APPROVE_OPTION)
                {
	                try 
	                {
	                    ImageIO.write(image.getBufferedImage(),"png", fileChooser.getSelectedFile());
	                } 
	                catch (IOException ex) 
	                {
	                    JOptionPane.showMessageDialog(button.getParent(), ex.getMessage(),
	                            "Cannot Save Image", JOptionPane.ERROR_MESSAGE);
	                }
            	}
        	}
		});
		//

		//рисование фрактала
		image.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
        	{
        		if (rowRemaining==0)
        		{
		            int x = e.getX();
		            int y = e.getY();
		            double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, x);
		            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, y);
		            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
		            drawFractal();

        		}
        	}
		});
		northpanel.add(label);
		northpanel.add(comboBox);
		southpanel.add(save);
		southpanel.add(reset);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(image,BorderLayout.CENTER);
        
        frame.add(southpanel, BorderLayout.SOUTH);
        frame.add(northpanel, BorderLayout.NORTH);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
//Данные операции правильно разметят содержимое окна, сделают его
//видимым и затем запретят изменение размеров окна.
	}

	private void drawFractal()
	{
        rowRemaining = displaySize; // она должна установить значение равным общему количеству строк, которые нужно нарисовать.
        enableUI(false); //отключить все элементы пользовательского интерфейса во время рисования
        for(int i = 0; i < displaySize; i++)
        {
            FractalWorker fractalWorker = new FractalWorker(i);
            //  Это действие запустит фоновый поток и запустит задачу в фоновом режиме.
            fractalWorker.execute();
        }
    }
//будет включать или отключать кнопки с выпадающим списком в пользовательском
//интерфейсе на основе указанного параметра
    private void enableUI(boolean val)
    {
        reset.setEnabled(val);
        save.setEnabled(val);
        comboBox.setEnabled(val);
    }
// FractalWorker будет отвечать за вычисление значений цвета для одной строки фрактала, поэтому ему потребуются два поля
// целочисленная yкоордината вычисляемой строки, и массив чисел типа int для хранения
//вычисленных значений RGB для каждого пикселя в этой строке
    public class FractalWorker extends SwingWorker<Object, Object>
    {
    	private int y;
    	private int[] rgb;
// Конструктор должен будет получать y-координату в качестве параметра и сохранять это
    	public FractalWorker(int y)
    	{
    		this.y=y;
    	}
//метод, который фактически выполняет фоновые
//операции. Swing вызывает этот метод в фоновом потоке, а не в потоке
//обработке событий
    	protected Object doInBackground() throws Exception
    	{
    		rgb=new int[displaySize];
	    	for(int i = 0; i < displaySize; i++)
	    	{
	               	double xCoord = FractalGenerator.getCoord(range.x,range.x + range.width, displaySize, i);
	                double yCoord = FractalGenerator.getCoord(range.y,range.y + range.height, displaySize, y);
	                int iter = fractal.numIterations(xCoord,yCoord);
	                if (iter == -1)rgb[i]=0;
                // значение цвета варьируется от 0 до 1, получается плавная последовательность цветов от
				//красного к желтому, зеленому, синему, фиолетовому и затем обратно к красному
	                else 
	                {
	                    float hue = 0.7f + (float)iter / 200f;
	                    rgb[i] =Color.HSBtoRGB(hue,1f,1f);
	                }
	            
	        }
	        return null;
    	}
//этот метод вызывается, когда фоновая задача завершена. Он
//вызывается в потоке обработки событий, поэтому данному методу разрешено
//взаимодействовать с пользовательским интерфейсом.
    	protected void done()
    	{
    		for(int i = 0; i < displaySize; i++)
    		{
                image.drawPixel(i,y,rgb[i]);
            }
            image.repaint(0,0,y,displaySize,1);
            rowRemaining--;
            if(rowRemaining == 0) enableUI(true);
    	}
    }

    
    public static void main(String[] args) 
    {
    	FractalExplorer fractalexp=new FractalExplorer(600);
    	fractalexp.createAndShowGUI();
    	fractalexp.drawFractal();
    }

    

}