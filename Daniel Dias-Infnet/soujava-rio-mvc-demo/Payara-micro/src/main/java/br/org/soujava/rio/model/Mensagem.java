package br.org.soujava.rio.model;

import java.io.Serializable;

import javax.inject.Named;
import javax.mvc.annotation.RedirectScoped;

import lombok.Data;

/**
 * @author Daniel Dias
 * github:Daniel-Dos
 * daniel.dias@soujava.org.br
 * twitter:@danieldiasjava
 */
@Named("mensagem")
@Data
@RedirectScoped
public class Mensagem implements Serializable{

	private static final long serialVersionUID = 1L;

	private String mensagem;
}