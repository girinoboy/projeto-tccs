/**
 * 
 */
package br.com.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.TreeDragDropEvent;
import org.primefaces.model.TreeNode;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import br.com.dao.MenuDAO;
import br.com.dao.PerfilMenuDAO;
import br.com.dto.MenuDTO;
import br.com.dto.PerfilMenuDTO;

/**
 * @author marcleonio.medeiros
 *
 */
@ManagedBean
@RequestScoped
public class MenuMB extends GenericoMB implements ModeloMB{

	private MenuModel model;
	private MenuDAO menuDAO = new MenuDAO();
	private PerfilMenuDAO perfilMenuDAO = new PerfilMenuDAO();
	private List<MenuDTO>  listMenu = new ArrayList<MenuDTO>();

	/**
	 * 
	 */
	public MenuMB() {

		try {
			atualiza(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void atualiza(ActionEvent event) throws Exception {
		listMenu = menuDAO.list();
		geraMenu(perfilMenuDAO.getMenuByIdPerfil(getUserSession().getPerfilDTO().getId()));
	}
	
	@Override
	public void reset(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

	private void geraMenu(List<PerfilMenuDTO> list) throws Exception {
		model = new DefaultMenuModel();
		DefaultMenuItem item;
		DefaultSubMenu submenu;
		for (PerfilMenuDTO perfilMenuDTO : list) {
			MenuDTO menu = perfilMenuDTO.getMenuDTO();
			if(StringUtils.isBlank(menu.getUrl()) && StringUtils.isBlank(menu.getOutcome()) && StringUtils.isBlank(menu.getComando())){
				if(rb.containsKey(menu.getNome())){
					submenu = new DefaultSubMenu(rb.getString(menu.getNome()));
				}else{
					submenu = new DefaultSubMenu(menu.getNome());
				}
				geraFilhosMenu(menu,submenu);
				model.addElement(submenu);
			}else{
				if(rb.containsKey(menu.getNome())){
					item = new DefaultMenuItem(rb.getString(menu.getNome()));
				}else{
					item = new DefaultMenuItem(menu.getNome());
				}
				item.setIcon(menu.getIconeNativo());
				item.setUrl(menu.getUrl());
				item.setOutcome(menu.getOutcome());
				item.setCommand(menu.getComando());
				//item.setUpdate(":centro");
				model.addElement(item);
			}
		}
	}

	private void geraFilhosMenu(MenuDTO menuA, DefaultSubMenu submenu) throws Exception {
		List<MenuDTO> listaMenu = menuDAO.listByIdSub(menuA.getId());
		DefaultMenuItem item;
		DefaultSubMenu sm;
		for(MenuDTO menu:listaMenu){
			if(StringUtils.isBlank(menu.getUrl()) && StringUtils.isBlank(menu.getOutcome()) && StringUtils.isBlank(menu.getComando())){
				if(rb.containsKey(menu.getNome())){
					sm = new DefaultSubMenu(rb.getString(menu.getNome()));
				}else{
					sm = new DefaultSubMenu(menu.getNome());
				}
				geraFilhosMenu(menu,submenu);
				submenu.addElement(sm);
			}else{
				if(rb.containsKey(menu.getNome())){
					item = new DefaultMenuItem(rb.getString(menu.getNome()));
				}else{
					item = new DefaultMenuItem(menu.getNome());
				}
				item.setIcon(menu.getIconeNativo());
				item.setUrl(menu.getUrl());
				item.setOutcome(menu.getOutcome());
				item.setCommand(menu.getComando());//"#{menuBean.delete}"
				submenu.addElement(item);
			}
		}

	}
	
	@Override
	public void add(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void onDragDrop(TreeDragDropEvent event) {  
        TreeNode dragNode = event.getDragNode();  
        TreeNode dropNode = event.getDropNode();  
        int dropIndex = event.getDropIndex();  
          
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dragged " + dragNode.getData(), "Dropped on " + dropNode.getData() + " at " + dropIndex);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	public void onEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Car Edited", ((MenuDTO) event.getObject()).getNome());
 
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Car Cancelled", ((MenuDTO) event.getObject()).getNome());
 
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	public List<MenuDTO> getListMenu() {
		return listMenu;
	}

	public void setListMenu(List<MenuDTO> listMenu) {
		this.listMenu = listMenu;
	}

}
