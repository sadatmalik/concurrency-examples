package streamapi;

/**
 * @author sm@creativefusion.net
 */
public class Student {
    private String name;
    private boolean local;

    public Student() {
    }

    public Student(String name, boolean local) {
        this.name = name;
        this.local = local;
    }

    public boolean isLocal() {
        return local;
    }

    public String getName() {
        return name;
    }
}
