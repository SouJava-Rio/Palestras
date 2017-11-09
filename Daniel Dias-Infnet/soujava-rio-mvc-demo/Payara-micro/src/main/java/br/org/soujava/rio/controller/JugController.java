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

package br.org.soujava.rio.controller;


import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.annotation.View;
import javax.mvc.binding.BindingResult;
import javax.mvc.binding.ValidationError;
import javax.validation.Valid;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.BeanParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.types.ObjectId;

import br.org.soujava.rio.model.JUG;
import br.org.soujava.rio.model.Mensagem;
import br.org.soujava.rio.repositorio.JugServico;

@Path("jug")
public class JugController {

	@Inject
	private Models models;

	@Inject
	private BindingResult bindingResult;
	
	@Inject
	private Mensagem mensagem;
	
	@Inject
	private JugServico jugServico;
	
	@GET
	@Controller
	@Path("formulario")
	@View("adicionarRegistros.jsp")
	public void regitrar() {
		List<String> paises = Arrays.stream(Locale.getISOCountries())
				.map(iso -> getCountryName(iso))
				.sorted((a, b) -> a.compareTo(b))
				.collect(Collectors.toList());
			models.put("paises", paises);
	}
	
	@GET
	@Controller
	@Path("editarRegistro/{id}")
	@View("editarRegistros.jsp")
	public void atualizar(@PathParam("id") ObjectId id) {
		models.put("update", jugServico.getJugPorId(id));

		List<String> paises = Arrays.stream(Locale.getISOCountries())
				.map(iso -> getCountryName(iso))
				.sorted((a, b) -> a.compareTo(b))
				.collect(Collectors.toList());
			models.put("paises", paises);
	}

	@POST
	@Controller
	@ValidateOnExecution(type = ExecutableType.NONE)
    @Path("update")
    public String atualizar(@Valid @BeanParam JUG jug) {
		if(bindingResult.isFailed()) {
			String errors = bindingResult.getAllValidationErrors().stream()
					.map(ValidationError::getMessage)
					.collect(Collectors.joining("<br>"));
			models.put("errors", errors);
			return "editarRegistros.jsp";
		}

		jugServico.atualizaJug(jug);
		mensagem.setMensagem("Sucesso ao editar.");
		return "redirect:jug/mostrar";
    }
	
	@POST
	@Path("form")
	@Controller
	@ValidateOnExecution(type = ExecutableType.NONE)
	public String registro(@Valid @BeanParam  JUG jug) {
		if(bindingResult.isFailed()) {
			String errors = bindingResult.getAllValidationErrors().stream()
					.map(ValidationError::getMessage)
					.collect(Collectors.joining("<br>"));
			models.put("errors", errors);
			return "adicionarRegistros.jsp";
		}

		jugServico.adicionaJug(jug);
		System.out.println("Sucesso");
		mensagem.setMensagem("Sucesso ao cadastrar.");
		return "redirect:jug/mostrar";
	}

	@GET
	@Path("mostrar")
	@Controller
	@View("consultarRegistros.jsp")
	public void consultar() {
	}

	@GET
	@Path("listar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<JUG> consultarTodos() {
		return jugServico.listarJUGS();
	}

	@DELETE
    @Path("remover/{id}")
    public void excluirPessoa(@PathParam("id") ObjectId id) {
		jugServico.excluirJug(id);
    }

	private String getCountryName(String iso) {
		return new Locale(iso, iso).getDisplayCountry(Locale.ENGLISH);
	}
}