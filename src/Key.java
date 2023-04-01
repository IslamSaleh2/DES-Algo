import java.util.ArrayList;
public class Key {
    public ArrayList key(String input){
        ArrayList<String>Cn_Dn=new ArrayList<>();
        ArrayList<String>Kn=new ArrayList<>();
        String K="";
        String K_Permutation="";
        int PC_1[]={57,49,41,33,25,17,9,1,58,50,42,34,26,18,10,2,59,51,43,35,27,19,11,3,60,52,44,36,36,55,47,39,31,23,15,7,62,54,46,38,30,22,14,6,61,53,45,37,29,21,13,5,28,20,12,4};
        int PC_2[]={14,17,11,24,1,5,3,28,15,6,21,10,23,19,12,4,26,8,16,7,27,20,13,2,41,52,31,37,47,55,30,40,51,45,33,48,44,49,39,56,34,53,46,42,50,36,29,32};
        byte[]bytes=input.getBytes();
        StringBuilder binary= new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        //String binary1="0001001100110100010101110111100110011011101111001101111111110001";
        for(int i=0;i<PC_1.length;i++){
            K_Permutation+=binary.charAt(PC_1[i]-1);
        }
        String C0="";
        String D0="";
        for(int i=0;i<K_Permutation.length();i++){
            if(i<28)
                C0+=K_Permutation.charAt(i);
            else
                D0+=K_Permutation.charAt(i);
        }
        String Cn_1=C0;
        String Dn_1=D0;
        for(int i=1;i<17;i++){
            String Cn="";
            String Dn="";
            String C_D="";
            if((i==1) || (i==2) || (i==9) || (i==16)){
                for(int j=1;j<Cn_1.length();j++){
                    Cn+=Cn_1.charAt(j);
                }
                Cn+=Cn_1.charAt(0);
                Cn_1=Cn;
                for(int j=1;j<Dn_1.length();j++){
                    Dn+=Dn_1.charAt(j);
                }
                Dn+=Dn_1.charAt(0);
                Dn_1=Dn;
                C_D=Cn+Dn;
                Cn_Dn.add(C_D);
            }
            else {
                for(int j=2;j<Cn_1.length();j++){
                    Cn+=Cn_1.charAt(j);
                }
                Cn+=Cn_1.charAt(0);
                Cn+=Cn_1.charAt(1);
                Cn_1=Cn;
                for(int j=2;j<Dn_1.length();j++){
                    Dn+=Dn_1.charAt(j);
                }
                Dn+=Dn_1.charAt(0);
                Dn+=Dn_1.charAt(1);
                Dn_1=Dn;
                C_D=Cn+Dn;
                Cn_Dn.add(C_D);
            }
        }for(int i=0;i<Cn_Dn.size();i++){
            String temp1=Cn_Dn.get(i);
            String temp2="";
            for(int j=0;j<PC_2.length;j++){
                temp2+=temp1.charAt(PC_2[j]-1);
            }
            Kn.add(temp2);
        }
        return Kn;
    }
}
