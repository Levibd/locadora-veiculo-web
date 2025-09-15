package com.algaworks.curso.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.curso.jpa2.dao.FuncionarioDAO;
import com.algaworks.curso.jpa2.modelo.Funcionario;
import com.algaworks.curso.jpa2.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Funcionario.class)
public class FuncionarioConverter implements Converter {

    private FuncionarioDAO funcionarioDAO;

    public FuncionarioConverter() {
        this.funcionarioDAO = CDIServiceLocator.getBean(FuncionarioDAO.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Funcionario retorno = null;

        if (value != null && !value.trim().isEmpty()) {
            try {
                Long codigo = Long.valueOf(value);
                retorno = this.funcionarioDAO.buscarPeloCodigo(codigo);
            } catch (NumberFormatException e) {
                // Log de erro para depuração
                System.err.println("Erro ao converter código do funcionário: " + value);
            }
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Long codigo = ((Funcionario) value).getCodigo();
            return (codigo == null ? "" : codigo.toString());
        }
        return "";
    }
}
