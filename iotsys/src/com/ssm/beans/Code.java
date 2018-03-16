package com.ssm.beans;



@SuppressWarnings("serial")
public class Code  implements
		java.io.Serializable {
	private Integer id;
	private Integer parentid;
	private String codetype;
	private String codekey;
	private String codevalue;
	private Integer sort;
	private String description;

	// Constructors

	/** default constructor */
	public Code() {
	}

	/** full constructor */
	public Code(Integer parentid, String codetype, String codekey,
			String codevalue, Integer sort, String description) {
		this.parentid = parentid;
		this.codetype = codetype;
		this.codekey = codekey;
		this.codevalue = codevalue;
		this.sort = sort;
		this.description = description;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentid() {
		return this.parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public String getCodetype() {
		return this.codetype;
	}

	public void setCodetype(String codetype) {
		this.codetype = codetype;
	}

	public String getCodekey() {
		return this.codekey;
	}

	public void setCodekey(String codekey) {
		this.codekey = codekey;
	}

	public String getCodevalue() {
		return this.codevalue;
	}

	public void setCodevalue(String codevalue) {
		this.codevalue = codevalue;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Code [id=" + id + ", parentid=" + parentid + ", codetype="
				+ codetype + ", codekey=" + codekey + ", codevalue="
				+ codevalue + ", sort=" + sort + ", description=" + description
				+ "]";
	}

}
