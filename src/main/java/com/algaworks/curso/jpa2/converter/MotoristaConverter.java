package com.algaworks.curso.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.curso.jpa2.dao.MotoristaDAO;
import com.algaworks.curso.jpa2.modelo.Motorista;
import com.algaworks.curso.jpa2.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Motorista.class)
public class MotoristaConverter implements Converter {

    private MotoristaDAO motoristaDAO;

    public MotoristaConverter() {
        this.motoristaDAO = CDIServiceLocator.getBean(MotoristaDAO.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Motorista retorno = null;

        if (value != null && !value.trim().isEmpty()) {
            try {
                Long codigo = Long.valueOf(value);
                retorno = this.motoristaDAO.buscarPeloCodigo(codigo);
            } catch (NumberFormatException e) {
                System.err.println("Erro ao converter código do motorista: " + value);
            }
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Long codigo = ((Motorista) value).getCodigo();
            return (codigo == null ? "" : codigo.toString());
        }
        return "";
    }
}
