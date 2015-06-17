package com.carm.vetustus.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product_line")
public class ProductLine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6370328332730792541L;
	
	@Id
	@Column(name = "product_line", nullable = false, length = 3)
	private String productLine;
	
	@Column(name = "product_line_name", nullable = false, length = 50)
	private String productLineName;
	
	@Column(name = "text_description", nullable = false)
	private String textDescription;
	
	@Column(name = "html_description", nullable = true)
	private String htmlDescription;
	
	@Column(name = "image", nullable = true)
	private Byte[] image;
	
	@OneToMany(mappedBy = "productLine")
	private Set<Product> products = new HashSet<Product>(0);
	
	public String getProductLine() {
		return productLine;
	}
	
	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}
	
	public String getProductLineName() {
		return productLineName;
	}
	
	public void setProductLineName(String productLineName) {
		this.productLineName = productLineName;
	}
	
	public String getTextDescription() {
		return textDescription;
	}
	
	public void setTextDescription(String textDescription) {
		this.textDescription = textDescription;
	}
	
	public String getHtmlDescription() {
		return htmlDescription;
	}
	
	public void setHtmlDescription(String htmlDescription) {
		this.htmlDescription = htmlDescription;
	}
	
	public Byte[] getImage() {
		return image;
	}
	
	public void setImage(Byte[] image) {
		this.image = image;
	}
	
	public Set<Product> getProducts() {
		return products;
	}
	
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	public String toString() {
		return new StringBuilder("ProductLine: {\n").
				append("\t productLine: ").append(productLine).append(",\n").
				append("\t productLineName: ").append(productLineName).append(",\n").
				append("\t textDescription: ").append(textDescription).append(",\n").
				append("\t htmlDescription: ").append(htmlDescription).append(",\n").
				append("\t image: ").append(image).append("\n").
				append("}").
				toString();
	}

}
