package com.betha.projeto_manutencao.persistence.dao.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.betha.projeto_manutencao.model.entity.BaseModel;
import com.betha.projeto_manutencao.persistence.dao.CrudDao;

public abstract class AbstractCrudDaoJpa<M extends BaseModel> implements CrudDao<M> {

	public abstract EntityManager getEntityManager();
	public abstract Class<M> getModelClass();
	
	@Override
	public M findById(Serializable id) {
		return findById(id, getModelClass());
	}

	@Override
	public M findById(Serializable id, Class<M> modelClass) {
		return getEntityManager().find(getModelClass(), id);
	}

	public M create(M model) {
		getEntityManager().persist(model);
		return model;
	}

	public M update(M model) {
		getEntityManager().merge(model);
		return model;
	}

	public List<M> find(final String _term) {
		String term = _term == null ? "" : _term;
		final CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<M> cQuery = cBuilder.createQuery(getModelClass());
		final Root<M> root = cQuery.from(getModelClass());
		
		final List<Expression<String>> expressionList = Stream.of(getModelClass().getDeclaredFields())
			.filter(field -> field.getAnnotation(Column.class) != null)
			.filter(field -> field.getType().equals(String.class))
			.map(field -> root.get(field.getName()).as(String.class))
			.collect(Collectors.toList());
		final List<Predicate> predicateList = expressionList.stream()
				.map(exp -> cBuilder.like(cBuilder.lower(exp), "%" + term.toLowerCase() + "%"))
				.collect(Collectors.toList());

		cQuery.select(root).where(cBuilder.or(predicateList.stream().toArray(Predicate[]::new)));
		final TypedQuery<M> query = getEntityManager().createQuery(cQuery);

		return query.getResultList();
	}

	public List<M> findBy(final String fieldName, final Object fieldVal, String term) {
		return findBy(fieldName, fieldVal, term, getModelClass());
	}

	@Override
	public List<M> findBy(final String fieldName, final Object fieldVal, final String term, final Class<M> modelClass) {
		final CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<M> cQuery = cBuilder.createQuery(modelClass);
		final Root<M> root = cQuery.from(modelClass);
		
		final Predicate filterPredicate = cBuilder.equal(root.get(fieldName), fieldVal);
		
		if (term != null) {
			final List<Expression<String>> expressionList = Stream.of(modelClass.getDeclaredFields())
				.filter(field -> field.getAnnotation(Column.class) != null)
				.filter(field -> field.getType().equals(String.class))
				.map(field -> root.get(field.getName()).as(String.class))
				.collect(Collectors.toList());
			final List<Predicate> predicateList = expressionList.stream()
					.map(exp -> cBuilder.like(cBuilder.lower(exp), "%" + term.toLowerCase() + "%"))
					.collect(Collectors.toList());

			cQuery.select(root).where(cBuilder.and(filterPredicate, cBuilder.or(predicateList.stream().toArray(Predicate[]::new))));
		} else {
			cQuery.select(root).where(filterPredicate);
		}
		final TypedQuery<M> query = getEntityManager().createQuery(cQuery);
		return query.getResultList();
	}

}
