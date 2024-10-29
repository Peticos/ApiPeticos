package com.example.apipeticos.api.services;

import com.example.apipeticos.api.models.Vaccine;
import com.example.apipeticos.api.models.Vakinha;
import com.example.apipeticos.api.repositories.VakinhaRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class VakinhasService {

    private final VakinhaRepository vakinhaRepository;

    public VakinhasService(VakinhaRepository vakinhaRepository){
        this.vakinhaRepository = vakinhaRepository;
    }

    public List<Vakinha> findAll(){
        return vakinhaRepository.findAll();
    }

    public List<Vakinha> findByIdUser(Integer id){
        return vakinhaRepository.findByIdUser(id);
    }

    public static int insertVakinhaRPA(Vakinha vakinha){
        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "python",
                    "C:\\Peticos\\Api\\ApiPeticos\\src\\main\\resources\\static\\RPA_Vakinha_Insert.py",
                    vakinha.getLink(),
                    vakinha.getIdPet().toString(),
                    vakinha.getIdUser().toString()
            );

            // Iniciar o processo e capturar a saída e erros
            Process process = pb.start();

            // Leitura da saída padrão (stdout)
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Output: " + line);
            }

            // Leitura dos erros (stderr)
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                System.err.println("Error: " + line);
            }

            // Esperar o processo terminar e obter o código de saída
            int exitCode = process.waitFor();
            System.out.println("Process exited with code: " + exitCode);

            return exitCode == 0 ? 0 : -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }
}
