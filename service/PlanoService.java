package com.facens.academia.service;

import com.facens.academia.dto.request.PlanoRequest;
import com.facens.academia.dto.response.PlanoResponse;
import com.facens.academia.entity.Plano;
import com.facens.academia.exception.RecursoNaoEncontradoException;
import com.facens.academia.repository.PlanoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanoService {

    private final PlanoRepository planoRepository;

    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    public PlanoResponse cadastrar(PlanoRequest request) {
        Plano plano = new Plano();
        plano.setNome(request.getNome());
        plano.setModalidade(request.getModalidade());
        plano.setValorMensal(request.getValorMensal());
        plano.setAtivo(request.getAtivo() != null ? request.getAtivo() : true);

        Plano salvo = planoRepository.save(plano);
        return toResponse(salvo);
    }

    public List<PlanoResponse> listarTodos() {
        return planoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public PlanoResponse buscarPorId(Long id) {
        Plano plano = planoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    "Plano não encontrado com id: " + id));
        return toResponse(plano);
    }

    private PlanoResponse toResponse(Plano plano) {
        return new PlanoResponse(
            plano.getId(),
            plano.getNome(),
            plano.getModalidade(),
            plano.getValorMensal(),
            plano.getAtivo()
        );
    }
}