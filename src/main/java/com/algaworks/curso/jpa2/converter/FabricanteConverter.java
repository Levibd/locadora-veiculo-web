package com.algaworks.curso.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.algaworks.curso.jpa2.dao.FabricanteDAO;
import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.util.cdi.CDIServiceLocator;

@FacesConverter(value = "fabricanteConverter")
public class FabricanteConverter implements Converter {

	@Inject
	private FabricanteDAO fabricanteDAO;

	public FabricanteConverter() {
		this.fabricanteDAO = CDIServiceLocator.getBean(FabricanteDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		System.out.println("--- CONVERTER: getAsObject chamado com o valor: '" + value + "'");

		Fabricante retorno = null;

		if (value != null && !value.trim().isEmpty()) {
			try {
				Long id = Long.valueOf(value);
				System.out.println("--- CONVERTER: Buscando fabricante com ID: " + id);

				retorno = this.fabricanteDAO.buscarPeloCodigo(id);

				// Linha de debug para saber o que o DAO retornou
				System.out.println(
						"--- CONVERTER: Fabricante encontrado: " + (retorno != null ? retorno.getNome() : "NULO"));
			} catch (NumberFormatException e) {
				System.err.println("--- CONVERTER: Erro, o valor '" + value + "' não é um número válido.");
			}

		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Fabricante) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());

			return retorno;
		}

		return "";
	}

}