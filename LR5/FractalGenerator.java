/**
 * This class provides the common interface and operations for fractal
 * generators that can be viewed in the Fractal Explorer.
 */
// Этот класс предоставляет общий интерфейс и операции для фракталов. Также данный класс предоставляет 
//некоторые полезные операции для перевода из экранных координат в систему
// координат вычисляемого фрактала.
import java.awt.geom.Rectangle2D;
public abstract class FractalGenerator {

    /**
     * This static helper function takes an integer coordinate and converts it
     * into a double-precision value corresponding to a specific range.  It is
     * used to convert pixel coordinates into double-precision values for
     * computing fractals, etc.
     *
     * @param rangeMin the minimum value of the floating-point range
     * @param rangeMax the maximum value of the floating-point range
     *
     * @param size the size of the dimension that the pixel coordinate is from.
     *        For example, this might be the image width, or the image height.
     *
     * @param coord the coordinate to compute the double-precision value for.
     *        The coordinate should fall in the range [0, size].
     */
    public static double getCoord(double rangeMin, double rangeMax,
        int size, int coord) {

        assert size > 0;
        assert coord >= 0 && coord < size;

        double range = rangeMax - rangeMin;
        return rangeMin + (range * (double) coord / (double) size);
    }


    /**
     * Sets the specified rectangle to contain the initial range suitable for
     * the fractal being generated.
     */
  //метод позволяет генератору  фракталов определить наиболее «интересную» область комплексной плоскости
//для конкретного фрактала.
    public abstract void getInitialRange(Rectangle2D.Double range);

//Обновляет текущий диапазон для его центрирования по указанным координатам 
//и для увеличения или уменьшения с указанным коэффициентом масштабирования. 
    public void recenterAndZoomRange(Rectangle2D.Double range,
        double centerX, double centerY, double scale) {

        double newWidth = range.width * scale;
        double newHeight = range.height * scale;

        range.x = centerX - newWidth / 2;
        range.y = centerY - newHeight / 2;
        range.width = newWidth;
        range.height = newHeight;
    }


    /**
     * Given a coordinate <em>x</em> + <em>iy</em> in the complex plane,
     * computes and returns the number of iterations before the fractal
     * function escapes the bounding area for that point.  A point that
     * doesn't escape before the iteration limit is reached is indicated
     * with a result of -1.
     */
    //реализует итеративную функцию для фрактала.
    public abstract int numIterations(double x, double y);
}
