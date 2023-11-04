package br.com.bom.consultorio.usuarios.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Entity(name = "usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue
    private Integer id;

    @UUID
    @GeneratedValue(strategy = GenerationType.UUID)
    private String identificador;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "administrador", nullable = false)
    private Boolean administradorPlataforma;

    @Column(name = "email_verificado", nullable = false)
    private Boolean emailVerificado;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioEmpresaModel> empresasVinculadas;

    @Column(name = "data_criacao", nullable = false)
    private OffsetDateTime dataCriacao;

    @Column(name = "data_alteracao", nullable = false)
    private OffsetDateTime dataAlteracao;
}
