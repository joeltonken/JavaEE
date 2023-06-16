package model;

// TODO: Auto-generated Javadoc
/**
 * The Class JavaBeans.
 */
public class JavaBeans {
	
	/** The idcon. */
	private String idcon;
	
	/** The nome. */
	private String nome;
	
	/** The idade. */
	private String idade;
	
	/** The peso. */
	private double peso;
	
	/** The altura. */
	private double altura;

	/**
	 * Instantiates a new java beans.
	 */
	public JavaBeans() {
		super();
	}

	/**
	 * Instantiates a new java beans.
	 *
	 * @param idcon the idcon
	 * @param nome the nome
	 * @param idade the idade
	 * @param peso the peso
	 * @param altura the altura
	 */
	public JavaBeans(String idcon, String nome, String idade, double peso, double altura) {
		super();
		this.idcon = idcon;
		this.nome = nome;
		this.idade = idade;
		this.peso = peso;
		this.altura = altura;
	}

	/**
	 * Gets the idcon.
	 *
	 * @return the idcon
	 */
	public String getIdcon() {
		return idcon;
	}

	/**
	 * Sets the idcon.
	 *
	 * @param idcon the new idcon
	 */
	public void setIdcon(String idcon) {
		this.idcon = idcon;
	}

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the idade.
	 *
	 * @return the idade
	 */
	public String getIdade() {
		return idade;
	}

	/**
	 * Sets the idade.
	 *
	 * @param idade the new idade
	 */
	public void setIdade(String idade) {
		this.idade = idade;
	}

	/**
	 * Gets the peso.
	 *
	 * @return the peso
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * Sets the peso.
	 *
	 * @param peso the new peso
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}

	/**
	 * Gets the altura.
	 *
	 * @return the altura
	 */
	public double getAltura() {
		return altura;
	}

	/**
	 * Sets the altura.
	 *
	 * @param altura the new altura
	 */
	public void setAltura(double altura) {
		this.altura = altura;
	}

	/**
	 * Calcular IMC.
	 *
	 * @return the double
	 */
	public double calcularIMC() {
		double imc = peso / (altura * altura);
		return imc;
	}

}
