package br.com.bom.consultorio.empresa.services;

import br.com.bom.consultorio.empresa.models.empresa.EmpresaModel;
import br.com.bom.consultorio.empresa.payloads.requests.CriarEmpresaApiRequest;
import br.com.bom.consultorio.empresa.usecases.empresa.BuscarEmpresasUseCase;
import br.com.bom.consultorio.empresa.usecases.empresa.CriarEmpresaUseCase;
import br.com.bom.consultorio.empresa.usecases.empresa.dto.CriarEmpresaUseCaseRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service para gerenciamento das empresas/tenants da plataforma. Aqui serão feitos os cadastros
 * gerenciados pelos administradores do SaaS, possibilitando a inserção manual de empresas na plataforma.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final CriarEmpresaUseCase criarEmpresaUseCase;
    private final BuscarEmpresasUseCase buscarEmpresasUseCase;

    /**
     * Criar uma nova empresa para utilização como tenant
     *
     * @param criarEmpresaApiRequest dados cadastrais
     * @return model da empresa criada
     */
    @Transactional
    public EmpresaModel criarEmpresa(CriarEmpresaApiRequest criarEmpresaApiRequest) {
        CriarEmpresaUseCaseRequest useCaseRequest = CriarEmpresaUseCaseRequest
                .builder()
                .slug(criarEmpresaApiRequest.getSlug())
                .inscricaoEstadual(criarEmpresaApiRequest.getInscricaoEstadual())
                .nomeFantasia(criarEmpresaApiRequest.getNomeFantasia())
                .razaoSocial(criarEmpresaApiRequest.getRazaoSocial())
                .cnpj(criarEmpresaApiRequest.getCnpj())
                .build();

        EmpresaModel empresaCriada = this.criarEmpresaUseCase.executar(useCaseRequest);
        log.info("Empresa criada: {}", empresaCriada);
        return empresaCriada;
    }

    public List<EmpresaModel> buscarEmpresasCadastradas() {
        return this.buscarEmpresasUseCase.executar();
    }

}
