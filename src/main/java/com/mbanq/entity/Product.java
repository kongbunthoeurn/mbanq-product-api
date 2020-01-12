package com.mbanq.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name", unique = true, nullable = false)
	private String name;
	@Column(name = "price")
	private float price;
	@Column(name = "created_by")
	private String created_by;

	@Column(name = "mod_by")
	private String mod_by;

	@Column(name = "created_date")
	@JsonFormat(pattern = "yyyy-MM-dd HH")
	private Timestamp created_date;

	@Column(name = "mod_date")
	@JsonFormat(pattern = "yyyy-MM-dd HH")
	private Timestamp mod_date;

	public Product(int id, String name, float price, String created_by, String mod_by, Timestamp created_date,
			Timestamp mod_date) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.created_by = created_by;
		this.mod_by = mod_by;
		this.created_date = created_date;
		this.mod_date = mod_date;
	}

	public Product(String name, float price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Product(int id, String name, float price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getMod_by() {
		return mod_by;
	}

	public void setMod_by(String mod_by) {
		this.mod_by = mod_by;
	}

	public Timestamp getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Timestamp created_date) {
		this.created_date = created_date;
	}

	public Timestamp getMod_date() {
		return mod_date;
	}

	public void setMod_date(Timestamp mod_date) {
		this.mod_date = mod_date;
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", created_by=" + created_by + ", mod_by="
				+ mod_by + ", created_date=" + created_date + ", mod_date=" + mod_date + "]";
	}

	public boolean checkValidate() {
		boolean check = true;
		if (this.name.trim() == "" || this.name.trim() == null)
			check = false;
		return check;
	}

	public boolean checkValidateUpdate() {
		boolean check = true;
		if (this.name.trim() == "" || this.name.trim() == null)
			check = false;
		return check;
	}

}
