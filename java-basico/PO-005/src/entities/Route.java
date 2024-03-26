package entities;

import java.util.ArrayList;
import java.util.Date;

public class Route {

    private ArrayList<Section> sections;
    private Date startRoute;
    private Date checkpoint;

    public Route() {
        super();
    }

    public Route(ArrayList<Section> sections) {
        super();
        this.sections = sections;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public Date getStartRoute() {
        return startRoute;
    }

    public void setStartRoute(Date startRoute) {
        this.startRoute = startRoute;
    }

    public Date getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(Date checkpoint) {
        this.checkpoint = checkpoint;
    }

    @Override
    public String toString() {
        // Customize the representation of the route as desired
        return "Sections: " + getSections();
    }
}
