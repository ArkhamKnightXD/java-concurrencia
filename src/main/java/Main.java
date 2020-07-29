import models.Printer;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int printerNumberA = 3;
        int printerNumberB = 4;
        int i = 0;

        Stack trabajos = new Stack();

        ArrayList<Thread> printersA = new ArrayList<>();
        ArrayList<Thread> printersB = new ArrayList<>();


        //Generando las impresoras A
        for(i = 0; i < printerNumberA; i++)
        {
            Printer printerA = new Printer(i);
            Thread th_PrinterA = new Thread(()-> {
                try {
                    printA(printerA,trabajos);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            });

            printersA.add(th_PrinterA);

        }


        //Generando las impresoras B
        for(i = 0; i < printerNumberB; i++)
        {
            Printer printerB = new Printer(i);
            Thread th_PrinterB = new Thread(()-> {
                try {
                    printB(printerB,trabajos);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });

            printersB.add(th_PrinterB);

        }

        //Thread para comenzar a generar trabajos de la impresora
        Thread th_generate = new Thread(() -> {
            try {
                generarTrabajo(trabajos);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        });
        th_generate.start();


        //Inicializando todas las impresoras A
        for(i = 0; i < printerNumberA; i++)
        {
            printersA.get(i).start();
        }

        //Inicializando todas las impresoras A
        for(i = 0; i < printerNumberB; i++)
        {
            printersB.get(i).start();
        }
    }


    public static void generarTrabajo(Stack trabajos) throws InterruptedException
    {
        Object[] tipoTrabajo = {"B","A","AB"};
        Random random = new Random();

        do
        {

            int option = random.nextInt(3);
            trabajos.push(tipoTrabajo[option]);
            //System.out.println(trabajos);
            Thread.sleep(200);

        }while(true);

    }


    public static void printA(Printer prntA, Stack trabajos) throws InterruptedException
    {
        do
        {

            Thread.sleep(1300);

            prntA.setOccupied(false); //desocupando la impresora para el siguiente trabajo
            if ((trabajos.peek().toString().equals("A") || trabajos.peek().toString().equals("AB")) && !prntA.isOccupied()) //si la impresora no esta ocupada y  el trabajo a realizar es de tipo A, realizar operacion
            {
                prntA.setOccupied(true); //la impresora estara ocupada procesando el trabajo
                System.out.println("imprimiendo trabajo " + trabajos.size() + " en A #" + prntA.getNumber() + " Cantidad de trabajos restantes: " + (trabajos.size() - 1));
                trabajos.pop();

                Thread.sleep(700);

            }

        } while (true);
    }

    public static void printB(Printer prntB, Stack trabajos) throws InterruptedException
    {
        do
        {
            Thread.sleep(1300);

            prntB.setOccupied(false); //desocupando la impresora para el siguiente trabajo
            if ((trabajos.peek().toString().equals("B") || trabajos.peek().toString().equals("AB")) && !prntB.isOccupied()) //si la impresora no esta ocupada y  el trabajo a realizar es de tipo A, realizar operacion
            {
                prntB.setOccupied(true);; //la impresora estara ocupada procesando el trabajo
                System.out.println("imprimiendo trabajo " + trabajos.size() + " en B #" + prntB.getNumber() + " Cantidad de trabajos restantes: " + (trabajos.size() - 1));
                trabajos.pop();

                Thread.sleep(700);

            }

        } while (true);
    }
}
