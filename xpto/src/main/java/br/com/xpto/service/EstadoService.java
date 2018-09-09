package br.com.xpto.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.com.xpto.model.Estado;
import br.com.xpto.repository.EstadoRepository;

@Service
@Transactional
public class EstadoService implements EstadoRepository, Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	EntityManager manager;

	@Override
	public Estado findEstado(String estado) {
		Session session = this.manager.unwrap(Session.class);
		Criteria cr = session.createCriteria(Estado.class);

		cr.add(Restrictions.eq("uf", estado));
		cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (Estado) cr.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Estado> all() {
		Session session = this.manager.unwrap(Session.class);
		Criteria cr = session.createCriteria(Estado.class);
		cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return cr.list();
	}

}
