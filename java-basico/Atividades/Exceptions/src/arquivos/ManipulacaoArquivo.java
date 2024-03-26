package arquivos;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import arquivos.exceptions.ArquivoNaoEncontradoException;

public class ManipulacaoArquivo {
  
	public static void doesFileExist(String fileName) throws ArquivoNaoEncontradoException {
        Path path = Paths.get(fileName);
        
        if (!Files.exists(path)) {
        	throw new ArquivoNaoEncontradoException();
        }
    }

    public static void main(String[] args) {
        String fileName = "exemplo.txt";
        
        try {
        	doesFileExist(fileName);
        	System.out.println("O arquivo existe");
        }
        catch (ArquivoNaoEncontradoException ex) {
        	System.out.println(ex.getMessage());
        }
    }
}
