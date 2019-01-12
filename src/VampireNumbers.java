import java.util.Arrays;
import java.util.HashSet;

public class VampireNumbers{
    private static int numDigits(long num){// returns number of digits in given number
        return Long.toString(Math.abs(num)).length();
    }

    private static boolean fangCheck(long number, long fang1, long fang2){//checks if the number is a vampire number
        if(Long.toString(fang1).endsWith("0") && Long.toString(fang2).endsWith("0"))//both fangs should not endwith 0
            return false;

        int numberLen = numDigits(number);
        if(numDigits(fang1) != numberLen / 2 || numDigits(fang2) != numberLen / 2)//checking length of fangs to be half of that of original number
            return false;
        
        byte[] numberBytes = Long.toString(number).getBytes();//getting byte array original number
        byte[] fangBytes = (Long.toString(fang1) + Long.toString(fang2)).getBytes();//getting concatenated byte array from fangs to check if the numbers in each fangs match uniquely or not
        Arrays.sort(numberBytes);
        Arrays.sort(fangBytes);//sorting both number and fang byte array to check for equality
        return Arrays.equals(numberBytes, fangBytes);//if all the bytes are similar then all digits in each fangs are present uniquely
    }

    public static void main(String[] args){
        int count=0;//counting number of vampire numbers collected
        for(long i = 10; count < 100; i++ ){
            if((numDigits(i) % 2) != 0) {//if the number is of odd length skip the iteration
                continue;
            }
            for(long fang1 = 2; fang1 <= Math.sqrt(i) + 1 ; fang1++) {//check for all factors of the number
                if (i % fang1 == 0) {
                    long fang2 = i / fang1;//obtaining other factor i.e fang
                    if (fangCheck(i, fang1, fang2) && fang1 <= fang2) {//validate fangs such that the number is vampire
                        count++;
                        System.out.println(count + " | " + i + ": [" + fang1 + ", " + fang2 + "]");//vampire numbers
                    }
                }
            }
        }
    }
}

