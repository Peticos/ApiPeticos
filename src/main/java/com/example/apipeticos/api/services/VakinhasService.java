package com.example.apipeticos.api.services;

import com.example.apipeticos.api.models.Vaccine;
import com.example.apipeticos.api.models.Vakinha;
import com.example.apipeticos.api.repositories.VakinhaRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
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

    public static int insertVakinhaRPA(Vakinha vakinha) {
        ProcessBuilder pb = new ProcessBuilder(
                "python",
                "C:\\Peticos\\Api\\ApiPeticos\\src\\main\\resources\\static\\RPA_Vakinha_Insert.py",
                vakinha.getLink(),
                vakinha.getIdPet().toString(),
                vakinha.getIdUser().toString()
        );

        int exitCode = -1;

        try {
            // Iniciar o processo e capturar a saída e erros
            Process process = pb.start();

            // Leitura da saída padrão (stdout)
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                 BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {

                // Processar saída padrão
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
                System.out.println("Output: " + output.toString());

                // Processar erros (stderr)
                StringBuilder errors = new StringBuilder();
                while ((line = errorReader.readLine()) != null) {
                    errors.append(line).append("\n");
                }
                if (errors.length() > 0) {
                    System.err.println("Errors: " + errors.toString());
                }
            }

            // Esperar o processo terminar e obter o código de saída
            exitCode = process.waitFor();
            System.out.println("Process exited with code: " + exitCode);

            // Verificar o código de saída e retorno detalhado
            return exitCode == 0 ? 0 : -1;
        } catch (IOException e) {
            System.err.println("Erro de IO ao executar o processo: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("Processo interrompido: " + e.getMessage());
            e.printStackTrace();
            Thread.currentThread().interrupt();  // Preserve o estado de interrupção
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }

        return exitCode;
    }

}
