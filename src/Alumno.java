public class Alumno {
    private String name;
    private String lastName;
    private int note;

    public Alumno(String name, String lastName, int note) {
        this.name = name;
        this.lastName = lastName;
        this.note = note;
    }

    public Alumno() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
