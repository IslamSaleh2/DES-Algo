


public class Main {
    public static void main(String[] args) {
        encryption e=new encryption();
        Key k=new Key();
        e.encrypt(k.key("abcdefgh"));
        decryption d=new decryption();
        KeyDe KD=new KeyDe();
        //d.decrypt(KD.key("abcdefgh"));
    }

}
