
import java.util.Scanner;

class Calculator {
     public static void main(String[] args) throws Exception {
          Scanner scan = new Scanner(System.in);
          System.out.print("Введите выражение: ");
          String deg = scan.nextLine();
          System.out.println("\"" + (analysis(deg)) + "\"");
     }

     static String analysis(String deg) throws Exception {
          String[] data; //Операнды a и b
          char ac;     //Оператор
          String result = null;
          // Разделяем на операторы и операндя
          if (deg.contains("+")) {                     //Проверяем если в нашем выражение есть + то
               if (deg.contains(" + ")) {
                    data = deg.split(" \\+ ");   //Мы разделяем наши операнды на 2 с помощью метода .split убирая между a и b знак + и запоминаем их в массиве переменных data
               } else {
                    data = deg.split("\\+");}    //Если ввели знак без пробела
               ac = '+';                               //И запоминаем в переменной ac наш знак
          } else if (deg.contains("-")) {              //Если же наше выражение нет знака выше продолжаем проверять остальные знаки по примеру выше
               if (deg.contains(" - ")) {
                    data = deg.split(" - ");
               } else {
                    data = deg.split("-");}
               ac = '-';
          } else if (deg.contains("*")) {//"Java"*
               if (deg.contains(" * ")) {
                    data = deg.split(" \\* ");
               } else {
                    data = deg.split("\\*");}
               ac = '*';
          } else if (deg.contains("/")) {
               if (deg.contains(" / ")) {
                    data = deg.split(" / ");
               } else {
                    data = deg.split("/");}
               ac = '/';
          } else {       //11.При вводе пользователем выражения, не соответствующего одной из вышеописанных арифметических операций, приложение выбрасывает исключение и завершает свою работу.
               throw new Exception("Неподдерживаемая математическая операция");}
          //7. Проверяем чтобы строки длинной не более 10 символов
          if (data[0].length()>10||data[1].length()>10){
               throw new Exception("Операнд должен быть от 1 до 10 символов");}

          if (ac == '*' || ac == '/') {
               if (data[1].contains("\"")) throw new Exception("Неподдерживаемая математическая операция");
               if (Integer.parseInt(data[1]) > 10) //7. проверяем что число от 1 до 10
                    throw new Exception("Операнд должен быть от 1 до 10");}
          //9. Проверяем что "Первым аргументом выражения, подаваемого на вход, должна быть строка"
          if (!data[0].contains("\"") && data[1].contains("\"")){throw new Exception("Первым аргументом выражения, подаваемого на вход, должна быть строка");}
          //Убираем кавычки
          data[0] = data[0].replace("\"", "");
          data[1] = data[1].replace("\"", "");
          //1. Калькулятор
          if (ac == '+') {    //3. Результатом сложения двух строк, является строка состоящая из переданных.
               result = data[0] + data[1];
          } else if (ac == '-') {
               int id = data[0].indexOf(data[1]);
               if (id == -1) {//6. Результатом вычитания строки из строки, является строка, в которой удалена переданная подстрока или сама исходная строка, если в нее нет вхождения вычитаемой строки
                    result = data[0];
               } else {
                    result = data[0].substring(0, id);
                    result += data[0].substring(id + data[1].length());}
          } else if (ac == '*') {  //5. Результатом умножения строки на число n, является строка, в которой переданная строка повторяется ровно n раз.
               result = data[0].repeat(Integer.parseInt(data[1]));//.repeat Этот метод принимает один целочисленный аргумент, который указывает, сколько раз строка должна быть повторена.
          } else {                 //4. Результатом деления строки на число n, является строка в n раз короче исходной (смотрите пример).
               if (data[1].contains("\"")) throw new Exception("Неподдерживаемая математическая операция");
               result = data[0].substring(0, Integer.parseInt(data[1]));
          }
          //7. Если строка, полученная в результате работы приложения длиннее 40 символов, то в выводе после 40 символа должны стоять три точки (...)
          if (result.length()>40){
               result=result+"...";
          }else {result=result;}
          return result;
     }


}
