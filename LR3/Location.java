
/** этот класс представляет координаты конкретной ячейки на карте**/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Создает новое местоположение с указанными целыми координатами **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /**Создает новое местоположение с координатами (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    @Override
    public boolean equals(Object a)
    {
        if(a instanceof Location)
        {
            return ((((Location) a).xCoord==this.xCoord) && (((Location) a).yCoord==this.yCoord)) ? true : false;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return 37*(37 + this.xCoord)+this.yCoord;
    }
}