package br.com.xpto.repository;

import java.util.List;

import javax.transaction.Transactional;

import br.com.xpto.model.Cidades;
import br.com.xpto.model.Estado;

@Transactional
public interface CidadeRepository {

	void salvarCidades(Cidades cidade);

	List<Cidades> all();

	List<Cidades> cidadesCapitais();

	int buscarPorEstados(Estado estado);
	
	Cidades buscarPeloIbge(String ibge);
	
	List<Cidades> buscarCidadePeloEstado(String estado);
	
	Cidades salvarCidadesComRetorno(Cidades cidade);
	
	Cidades find(Long id);
	
	void delet (Cidades cidade);
	
	int quantidadeDeRegistrosTotal();
}
