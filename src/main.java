import java.util.Scanner;


public class main {
    public static void main(String[] args) throws Exception{
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String deg = scan.nextLine();               //Получили выражение
                                                    //deg = degeneration(выражение)
        // Убираем пробелы в выражение если есть
        deg = deg.replace(" ", ""); //Убрали все лишнее чтобы не было ошибок
        System.out.println(analysis(deg));
    }

    static String analysis (String deg) throws Exception {
        int nam1;       //Операнд 1
        int nam2;       //Операнд 2
        String oper;    //Оператор
        String result;  //Результат
        boolean isRoman;//Является ли Римским или арабским

        //Вычисляем какие мы получили операнды
        String[] operands = deg.split("[+\\-*/]");// Проверяем выражение на наличие знака и убираем, чтобы получать в массиве 2 операнда

        //Вычисляем наш оператор
        oper = dtOper(deg); //Переходим к методу и проверяем какой у нас оператор и запоминаем его в переменной oper
        //Проверяем что наш оператор верен
        if (oper == null) throw new Exception("Неподдерживаемая математическая операция");  // Проверяем нашу переменную oper после метода что наш оператор и если нет выдаем ошибку

        //Проверяем что у нас 2 операнда
        if (operands.length != 2) throw new Exception("Должно быть два операнда");//operands - операнды

        //Eсли оба числа римские
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) { //Проверяем наши оперенды если они = римским то конвертируем из в арабские числа и переводим его в тип int
                                                                        //Операторы & и && - это логические операторы И. Работает так: если хотя бы одно условие false, то результат - false.
                                                                        // Т.е, чтобы результат был true, надо чтобы оба условия были true.
            //Конвертируем оба числа в арабские если они римские
            nam1 = Roman.convertToArabian(operands[0]);
            nam2 = Roman.convertToArabian(operands[1]);
            isRoman = true;}
        //Eсли оба числа арабские
        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            nam1 = Integer.parseInt(operands[0]);
            nam2 = Integer.parseInt(operands[1]);
            isRoman = false;}
        //Eсли одни число римское, а другое - арабское
        else {throw new Exception("Числа должны быть в одном формате");}

        // Проверяем на то что оба числа не менее 1 и не более 10
        if (nam1 > 10 || nam2 >10) throw new Exception("Операнд должен быть от 1 до 10");

        //Калькулятор
        int ara = calc(nam1, nam2, oper);   //Переходим к методу и сравниваем нашу переменную oper cо старкой в которой указан
                                            //нам интересующий знак и делаем вычисление наших операндов nam1 и nam2. Возрашаем результат.

        // Проверяем были введены в начале римские или арабские и выводим в правильном формате ответ.
        if (isRoman) {                          //Если вводили римские цифры то
            //если римское число получилось меньше либо равно нулю, генерируем ошибку
            if (ara <= 0) {                     //Проверяем ответ выражения <= 0 и ели это правда выводим ошибку в консоль по скольку римские цифры не могут быть отрицательные
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            //конвертируем результат операции из арабского в римское
            result = Roman.convertToRoman(ara); //Если же проходит проверку то конвертируем наш результат в римское число и запоминаем его в переменную result
        } else {
            //Конвертируем арабское число в тип String
            result = String.valueOf(ara);       //Если же проверка ложна то преобразовываем из int в String и сохраняем в в переменную result
        }
        //возвращаем результат
        return result;
    }

    static String dtOper (String deg) throws Exception {
        if (deg.contains("+")) return "+";
        else if (deg.contains("-")) return "-";
        else if (deg.contains("*")) return "*";
        else if (deg.contains("/")) return "/";
        else return null;
    }

     static int calc(int nam1, int nam2, String oper) {
         return switch (oper) {
             case "+" -> nam1 + nam2;
             case "-" -> nam1 - nam2;
             case "*" -> nam1 * nam2;
             default -> nam1 / nam2;
         };
     }
}

class Roman {
    static String[] romanArray = new String[]{null, "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};

    public static boolean isRoman(String val) {
        for (int i = 0; i < romanArray.length; i++) {
            if (val.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int ara) {
        return romanArray[ara];
    }

}

