import config.Settings;

import java.io.File;
import java.util.Scanner;

public class Main {

    static FileManager fileManager = new FileManager();
    static Settings settings = new Settings();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        settings.CreateConfigFile();
        fileManager.checkFolder(settings.PATH_DB_FOLDER);
        fileManager.checkFile(settings.PATH_DB_FILE);

        boolean isLogin = false;
        int intents = 2;

        System.out.println("Bienvenido al sistema de gestion de alumnos para poder continuar debe iniciar sesion");
        while (!isLogin) {
            System.out.println("Ingrese su usuario: ");
            String user = scanner.nextLine();
            System.out.println("Ingrese su contraseña: ");
            String password = scanner.nextLine();

            if (settings.LoadConfigFile(user, password)) {
                isLogin = true;
                MenuSeletorOpcion();
                System.out.println("Bienvenido " + user);
            } else {
                if (intents == 0) {
                    System.out.println("Ha superado el numero de intentos");
                    break;
                } else {
                    intents--;
                    System.out.println("Usuario o contraseña incorrectos, le quedan " + intents + " intentos");
                }
            }
        }
    }

    public static void MenuSeletorOpcion() {
        boolean isMenu = false;

        while(!isMenu) {
            System.out.println("1 | Agregar Alumno");
            System.out.println("2 | Mostrar Alumnos");
            System.out.println("3 | Buscar Alumno por Nombre");
            System.out.println("4 | Salir");
            System.out.print("Ingrese una opcion: ");
            try {
                String option = scanner.nextLine();
                switch (option) {
                    case "1":
                        System.out.println("Agregar Alumno");
                        break;
                    case "2":
                        System.out.println("Mostrar Alumnos");
                        break;
                    case "3":
                        System.out.println("Buscar Alumno por Nombre");
                        break;
                    case "4":
                        isMenu = true;
                        break;
                    default:
                        System.out.println("Opcion incorrecta");
                        break;
                }
            } catch (Exception e) {
                isMenu = false;
                System.out.println("[SERVER] Error: " + e.getMessage());
            }
        }
    }
}