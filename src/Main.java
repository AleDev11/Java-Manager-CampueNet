import config.Settings;

import java.io.File;
import java.util.Scanner;

public class Main {

    static FileManager fileManager = new FileManager();
    static Settings settings = new Settings();
    static Scanner scanner = new Scanner(System.in);

    static ManagerProject managerProject = new ManagerProject();

    public static void main(String[] args) {
        settings.CreateConfigFile();
        fileManager.checkFolder(settings.PATH_DB_FOLDER);
        fileManager.checkFile(settings.PATH_DB_FILE);

        boolean isLogin = false;
        int intents = 2;

        System.out.println("[SYSTEM] Bienvenido al sistema de gestion de alumnos para poder continuar debe iniciar sesion");
        while (!isLogin) {
            System.out.println("[SYSTEM] Ingrese su usuario: ");
            String user = scanner.nextLine();
            System.out.println("[SYSTEM] Ingrese su contraseña: ");
            String password = scanner.nextLine();

            if (settings.LoadConfigFile(user, password)) {
                isLogin = true;
                MenuSeletorOpcion();
                System.out.println("[SYSTEM] Bienvenido " + user);
                break;
            } else {
                if (intents == 0) {
                    System.out.println("[SYSTEM] Ha superado el numero de intentos");
                    break;
                } else {
                    intents--;
                    System.out.println("[SYSTEM] Usuario o contraseña incorrectos, le quedan " + intents + " intentos");
                }
            }
        }
    }

    public static void MenuSeletorOpcion() {
        boolean isMenu = false;

        while(!isMenu) {
            System.out.println("1 | Agregar Alumno");
            System.out.println("2 | Mostrar Alumnos");
            System.out.println("3 | Buscar Alumno por Nombre y apellido");
            System.out.println("4 | Mostrar todas las asignaturas");
            System.out.println("5 | Crear asignatura");
            System.out.println("6 | Salir");
            System.out.print("[SYSTEM] Ingrese una opcion: ");
            try {
                String option = scanner.nextLine();
                switch (option) {
                    case "1":
                        managerProject.AddStudent();
                        break;
                    case "2":
                        managerProject.ShowStudents();
                        break;
                    case "3":
                        managerProject.SearchStudent();
                        break;
                    case "4":
                        managerProject.ShowAsignaturas();
                        break;
                    case "5":
                        managerProject.AddAsignatura();
                        break;
                    case "6":
                        isMenu = true;
                        break;
                    default:
                        System.out.println("[SYSTEM] Opcion incorrecta");
                        break;
                }
            } catch (Exception e) {
                isMenu = false;
                System.out.println("[SERVER] Error: " + e.getMessage());
            }
        }
    }
}
