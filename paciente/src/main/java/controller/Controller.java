package controller;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The paciente. */
	JavaBeans paciente = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/main")) {
			pacientes(request, response);
		} else if (action.equals("/insert")) {
			adicionarPaciente(request, response);
		} else if (action.equals("/select")) {
			listarPaciente(request, response);
		} else if (action.equals("/update")) {
			editarPaciente(request, response);
		} else if (action.equals("/delete")) {
			removerPaciente(request, response);
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	/**
	 * Pacientes.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void pacientes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<JavaBeans> lista = dao.listarPacientes();
		request.setAttribute("pacientes", lista);
		RequestDispatcher rd = request.getRequestDispatcher("paciente.jsp");
		rd.forward(request, response);
	}

	/**
	 * Adicionar paciente.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void adicionarPaciente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		paciente.setNome(request.getParameter("nome"));
		paciente.setIdade(request.getParameter("idade"));
		paciente.setPeso(Double.parseDouble(request.getParameter("peso")));
		paciente.setAltura(Double.parseDouble(request.getParameter("altura")));
		paciente.calcularIMC();
		dao.inserirPaciente(paciente);
		response.sendRedirect("main");
	}

	/**
	 * Listar paciente.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void listarPaciente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		paciente.setIdcon(request.getParameter("idcon"));
		dao.selecionarPaciente(paciente);
		request.setAttribute("idcon", paciente.getIdcon());
		request.setAttribute("nome", paciente.getNome());
		request.setAttribute("idade", paciente.getIdade());
		request.setAttribute("peso", paciente.getPeso());
		request.setAttribute("altura", paciente.getAltura());
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	/**
	 * Editar paciente.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarPaciente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		paciente.setIdcon(request.getParameter("idcon"));
		paciente.setNome(request.getParameter("nome"));
		paciente.setIdade(request.getParameter("idade"));
		paciente.setPeso(Double.parseDouble(request.getParameter("peso")));
		paciente.setAltura(Double.parseDouble(request.getParameter("altura")));
		dao.alterarPaciente(paciente);
		response.sendRedirect("main");
	}

	/**
	 * Remover paciente.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void removerPaciente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		paciente.setIdcon(request.getParameter("idcon"));
		dao.deletarPaciente(paciente);
		response.sendRedirect("main");
	}

	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();
		try {
			response.setContentType("apllication/pdf");
			response.addHeader("Content-Disposition", "inline; filename=" + "pacientes.pdf");
			PdfWriter.getInstance(documento, response.getOutputStream());
			documento.open();
			documento.add(new Paragraph("Lista de pacientes:"));
			documento.add(new Paragraph(" "));
			PdfPTable tabela = new PdfPTable(5);
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Idade"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Peso (KG)"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Altura (M)"));
			PdfPCell col5 = new PdfPCell(new Paragraph("IMC"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			tabela.addCell(col5);
			ArrayList<JavaBeans> lista = dao.listarPacientes();
			for (int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getIdade());
				double peso = lista.get(i).getPeso();
				double altura = lista.get(i).getAltura();
				String pesoString = String.format("%.2f", peso);
				String alturaString = String.format("%.2f", altura);
				tabela.addCell(pesoString);
				tabela.addCell(alturaString);
				double imc = peso / (altura * altura);
				String imcString = String.format("%.2f", imc);
				tabela.addCell(imcString);
			}
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}
}
