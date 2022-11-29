import java.util.Scanner;

public class ManagerProject {
    public static void AddStudent() {
        String _name = "";
        String _lastName = "";
        int _note = 0;
        boolean isNumber = false;

        Scanner scanner = new Scanner(System.in);

        System.out.println("[SYSTEM] Agregar Alumno");

        do {
            System.out.print("[SYSTEM] Ingrese el nombre del alumno: ");
            _name = scanner.nextLine();
        } while (!Main.fileManager.checkText(_name));


        do {
            System.out.print("[SYSTEM] Ingrese el apellido del alumno: ");
            _lastName = scanner.nextLine();
        } while (!Main.fileManager.checkText(_lastName));

        do {
            System.out.print("[SYSTEM] Ingrese la nota del alumno: ");
            _note = scanner.nextInt();
        } while (!Main.fileManager.checkNumber(_note) && _note >= 0 && _note <= 10);

        Main.alumno.setName(_name);
        Main.alumno.setLastName(_lastName);
        Main.alumno.setNote(Integer.parseInt(String.valueOf(_note)));
        System.out.println("[SYSTEM] Alumno agregado correctamente");

        /*System.out.println("==================================");
        System.out.println("Nombre: " + Main.alumno.getName());
        System.out.println("Apellido: " + Main.alumno.getLastName());
        System.out.println("Nota: " + Main.alumno.getNote());
        System.out.println("==================================");*/
    }
}
