package com.stefanini.hackathon2.managed.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import com.stefanini.hackathon2.entidades.Livro;
import com.stefanini.hackathon2.servicos.LivroServico;
import com.stefanini.hackathon2.util.Mensageiro;

@ManagedBean
@ViewScoped
public class LivroManagedBean {

	private Livro livro;
	private List<Livro> listaDeLivrosCadastrados;
	private StreamedContent imagem;

	@Inject
	private LivroServico servico;

	public LivroManagedBean() {
	}

	public void salvar() {
		servico.salvar(getLivro());
		Mensageiro.notificaInformacao("Parabéns!", "Livro salvo com sucesso!");
		carregaListaDeLivros();
		limpar();
	}

	public void deletar(Livro livro) {
		servico.deletar(livro);
		Mensageiro.notificaInformacao("Parabéns!", "Livro deletado com sucesso!");
		carregaListaDeLivros();
		limpar();
	}

	public void limpar() {
		setLivro(new Livro());
	}

	private void carregaListaDeLivros() {
		setListaDeLivrosCadastrados(servico.carregaTodosLivrosDoBanco());
	}

	public List<Livro> getListaDeLivrosCadastrados() {
		if (listaDeLivrosCadastrados == null) {
			carregaListaDeLivros();
		}
		return listaDeLivrosCadastrados;
	}

	public void setListaDeLivrosCadastrados(List<Livro> listaDeLivrosCadastrados) {
		this.listaDeLivrosCadastrados = listaDeLivrosCadastrados;
	}

	public Livro getLivro() {
		if (livro == null) {
			limpar();
		}
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public StreamedContent getImagem() {
		return imagem;
	}

	public void setImagem(StreamedContent imagem) {
		this.imagem = imagem;
	}

	public void uploadFoto(FileUploadEvent event) {
		try {
			imagem = new DefaultStreamedContent(event.getFile().getInputstream());
			byte[] foto = event.getFile().getContents();
			this.livro.setFoto(foto);
		} catch (IOException ex) {
		}
	}

	public String getFoto(byte[] fotoArray) throws IOException {
		File file = new File("C://Users//dpvillanova//Desktop//fotosTemp//foto.jpg");
		OutputStream outputStream = new FileOutputStream(file);
		outputStream.write(fotoArray);
		outputStream.close();
		return file.getPath();
	}

}
