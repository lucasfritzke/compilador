package Util;

import java.util.Formatter;
import java.util.Locale;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import classesGals.LexicalError;
import classesGals.Lexico;
import classesGals.Token;

public class CompiladorUtil {

	private ArrayList components = new ArrayList();
	private String path = null;
	private String copiedText = null;

	public void addComponent(Component comp) {
		components.add(comp);
	}

	public void metodoNovo() {
		// Inicializa para comporar classes
		JTextArea jTextArea = new JTextArea();
		JTextField jTextField = new JTextField();
		this.path = null;

		jTextField = (JTextField) getComponentByName("StatusBar");
		jTextField.setText("");

		jTextArea = (JTextArea) getComponentByName("CodeBlock");
		jTextArea.setText("");

		jTextArea = (JTextArea) getComponentByName("MessageBlock");
		jTextArea.setText("");

	}

	public void metodoAbrir() {
		// Checando se o caminho foi alterado
		String pathTemp = null;
		FileDialog fd = new FileDialog(new JFrame());
		fd.setFile("*.txt");
		fd.setVisible(true);

		File[] f = fd.getFiles();

		if (f.length > 0) {
			pathTemp = fd.getFiles()[0].getAbsolutePath();
		}

		if (pathTemp != null && !pathTemp.equals(path)) {
			this.path = pathTemp;
			try {
				File file = new File(path);
				Scanner myReader = new Scanner(file);
				String data = "";
				while (myReader.hasNextLine()) {
					data += myReader.nextLine() + "\n";
				}
				JTextField jTextField = (JTextField) getComponentByName("StatusBar");
				jTextField.setText(path);

				JTextArea jTextArea = (JTextArea) getComponentByName("CodeBlock");
				jTextArea.setText(data);
				myReader.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void metodoSalvar() {
		if (this.path == null) {
			final JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(new JFrame());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					File file = fc.getSelectedFile();

					if (!file.getName().endsWith(".txt")) {
						File rename = new File(file.getAbsolutePath().replace('\\', '/') + ".txt");
						file = rename;
					}
					file.createNewFile();
					this.path = file.getAbsolutePath();

					JTextArea textArea = (JTextArea) getComponentByName("CodeBlock");
					String text = textArea.getText();
					JTextField jTextField = (JTextField) getComponentByName("StatusBar");
					jTextField.setText(path);

					String[] splitString = text.split("\n");
					FileWriter fw = new FileWriter(file);
					BufferedWriter out = new BufferedWriter(fw);
					for (int i = 0; i < splitString.length; i++) {
						out.write(splitString[i]);
					}
					out.flush();
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} else {
			File file = new File(path);
			if (file.exists()) {

				JTextArea textArea = (JTextArea) getComponentByName("CodeBlock");
				String text = textArea.getText();
				JTextField jTextField = (JTextField) getComponentByName("StatusBar");
				jTextField.setText(path);

				String[] splitString = text.split("\n");

				try {
					FileWriter fw = new FileWriter(file);
					BufferedWriter out = new BufferedWriter(fw);
					for (int i = 0; i < splitString.length; i++) {
						out.write(splitString[i] + "\n");
					}
					out.flush();
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	}

	public void metodoCompilar() {

		JTextArea messageBlock = (JTextArea) getComponentByName("MessageBlock");
		JTextArea codeBlock = (JTextArea) getComponentByName("CodeBlock");
		Lexico lexico = new Lexico();
		String codigoFonte = codeBlock.getText();
		int contLinha = 1;
		int contPos = 0;
		ArrayList<String[]> listaTokens = new ArrayList<String[]>();
		StringBuilder tabela = new StringBuilder();

		// Cabecalho
		tabela.append(String.format("%-5s %-18s %-6s%n", "Linha", "Classe", "Lexema"));
		lexico.setInput(codigoFonte);

		try {
			Token t = null;
			while ((t = lexico.nextToken()) != null) {

				// Contador de quebras de linha
				while (contPos <= t.getPosition()) {
					if (codigoFonte.charAt(contPos) == '\n') {
						contLinha++;
					}
					contPos++;
				}

				// Adiciona em um array as informacoes do token
				String[] linha = new String[] { Integer.toString(contLinha), this.verificarClasse(t), t.getLexeme() };
				listaTokens.add(linha);
			}

			// apos reconhecer todos os tokens, é montada a mensagem para o usuario
			while (listaTokens.size() != 0) {
				String[] linha = (String[]) listaTokens.get(0);
				listaTokens.remove(0);

				String linhaFormatada = String.format("%-5s %-18s %-6s%n", linha[0], linha[1], linha[2]);
				tabela.append(linhaFormatada);
			}
			tabela.append("\nPrograma compilado com sucesso");

			messageBlock.setText(tabela.toString());

		} catch (LexicalError e) { // tratamento de erros
			
			// Contador de quebras de linha
			while (contPos <= e.getPosition()) {
				if (codigoFonte.charAt(contPos) == '\n') {
					contLinha++;
				}
				contPos++;
			}

			String str = "";
			contPos = e.getPosition();

			if (e.getMessage().equals("Símbolo inválido")) {
				str += codigoFonte.charAt(contPos);
				
			} else if (e.getMessage().equals("comentário de bloco inválido ou não finalizado") || 
					e.getMessage().equals("constante_string inválida")) {
				messageBlock.setText("Linha " + contLinha + ": " + e.getMessage());
				return;
			} else {
				
			// Caso seja uma palavra reservada ou identificador	
				while (contPos >= 0 && codigoFonte.charAt(contPos) != ' ' && codigoFonte.charAt(contPos) != '\n') {
					contPos--;
				}
				contPos++;
				while (contPos < codigoFonte.length() && codigoFonte.charAt(contPos) != ' '
						&& codigoFonte.charAt(contPos) != '\n') {
					str += codigoFonte.charAt(contPos);
					contPos++;
				}

			}
			messageBlock.setText("Linha " + contLinha + ": " + str + " " + e.getMessage());
		}

	}


	private String verificarClasse(Token t) throws LexicalError {
		int id = t.getId();
		if (id == 2) {
			return "identificador";
		} else if (id == 3) {
			return "constante_int";
		} else if (id == 4) {
			return "constante_float";
		} else if (id == 5) {
			return "constante_string";
		} else if (id > 6 && id <= 17) {
			return "palavra reservada";
		} else if (id > 17) {
			return "Símbolo Especial";
		} else {
			throw new LexicalError("palavra reservada invalida", t.getPosition());
		}

	}

	public void metodoMostraEquipe() {
		JTextArea m = (JTextArea) getComponentByName("MessageBlock");
		m.setText("Equipe:Guilherme W. Back, João Victor Schmidt e Lucas Fritzke");

	}

	public void metodoCopiar() {
		JTextArea jTextArea1 = (JTextArea) getComponentByName("CodeBlock");
		JTextArea jTextArea2 = (JTextArea) getComponentByName("MessageBlock");

		String selected1 = jTextArea1.getSelectedText();
		String selected2 = jTextArea2.getSelectedText();

		if (selected1 != null && !selected1.equals("")) {
			this.copiedText = selected1;
			StringSelection stringSelection = new StringSelection(selected1);
			java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);
		} else if (selected2 != null && !selected2.equals("")) {
			this.copiedText = selected2;
			StringSelection stringSelection = new StringSelection(selected2);
			java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);
		}

	}

	public void metodoColar() {
		if (this.copiedText == null) {
			return;
		}

		JTextArea jTextArea = (JTextArea) getComponentByName("CodeBlock");
		int caret = jTextArea.getCaretPosition();

		char[] selectedText = copiedText.toCharArray();
		char[] textArr = jTextArea.getText().toCharArray();
		char[] newTextArr = new char[textArr.length + selectedText.length];

		// populate the new array ,
		for (int i = 0; i < textArr.length; i++) {
			newTextArr[i] = textArr[i];
		}

		for (int i = caret, z = 0; z < selectedText.length; i++, z++) {
			for (int x = newTextArr.length - 1; x > i; x--) {
				newTextArr[x] = newTextArr[x - 1];
			}
			newTextArr[i] = selectedText[z];
		}

		String text = new String(newTextArr);
		jTextArea.setText(text);

	}

	public void metodoRecortar() {
		JTextArea jTextArea = (JTextArea) getComponentByName("CodeBlock");
		int caret = jTextArea.getCaretPosition();
		char[] selectedText = jTextArea.getSelectedText() != null ? jTextArea.getSelectedText().toCharArray() : null;
		if (selectedText == null) {
			return;
		}
		char[] allText = jTextArea.getText().toCharArray();

		if (allText.length == selectedText.length) {
			String newText = new String(allText);
			this.copiedText = newText;
			StringSelection stringSelection = new StringSelection(this.copiedText);
			java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);
			jTextArea.setText("");
		} else {
			char[] newChar = new char[allText.length - selectedText.length];

			for (int i = 0; i < allText.length - 1; i++) {
				if (caret > i) {
					newChar[i] = allText[i];
				} else {
					for (int z = newChar.length - 1; z >= caret; z--) {
						newChar[z] = allText[selectedText.length + z];
					}
					break;
				}
			}

			String newText = new String(newChar);
			jTextArea.setText(newText);
			newText = new String(selectedText);
			this.copiedText = newText;
			StringSelection stringSelection = new StringSelection(newText);
			java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);
		}
	}

	public Component getComponentByName(String name) {
		for (int i = 0; i < components.size(); i++) {
			Component c = (Component) components.get(i);
			if (c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}

}
