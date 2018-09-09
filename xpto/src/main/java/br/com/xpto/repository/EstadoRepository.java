package br.com.xpto.repository;
import java.util.List;

import javax.transaction.Transactional;

import br.com.xpto.model.Estado;

@Transactional
public interface EstadoRepository {
		
	Estado findEstado(String estado);
	
	List<Estado> all();

}
