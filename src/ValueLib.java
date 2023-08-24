//public class ValueLib {
//    private static ValueLib valueLib;
//    private static int primaryInt = -1;
//
//    private ValueLib(){
//    }
//
//    public static ValueLib getLib(){
//        if (valueLib == null){
//            primaryInt = generatorBasicInteger();
//            valueLib = new ValueLib();
//        }
//        return valueLib;
//    }
//
//    private static int generatorBasicInteger(){
//        int value = -1;
//        // Generate a prime number if it hasn't been generated before
//        if (primaryInt == -1) {
//            do {
//                for (int i = 2; i < 2000; i++) {
//                    if (isPrime(i)) {
//                        if (Math.random() < 0.25) {
//                            value = i;
//                            if (Math.random() >= 0.5){
//                                break;
//                            }
//                        }
//                    }
//                }
//            } while (value == -1);
//
//            // Store the generated prime number
//            primaryInt = value;
//        }
//        else {
//            value = primaryInt; // Use the previously generated prime number
//        }
//
//        return value;
//    }
//
//    private static boolean isPrime(int number){
//        for (int i = 2; i < number; i++){
//            if (number % i == 0){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public int getPrime(){
//        return primaryInt;
//    }
//}