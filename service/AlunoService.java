package com.facens.academia.service;

import com.facens.academia.dto.request.AlunoRequest;
import com.facens.academia.dto.response.AlunoResponse;
import com.facens.academia.entity.Aluno;
import com.facens.academia.entity.Plano;
import com.facens.academia.exception.RecursoNaoEncontradoException;
import com.facens.academia.exception.RegraNegocioException;
import com.facens.academia.repository.AlunoRepository;
import com.facens.academia.repository.PlanoRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final PlanoRepository planoRepository;

    public AlunoService(AlunoRepository alunoRepository,
                        PlanoRepository planoRepository) {
        this.alunoRepository = alunoRepository;
        this.planoRepository = planoRepository;
    }

    public AlunoResponse cadastrar(AlunoRequest request) {
        // Valida email duplicado
        alunoRepository.findByEmail(request.getEmail()).ifPresent(a -> {
            throw new RegraNegocioException(
                "Email já cadastrado: " + request.getEmail());
        });

        // Valida existência do plano
        Plano plano = planoRepository.findById(request.getPlanoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    "Plano não encontrado com id: " + request.getPlanoId()));

        Aluno aluno = new Aluno();
        aluno.setNome(request.getNome());
        aluno.setEmail(request.getEmail());
        aluno.setIdade(request.getIdade());
        aluno.setTelefone(request.getTelefone());
        aluno.setSituacao(request.getSituacao());
        aluno.setDataCadastro(LocalDateTime.now());
        aluno.setPlano(plano);

        Aluno salvo = alunoRepository.save(aluno);
        return toResponse(salvo);
    }

    public List<AlunoResponse> listarTodos() {
        return alunoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public AlunoResponse buscarPorId(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    "Aluno não encontrado com id: " + id));
        return toResponse(aluno);
    }

    private AlunoResponse toResponse(Aluno aluno) {
        return new AlunoResponse(
            aluno.getId(),
            aluno.getNome(),
            aluno.getEmail(),
            aluno.getIdade(),
            aluno.getTelefone(),
            aluno.getSituacao(),
            aluno.getDataCadastro(),
            aluno.getPlano().getNome()
        );
    }
}