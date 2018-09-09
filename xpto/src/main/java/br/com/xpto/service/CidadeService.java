package br.com.xpto.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.com.xpto.model.Cidades;
import br.com.xpto.model.Estado;
import br.com.xpto.repository.CidadeRepository;

@Service
@Transactional
public class CidadeService implements CidadeRepository, Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	EntityManager manager;

	@Override
	public void salvarCidades(Cidades cidade) {
		manager.persist(cidade);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cidades> all() {
		Session session = this.manager.unwrap(Session.class);
		Criteria cr = session.createCriteria(Cidades.class);
		cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return cr.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cidades> cidadesCapitais() {
		Session session = this.manager.unwrap(Session.class);
		Criteria cr = session.createCriteria(Cidades.class);
		cr.add(Restrictions.eq("capital", true));
		cr.addOrder(Order.asc("nome"));
		cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return cr.list();
	}

	@Override
	public int buscarPorEstados(Estado estado) {
		Session session = this.manager.unwrap(Session.class);
		Criteria cr = session.createCriteria(Cidades.class);
		cr.createAlias("estado", "e");
		cr.add(Restrictions.eq("e.id", estado.getId()));
		return cr.setProjection(Projections.rowCount()).uniqueResult().hashCode();
	}

	@Override
	public Cidades buscarPeloIbge(String ibge) {
		Session session = this.manager.unwrap(Session.class);
		Criteria cr = session.createCriteria(Cidades.class);
		cr.add(Restrictions.eq("ibge", ibge));
		return (Cidades) cr.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cidades> buscarCidadePeloEstado(String estado) {
		Session session = this.manager.unwrap(Session.class);
		Criteria cr = session.createCriteria(Cidades.class);
		cr.createAlias("estado", "e");
		cr.add(Restrictions.eq("e.uf", estado));
		cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return cr.list();
	}

	@Override
	public Cidades salvarCidadesComRetorno(Cidades cidade) {
		return manager.merge(cidade);
	}

	@Override
	public Cidades find(Long id) {
		return manager.find(Cidades.class, id);
	}

	@Override
	public void delet(Cidades cidade) {
		manager.remove(cidade);		
	}

	@Override
	public int quantidadeDeRegistrosTotal() {
		Session session = this.manager.unwrap(Session.class);
		Criteria cr = session.createCriteria(Cidades.class);
		cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return cr.setProjection(Projections.rowCount()).uniqueResult().hashCode();
	}
}
