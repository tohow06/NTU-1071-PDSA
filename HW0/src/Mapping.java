import java.io.FileReader;

import java.io.BufferedReader;


public class Mapping {

    public static void main(String[] args) throws Exception {
        // read file from args[0] in Java 7 style

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            // read a line
            String data = br.readLine();

            // store the first integer in variable re adCount (number of reads)
            int readCount = Integer.parseInt(data);

            // initialization of a String array
            String[] readsArray = new String[readCount];
            String reference = new String();

            // printf in Java
//            System.out.printf("%d\n", readCount);

            //read the rest content of the file
            for (int i = 0; i < readCount; i++) {
                readsArray[i] = br.readLine();
//                System.out.println(readsArray[i]);
            }
            reference = br.readLine();
//            System.out.println(reference);

            int[] count = new int[readCount];
            for (int j = 0; j < readCount; j++) {

                int index = 0;
                int arrayLength = readsArray[j].length();
                while (true) {
                    index = reference.indexOf(readsArray[j], index);
//                    System.out.printf("index is %d\n" ,index);
                    if (index > -1) {
                        count[j]++;
                        index++;
                    } else {
                        break;
                    }
                }
//                System.out.printf("%s : %d\n", readsArray[j], count[j]);
            }
            int ans1 = 0;
            int ans2 = 0;
            for (int element : count) {
                if (element >= 1) {
                    ans1++;
                    if (element >= 2) {
                        ans2++;
                    }
                }
            }

            System.out.println(ans1);

            System.out.println(ans2);


        }

    }

}

