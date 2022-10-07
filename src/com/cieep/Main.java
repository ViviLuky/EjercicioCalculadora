package com.cieep;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int num1 = 0,num2 = 0;
        String signo="ERROR";
        Scanner scanner = new Scanner(System.in);
        ProcessBuilder proceso= null;

        System.out.println("Escribe la operación: ");
        String operacion= scanner.nextLine();

        try{
        if (operacion.contains("+")){
            signo="+";
            num1=Integer.parseInt(operacion.split("\\+")[0]);
            num2=Integer.parseInt(operacion.split("\\+")[1]);
            proceso= new ProcessBuilder("java","-jar","Suma.jar");

        }else if(operacion.contains("-")){
            signo="-";
            num1=Integer.parseInt(operacion.split("-")[0]);
            num2=Integer.parseInt(operacion.split("-")[1]);
            proceso= new ProcessBuilder("java","-jar","Resta.jar");

        }else if(operacion.contains("*")){
            signo="*";
            num1=Integer.parseInt(operacion.split("\\*")[0]);
            num2=Integer.parseInt(operacion.split("\\*")[1]);
            proceso= new ProcessBuilder("java","-jar","Multiplicacion.jar");
        }else if(operacion.contains("/")){
            signo="/";
            num1=Integer.parseInt(operacion.split("/")[0]);
            num2=Integer.parseInt(operacion.split("/")[1]);
            proceso= new ProcessBuilder("java","-jar","Division.jar");
        }else {
            System.out.println("La operacion no es realizable ");
        }
        if (!signo.equals("ERROR")){

            float resultado=crearHijoCalculadora(proceso,num1,num2);
            System.out.println(operacion+"="+resultado);
        }
    }catch (NumberFormatException | IOException exception){
            System.out.println("Eres bobo no sabes escribir una operacion");
        }
    }

    private static float crearHijoCalculadora(ProcessBuilder proseso, int num1, int num2) throws IOException {
        proseso.redirectErrorStream(true);

        Process hijo= proseso.start();

        OutputStream outputStream= hijo.getOutputStream();
        PrintStream printStream=new PrintStream(outputStream);
        InputStream inputStream=hijo.getInputStream();
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);


        printStream.println(String.valueOf(num1));
        printStream.flush();

        printStream.println(String.valueOf(num2));
        printStream.flush();


        return Float.parseFloat(bufferedReader.readLine());
    }
}