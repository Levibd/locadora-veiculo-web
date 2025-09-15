package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.FabricanteDAO;
import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.service.CadastroFabricanteService;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFabricanteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroFabricanteService cadastroFabricanteService;
	
	private Fabricante fabricante;
	
	@Inject
	private FabricanteDAO fabricanteDAO;
	
	private Long fabricanteId;
	
	public void salvar() {
		try {
			this.cadastroFabricanteService.salvar(fabricante);
			FacesUtil.addSuccessMessage("Fabricante salvo com sucesso!");
			
			this.limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	@PostConstruct
	public void init() {
		System.out.println("--- BEAN CADASTRO: Fui criado!");
		this.limpar();
	}
	
	public void inicializar() {
        System.out.println("--- BEAN CADASTRO: Método inicializar() foi chamado.");
        
        if (this.fabricanteId != null) {
            System.out.println("--- BEAN CADASTRO: Editando. Buscando fabricante com ID: " + this.fabricanteId);
            this.fabricante = this.fabricanteDAO.buscarPeloCodigo(this.fabricanteId);
        } else {
            System.out.println("--- BEAN CADASTRO: Cadastrando um novo fabricante.");
            this.fabricante = new Fabricante();
        }
    }
	
	public void limpar() {
		this.fabricante = new Fabricante();
	}

	public Fabricante getFabricante() {
		return fabricante;
	}
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	
	public Long getFabricanteId() {
		return fabricanteId;
	}
	
	public void setFabricanteId(Long fabricanteId) {
        this.fabricanteId = fabricanteId;
    }
	
	
	
}
