package com.stefanini.hackathon2.managed.beans;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import com.stefanini.hackathon2.entidades.Livro;
import com.stefanini.hackathon2.servicos.LivroServico;
import com.stefanini.hackathon2.util.Mensageiro;

@ManagedBean
@ViewScoped
public class LivroManagedBean {

	private Livro livro;
	private List<Livro> listaDeLivrosCadastrados;

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

	public void uploadFoto(FileUploadEvent event) {
		byte[] foto = event.getFile().getContents();
		this.livro.setFoto(foto);
	}

	public String getFoto(byte[] fotoArray) throws Exception {
		String localSave = ("C:\\dev\\Workspaces\\Workspace-Eclipse-Mars2\\HackathonV2\\WebContent\\resources\\fotosDoBanco\\");
		File file = new File(localSave + fotoArray + ".jpg");
		try {
			FileOutputStream outputStream = new FileOutputStream(file);
			outputStream.write(fotoArray);
			FileDescriptor fd = outputStream.getFD();
			outputStream.flush();
			fd.sync();
			outputStream.close();
		} catch (Exception e) {
			throw new Exception("Erro ao converter os bytes recebidos para imagem");

		}
		return file.getPath();
	}

}
