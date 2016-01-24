package pl.poznan.put.putnav;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

@DatabaseTable(tableName = "MapPoints")

public class MapPoint implements Comparable<MapPoint> {

    @DatabaseField(id = true)
    private int Id;
    @DatabaseField
    private int X;
    @DatabaseField
    private int Y;
    @DatabaseField
    private int Type;
    //@DatabaseField (foreign = true)
    private Map map;
    //@DatabaseField (foreign = true)
    private Building building;
    //@DatabaseField (foreign = true)
    private Room room;

    private double distance;
    private MapPoint previous;

    private ArrayList<MapPoint> successors = new ArrayList<MapPoint>();	  // lista nastepnikow
    private ArrayList<MapPointsArcs> edges = new ArrayList<MapPointsArcs>(); //lista krawedzi wychodzacych z danego wierzcholka

    @Override
    public int compareTo(MapPoint another) {
        return Double.compare(distance, another.distance);
    }

    public MapPoint() {
        this.distance = Double.longBitsToDouble(0x7ff0000000000000L);
    }

    public MapPoint(int id, int x, int y, int type) {
        this.X = x;
        this.Y = y;
        this.Type = type;
        this.distance = Double.longBitsToDouble(0x7ff0000000000000L);
    }

    void searchEdges(ArrayList<MapPointsArcs> allEdges) {
        ArrayList<MapPointsArcs> edges = new ArrayList<>();
        for (MapPointsArcs current : allEdges) {
            if (current.getPoint1().getId() == this.getId() || current.getPoint2().getId() == this.getId())
                edges.add(current);
        }

        this.edges =  edges;
    }

    //po co zwracamy?
    public MapPoint addSuccessor(MapPoint mapPoint) {
        successors.add(mapPoint);
        return mapPoint;
    }

    public MapPoint findPointById(ArrayList<MapPoint> points, int id) {
        MapPoint x = null;
        for (MapPoint m : points) {
            if (m.getId() == id) x = m;
        }
        return x;
    }

    public void removeSuccessor(MapPoint mapPoint) {
        successors.remove(mapPoint);
    }

    public void addEdge(MapPointsArcs edge) {
        edges.add(edge);
    }

    public void removeEdge(MapPointsArcs edge) {
        edges.remove(edge);
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        this.X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        this.Y = y;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        this.Type = type;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public MapPoint getPrevious() {
        return this.previous;
    }

    public void setPrevious(MapPoint previos) {
        this.previous = previos;
    }

    public void move(int x, int y) {
        setX(x);
        setY(y);
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ArrayList<MapPoint> getSuccessors() {
        return successors;
    }

    public void setSuccessors(ArrayList<MapPoint> successors) {
        this.successors = successors;
    }

    public ArrayList<MapPointsArcs> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<MapPointsArcs> edges) {
        this.edges = edges;
    }

    public void addSimpleEdge(MapPointsArcs arc) {
        edges.add(arc);
    }
}
