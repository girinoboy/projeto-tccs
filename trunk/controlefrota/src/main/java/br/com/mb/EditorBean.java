/**
 * 
 */
package br.com.mb;

import javax.faces.bean.ManagedBean;

/**
 * @author Marcleônio
 *
 */

@ManagedBean(name = "editor")
public class EditorBean {

	private String value = "This editor is provided by PrimeFaces";

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}