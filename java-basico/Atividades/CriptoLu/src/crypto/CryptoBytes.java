package crypto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CryptoBytes {

    public static void process(String source, String destination, String password) throws IOException {
        // Ler os bytes do arquivo original e gerar um arquivo criptografado

        FileOutputStream  dist = new FileOutputStream(destination, false);
        FileInputStream  src = new FileInputStream(source);
        
        System.out.println("Criptografando o arquivo...");
        try {
            for (int byteRead = src.read(); byteRead != -1; byteRead = src.read()) {
                //  Mapear cada byte lido do objeto src para um byte correspondente da senha e criptografar fazendo o XOR
                int passwordIndex = 0;
                while (byteRead != -1) {
                    byte passwordByte = (byte) password.charAt(passwordIndex);
                    byte encryptedByte = (byte) (byteRead ^ passwordByte);
                    dist.write(encryptedByte);
                    passwordIndex = (passwordIndex + 1) % password.length();
                    byteRead = src.read();
                }
            }
            System.out.println("Arquivo criptografado.");
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        
        dist.close();
        src.close();

    }
}
