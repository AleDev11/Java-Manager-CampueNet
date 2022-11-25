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
        if (FileManager.checkText(name)) {
            this.name = name;
        } else {
            System.out.println("El nombre no puede contener numeros");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (FileManager.checkText(lastName)) {
            this.lastName = lastName;
        } else {
            System.out.println("El apellido no puede contener numeros");
        }
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        if (FileManager.checkNumber(String.valueOf(note))) {
            if (note >= 0 && note <= 10) {
                this.note = note;
            } else {
                System.out.println("La nota debe estar entre 0 y 10");
            }
        } else {
            System.out.println("La nota no puede contener letras");
        }
    }
}
