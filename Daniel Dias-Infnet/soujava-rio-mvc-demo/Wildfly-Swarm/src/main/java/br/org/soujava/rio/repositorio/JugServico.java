package br.org.soujava.rio.repositorio;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.org.soujava.rio.model.JUG;

/**
 * @author Daniel Dias
 * github:Daniel-Dos
 * daniel.dias@soujava.org.br
 * twitter:@danieldiasjava
 */

@Transactional
public class JugServico {

	@Inject 
	private JUGDAO dao;

	public void adicionaJug(JUG pessoa) {
		dao.save(pessoa);
	}

	public List<JUG> listarJUGS () {
		return dao.findAll();
	}

	public void excluirJug(int id) {
		dao.delete(id);
	}

	public JUG getJugPorId(int id) {

		return dao.findById(id);
	}

	public void atualizaJug(JUG jug) {
		dao.update(jug);
	}
}