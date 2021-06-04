/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
/** Этот класс хранит набор открытых и закрытых вершин, и предоставляет основные операции,
 необходимые для функционирования алгоритма поиска А**/
import java.util.*;
public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;
    private Map<Location, Waypoint> open=new HashMap<Location, Waypoint>();
    private Map<Location, Waypoint> close=new HashMap<Location, Waypoint>();

    /**
     * Инициализировать новый объект состояния для использования алгоритма поиска пути A *
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Возвращает карту, по которой перемещается поисковик A *. **/
    public Map2D getMap()
    {
        return map;
    }

    /** Эта функция должна проверить все вершины в наборе открытых вершин, и после этого 
    она должна вернуть ссылку на вершину с наименьшей общей стоимостью( если в открытом наборе 
    нет вершин, функция возвращает NULL)
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint()
    {
        Waypoint minimumCostPoint = null;
        if (!open.isEmpty()){
            double miniCost = Double.MAX_VALUE;
            for(Waypoint cWaypoint: open.values())
            if (cWaypoint.getTotalCost()< miniCost){
                miniCost = cWaypoint.getTotalCost();
                minimumCostPoint = cWaypoint;
            }
        }
        return minimumCostPoint;
    }

    /**
    Он должен добавлять указанную указанную вершину только в том случае, если существующая вершина
    хуже новой. Если в наборе Опен вершин в наст время нет вершины для данного местоположения, то надо
    прост одобавить новую вершину. если есть вершина то надо добавить новую верш  том случае, если сто
    имость пути до новой вершиныв= меньше стоимости пути до текущей
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        if(open.containsKey(newWP.getLocation())){
            if(newWP.getPreviousCost() < open.get(newWP.getLocation()).getPreviousCost())
            {
                open.put(newWP.getLocation(), newWP);
                return true;
            }
            return false;
        }
        else{
            open.put(newWP.getLocation(), newWP);
            return true;
        }
    }


    /** Этот метод возвращает колво точек в наборе открытых вершин **/
    public int numOpenWaypoints()
    {
        return open.size();
    }


    /**
    Метод перемещает вершину из набора открытых верш в набор закрытых
    (dell вершину )
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)
    {
        Waypoint wp=open.remove(loc);
        close.put(loc, wp);
    }

    /**
    Метод возвращает значение тру, если указанное местоположеие встречается в наборе 
    закрытых вершин ифолс в противном случае.
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
    {
        return close.containsKey(loc);
    }
}