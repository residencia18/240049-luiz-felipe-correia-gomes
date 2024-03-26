package entities;

public class Section {

    private String section;
    private int interval;

    public Section(String section, int interval) {
        super();
        this.section = section;
        this.interval = interval;
    }

    public String getSection() {
        return section;
    }

    public int getInterval() {
        return interval;
    }

    @Override
    public String toString() {
        return "Section: " + getSection() + "| Estimated interval: " + getInterval() + " minutes";
    }
}
