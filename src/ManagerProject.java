import java.util.Scanner;

public class ManagerProject {
    static XmlManager xmlManager = new XmlManager();

    public static void AddStudent() {
        String _name = "";
        String _lastName = "";
        String _asignatura = "";
        int _note = 0;
        boolean asignaturaExits = false;

        Scanner scanner = new Scanner(System.in);

        System.out.println("[SYSTEM] Agregar Alumno");

        do {
            System.out.print("[SYSTEM] Ingrese la asignatura: ");
            _asignatura = scanner.nextLine();

            if (xmlManager.CheckAsignatura(_asignatura.toLowerCase())) {
                asignaturaExits = true;
                break;
            } else {
                System.out.println("[SYSTEM] La asignatura no existe creala primero");
            }

        } while (_asignatura.isEmpty());

        if (asignaturaExits) {
            do {
                System.out.print("[SYSTEM] Ingrese el nombre del alumno: ");
                _name = scanner.nextLine();
            } while (!Main.fileManager.checkText(_name.toLowerCase()));


            do {
                System.out.print("[SYSTEM] Ingrese el apellido del alumno: ");
                _lastName = scanner.nextLine();
            } while (!Main.fileManager.checkText(_lastName.toLowerCase()));

            do {
                System.out.print("[SYSTEM] Ingrese la nota del alumno: ");
                _note = scanner.nextInt();
            } while (!Main.fileManager.checkNumber(_note) && _note > 0 && _note < 10);

            if (xmlManager.CheckAlumno(_name.toLowerCase(), _lastName.toLowerCase(), _asignatura.toLowerCase())) {
                System.out.println("[SYSTEM] El alumno ya existe en la asignatura");
            } else {
                xmlManager.AddStudent(_asignatura.toLowerCase(), _name.toLowerCase(), _lastName.toLowerCase(), _note);
                System.out.println("[SYSTEM] Alumno agregado correctamente");
            }
        }
    }

    public static void AddAsignatura() {
        String _asignatura = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("[SYSTEM] Agregar Asignatura");

        do {
            System.out.print("[SYSTEM] Ingrese la asignatura: ");
            _asignatura = scanner.nextLine();

            if (xmlManager.CheckAsignatura(_asignatura.toLowerCase())) {
                System.out.println("[SYSTEM] La asignatura ya existe no se puede agregar de nuevo");
            } else {
                xmlManager.CreateAsignatura(_asignatura.toLowerCase());
                System.out.println("[SYSTEM] Asignatura agregada correctamente");
            }
        } while (_asignatura.isEmpty());

    }

    public static void ShowAsignaturas() {
        xmlManager.ShowAsignaturas();
    }

    public static void ShowStudents() {
        xmlManager.ShowStudents();
    }

    public static void SearchStudent() {
        String _name = "";

        Scanner scanner = new Scanner(System.in);

        System.out.println("[SYSTEM] Buscar Alumno");

        do {
            System.out.print("[SYSTEM] Ingrese el nombre del alumno: ");
            _name = scanner.nextLine();
        } while (!Main.fileManager.checkText(_name.toLowerCase()));

        xmlManager.SearchStudent(_name.toLowerCase());
    }
}
