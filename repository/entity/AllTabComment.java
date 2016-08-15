package zany.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the ALL_TAB_COMMENTS database table.
 * 
 */
@Entity
@Table(name="ALL_TAB_COMMENTS")
public class AllTabComment implements Serializable {
	private static final long serialVersionUID = 1L;

	private String comments;

	private String owner;

	@Id
	private String tableName;
	
	@Column(name="TABLE_TYPE")
	private String tableType;

	public AllTabComment() {
	}

	public String getComments() {
		return this.comments;
	}

	public String getOwner() {
		return this.owner;
	}

	public String getTableName() {
		return this.tableName;
	}

	public String getTableType() {
		return this.tableType;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

}