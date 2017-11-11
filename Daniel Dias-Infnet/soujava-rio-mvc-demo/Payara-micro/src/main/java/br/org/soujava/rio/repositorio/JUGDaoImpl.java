/*
 * The MIT License
 * Copyright © 2017 Daniel Dias
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.org.soujava.rio.repositorio;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.bson.types.ObjectId;

import br.org.soujava.rio.model.JUG;

/**
 * @author Daniel Dias
 * github:Daniel-Dos
 * daniel.dias@soujava.org.br
 * twitter:@danieldiasjava
 */

public class JUGDaoImpl implements JUGDAO {

	@Inject
	private EntityManager entityManager;

	public JUGDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void save(JUG entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(JUG entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(ObjectId id) {
		entityManager.remove(findById(id));
	}

	@Override
	public JUG findById(ObjectId id) {
		return entityManager
				.createNamedQuery("JUG.findById", JUG.class)
				.setParameter("id", id.toString())
				.getSingleResult();
	}

	@Override
	public List<JUG> findAll() {
		return entityManager
				.createNamedQuery("JUG.findAll", JUG.class)
				.getResultList();
	}
}