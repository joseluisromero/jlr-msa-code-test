package com.code;

import java.util.Scanner;

public class ListasDinamicas {

  public static void main(String[] args) {
    Agenda agenda = new Agenda();
    Scanner scanner = new Scanner(System.in);
    int option;

    do {
      System.out.println("\nMenu:");
      System.out.println("1. Añadir tarea");
      System.out.println("2. Listar tareas");
      System.out.println("3. Eliminar tarea");
      System.out.println("4. Salir");
      System.out.println("");
      System.out.print("Seleccione una opción: ");
      option = scanner.nextInt();

      switch (option) {
        case 1:
          System.out.print("Ingrese el mes (0-11): ");
          int month = scanner.nextInt();
          Integer daysInMonth = verifyDayMes(month);
          if (daysInMonth == null) {
            System.out.println("Mes inválido.");
            break;
          }

          System.out.print("Ingrese el día (0-" + (daysInMonth - 1) + "): ");
          int day = scanner.nextInt();
          scanner.nextLine(); // Consumir el salto de línea
          System.out.print("Ingrese la descripción de la tarea: ");
          String description = scanner.nextLine();
          agenda.addTask(month, day, description);
          break;
        case 2:
          System.out.print("Ingrese el mes (0-11): ");
          month = scanner.nextInt();
          daysInMonth = verifyDayMes(month);
          if (daysInMonth == null) {
            System.out.println("Mes inválido.");
            break;
          }
          System.out.print("Ingrese el día (0-" + (daysInMonth - 1) + "): ");
          day = scanner.nextInt();
          agenda.listTasks(month, day);
          break;
        case 3:
          System.out.print("Ingrese el mes (0-11): ");
          month = scanner.nextInt();
          daysInMonth = verifyDayMes(month);
          if (daysInMonth == null) {
            System.out.println("Mes inválido.");
            break;
          }

          System.out.print("Ingrese el día (0-" + (daysInMonth - 1) + "): ");
          day = scanner.nextInt();
          scanner.nextLine(); // Consumir el salto de línea
          System.out.print("Ingrese la descripción de la tarea a eliminar: ");
          description = scanner.nextLine();
          agenda.deleteTask(month, day, description);
          break;
        case 4:
          System.out.println("Saliendo...");
          break;
        default:
          System.out.println("Opción inválida.");
      }
    } while (option != 4);

    scanner.close();
  }

  public static Integer verifyDayMes(int month) {
    int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    if (month < 0 || month >= diasPorMes.length) {
      return null;
    }
    return diasPorMes[month];
  }
}


class Task {

  String description;
  Task next;

  Task(String description) {
    this.description = description;
    this.next = null;
  }
}

class Agenda {

  private Task[][] calendar;

  public Agenda() {
    calendar = new Task[12][31]; // 12 meses, 31 días
  }

  public void addTask(int month, int day, String description) {
    Task newTask = new Task(description);
    if (calendar[month][day] == null) {
      calendar[month][day] = newTask;
    } else {
      Task temp = calendar[month][day];
      while (temp.next != null) {
        temp = temp.next;
      }
      temp.next = newTask;
    }
  }


  public void listTasks(int month, int day) {
    Task temp = calendar[month][day];
    if (temp == null) {
      System.out.println("No hay tareas para este día.");
    } else {
      while (temp != null) {
        System.out.println(temp.description);
        temp = temp.next;
      }
    }
  }

  public void deleteTask(int month, int day, String description) {
    Task temp = calendar[month][day];
    Task prev = null;
    while (temp != null && !temp.description.equals(description)) {
      prev = temp;
      temp = temp.next;
      if (temp != null) {
        if (prev == null) {
          calendar[month][day] = temp.next;
        } else {
          prev.next = temp.next;
        }
        System.out.println("Tarea eliminada.");
      } else {
        System.out.println("Tarea no encontrada.");
      }
    }
  }
}








