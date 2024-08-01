
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int cnt; // 문자의 수
    static String encodedStr; // 인코딩된 문자열
    static String binaryStr; // 16진수 입력값을 2진수 문자열로 변환
    static String alphanumericArr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:";
    static int bits;
    static int divides;
    static int remains;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        StringTokenizer token;

        int testCase = Integer.parseInt(input.readLine());
        for (int t = 0; t < testCase; t++) {
            binaryStr = convertToBinary(input.readLine()); // 16진수 입력값을 2진수 문자열로 변환
            cnt = 0; // 문자의 수 초기화
            encodedStr = ""; // 인코딩된 문자열 초기화
            while (true) {
                if (binaryStr.length() < 4) {
                    break;
                }
                String countBit;
                String code = binaryStr.substring(0, 4); // 코드 4자리 잘라냄
                binaryStr = binaryStr.substring(4, binaryStr.length()); // 사용한 코드를 문자열에서 제거

                if (code.equals("0001")) {
                    countBit = binaryStr.substring(0, 10); // Numeric 의 countBit는 10개
                    binaryStr = binaryStr.substring(10, binaryStr.length()); // countBit는 10개 제거
                    bits = convertToDecimal(countBit);
                    divides = bits / 3;
                    remains = bits % 3;

                    for (int i = 0; i < divides; i++) { // 수로 이루어진 문자열은 한번에 3자리씩 저장한다.
                        addStrAndIncreaseCnt(3); // 문자열 인코딩, 인코딩값 저장, cnt값 증가
                    }
                    if (remains != 0) {
                        addStrAndIncreaseCnt(remains); // 남은 문자열도 처리: 2자리는 7bit, 1자리는 4bit
                    }

                } else if (code.equals("0010")) {
                    countBit = binaryStr.substring(0, 9); // Alphanumeric 의 countBit는 9개
                    binaryStr = binaryStr.substring(9, binaryStr.length()); // countBit는 9개 제거
                    bits = convertToDecimal(countBit);
                    divides = bits / 2;
                    remains = bits % 2;
                    for (int i = 0; i < divides; i++) {
                        int temp = convertToDecimal(binaryStr.substring(0, 11));
                        binaryStr = binaryStr.substring(11, binaryStr.length()); // 사용한 문자열은 제거
                        encodedStr = encodedStr + alphanumericArr.charAt(temp / 45) + alphanumericArr.charAt(temp % 45);
                        cnt += 2;
                    }
                    if (remains == 1) {
                        int temp = convertToDecimal(binaryStr.substring(0, 6));
                        binaryStr = binaryStr.substring(6, binaryStr.length()); // 사용한 문자열은 제거
                        encodedStr = encodedStr + alphanumericArr.charAt(temp);
                        cnt++;
                    }

                } else if (code.equals("0100")) {
                    countBit = binaryStr.substring(0, 8);
                    binaryStr = binaryStr.substring(8, binaryStr.length()); // countBit는 8개 제거
                    bits = convertToDecimal(countBit);
                    for (int i = 0; i < bits; i++) {
                        int temp = convertToDecimal(binaryStr.substring(0, 8));
                        binaryStr = binaryStr.substring(8, binaryStr.length()); // 사용한 문자열은 제거

                        if (temp >= Integer.parseInt("20", 16) && temp <= Integer.parseInt("7e", 16)) {
                            encodedStr += (char) temp;
                        } else {
//                            encodedStr += "\\" + Integer.toHexString(temp).toUpperCase();
                            encodedStr += "\\" + String.format("%2s", Integer.toHexString(temp).toUpperCase()).replace(' ', '0');
                        }
                        cnt++;
                    }

                } else if (code.equals("1000")) {
                    countBit = binaryStr.substring(0, 8);
                    binaryStr = binaryStr.substring(8, binaryStr.length()); // countBit는 8개 제거
                    bits = convertToDecimal(countBit);

                    for (int i = 0; i < bits; i++) {
                        int temp = convertToDecimal(binaryStr.substring(0, 13));
                        binaryStr = binaryStr.substring(13, binaryStr.length());
                        encodedStr += "#"
                                + String.format("%4s", Integer.toHexString(temp).toUpperCase()).replace(' ', '0');
                        cnt++;
                    }

                } else if (code.equals("0000")) {
                    break;
                }
            }


            encodedStr = cnt + " " + encodedStr;
            System.out.println(encodedStr);

        }

    }

    private static String convertToBinary(String input) {
        String binaryOutput = "";
        int decimal;
        for (int i = 0; i < 19; i++) {
            String temp;
            temp = input.substring(i * 2, i * 2 + 2);
            decimal = Integer.parseInt(temp, 16);
            binaryOutput += String.format("%8s", Integer.toBinaryString(decimal)).replace(' ', '0');
        }
        return binaryOutput;
    }

    private static int convertToDecimal(String binary) {
        int sum = 0;
        int pow = 1;
        for (int i = binary.length() - 1; i >= 0; i--) {
            sum += (binary.charAt(i) - '0') * pow;
            pow *= 2;
        }
        return sum;
    }

    private static void addStrAndIncreaseCnt(int num) {
        int range = num * 3 + 1; // bit 범위 : 3->10, 2->7, 1->4
        int numbers = convertToDecimal(binaryStr.substring(0, range));
        binaryStr = binaryStr.substring(range, binaryStr.length()); // 사용한 문자열은 제거
        cnt += num; // 문자열 개수 증가
        String temp = "" + numbers;
//        encodedStr += numbers; // 문자열 저장
        if(num==3)
            encodedStr += String.format("%3s",temp).replace(' ', '0');
        else if (num==2)
            encodedStr += String.format("%2s",temp).replace(' ', '0');
        else if (num==1){
            encodedStr += String.format("%1s",temp).replace(' ', '0');
        }

    }

}
