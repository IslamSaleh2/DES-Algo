import java.io.*;
import java.util.ArrayList;

public class decryption {
    char XOR(char x,char y){
        if(x==y){
            return '0';
        }
        else {
            return '1';
        }
    }
    public void decrypt(ArrayList<String>Key){
        ArrayList<String> all_binary=new ArrayList<>();
        ArrayList<String> Apply_IP=new ArrayList<>();
        String filename=" EncryptedFile.txt";
        String ciphertext="";
        String line=null;
        int[] IP = { 58, 50, 42, 34, 26, 18,
                10, 2, 60, 52, 44, 36, 28, 20,
                12, 4, 62, 54, 46, 38,
                30, 22, 14, 6, 64, 56,
                48, 40, 32, 24, 16, 8,
                57, 49, 41, 33, 25, 17,
                9, 1, 59, 51, 43, 35, 27,
                19, 11, 3, 61, 53, 45,
                37, 29, 21, 13, 5, 63, 55,
                47, 39, 31, 23, 15, 7 };
        int E[]={32,1,2,3,4,5,
                4,5,6,7,8,9,
                8,9,10,11,12,13,
                12,13,14,15,16,17,
                16,17,18,19,20,21,
                20,21,22,23,24,25,
                24,25,26,27,28,29,
                28,29,30,31,32,1};
        int[][][] sbox = {
                { { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
                        { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
                        { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
                        { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } },

                { { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
                        { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
                        { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
                        { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } },
                { { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
                        { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
                        { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
                        { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } },
                { { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
                        { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
                        { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
                        { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } },
                { { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
                        { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
                        { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
                        { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } },
                { { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
                        { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
                        { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
                        { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } },
                { { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
                        { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
                        { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
                        { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } },
                { { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
                        { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
                        { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
                        { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } }
        };
        int[] P = { 16, 7, 20, 21, 29, 12, 28,
                17, 1, 15, 23, 26, 5, 18,
                31, 10, 2, 8, 24, 14, 32,
                27, 3, 9, 19, 13, 30, 6,
                22, 11, 4, 25 };
        int[] IP1 = { 40, 8, 48, 16, 56, 24, 64,
                32, 39, 7, 47, 15, 55,
                23, 63, 31, 38, 6, 46,
                14, 54, 22, 62, 30, 37,
                5, 45, 13, 53, 21, 61,
                29, 36, 4, 44, 12, 52,
                20, 60, 28, 35, 3, 43,
                11, 51, 19, 59, 27, 34,
                2, 42, 10, 50, 18, 58,
                26, 33, 1, 41, 9, 49,
                17, 57, 25 };
        try{
            FileReader fileReader=new FileReader(filename);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            while((line=bufferedReader.readLine()) != null){
                ciphertext+=line;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Unable to open file '" + filename + "'");
        }
        catch (IOException e){
            System.out.println("Error reading file '" + filename + "'");
        }
        String temp="";
        for(int i=0;i<=ciphertext.length();i++){
            if((i)%64 == 0 && i !=0){
                all_binary.add(temp);
                temp="";
                if(i==ciphertext.length())
                    break;
                else
                    temp+=ciphertext.charAt(i);
            }
            else
                temp+=ciphertext.charAt(i);
        }
        for(int j=0;j<all_binary.size();j++) {
            String temp1="";
            for(int i=0;i<IP.length;i++){
                temp1+=all_binary.get(j).charAt(IP[i]-1);
            }
            Apply_IP.add(temp1);
        }
        String h="";
        for(int i=0;i<Apply_IP.size();i++){
            String L0="";
            String R0="";
            for(int j=0;j<Apply_IP.get(i).length();j++){
                if(j<32)
                    L0+=Apply_IP.get(i).charAt(j);
                else
                    R0+=Apply_IP.get(i).charAt(j);
            }
            String Ln_1=L0;
            String Rn_1=R0;
            for(int j=0;j<16;j++){
                String Ln=Rn_1;
                String Rn="";
                String Expansion="";
                String F_XOR="";
                for(int k=0;k<E.length;k++){
                    Expansion+=Rn_1.charAt(E[k]-1);
                }
                for(int k=0;k<Expansion.length();k++){
                    F_XOR+=XOR(Key.get(0).charAt(k),Expansion.charAt(k));
                }
                String S_Box="";
                for (int k = 0; k < 48; k += 6) {
                    String x = F_XOR.substring(k, k + 6);
                    int num = k / 6;
                    int row = Integer.parseInt(x.charAt(0) + "" + x.charAt(5), 2);
                    int col = Integer.parseInt(x.substring(1, 5), 2);
                    String num_s_box=Integer.toBinaryString(sbox[num][row][col]);
                    if(num_s_box.length()==4){
                        S_Box+=num_s_box;
                    }
                    else {
                        for (int m = 0; m < 4-num_s_box.length(); m++) {
                            S_Box+="0";
                        }
                        S_Box+=num_s_box;
                    }
                }
                String F_Permutation="";
                for(int k=0;k<P.length;k++){
                    F_Permutation+=S_Box.charAt(P[k]-1);
                }
                for(int k=0;k<F_Permutation.length();k++){
                    Rn+=XOR(F_Permutation.charAt(k),Ln_1.charAt(k));
                }
                Ln_1=Ln;
                Rn_1=Rn;
            }
            String IP_11=Rn_1+Ln_1;
            String Final="";
            for(int k=0;k<IP1.length;k++){
                Final+=IP_11.charAt(IP1[k]-1);
            }
            h+=Final;
        }
        String str = "";
        for (int i = 0; i < h.length()/8; i++) {
            int a = Integer.parseInt(h.substring(8*i,(i+1)*8),2);
            str += (char)(a);
        }
       String plaintext="";
        for(int i=0;i<str.length();i++){
            if(str.length()-i<8){
                if(str.charAt(i)!='#')
                    plaintext+=str.charAt(i);
            }
            else
                plaintext+=str.charAt(i);
        }
        System.out.println("plaintext: "+ plaintext);
        String fileName="Decrypted_File.txt";
        try {
            FileWriter fileWriter=new FileWriter(fileName);
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            bufferedWriter.write(plaintext);
            bufferedWriter.close();
        }

        catch (IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }
    }
}
