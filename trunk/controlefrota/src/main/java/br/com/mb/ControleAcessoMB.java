package br.com.mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.tree.Tree;
import org.primefaces.event.TreeDragDropEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.com.dao.ControleAcessoDAO;
import br.com.dao.MenuDAO;
import br.com.dao.PerfilDAO;
import br.com.dto.MenuDTO;
import br.com.dto.PerfilDTO;
import br.com.dto.PerfilMenuDTO;
import br.com.dto.PermissaoMenuDTO;
import br.com.dto.UsuarioDTO;

@ManagedBean
@ViewScoped
public class ControleAcessoMB extends GenericoMB{

	private TreeNode root;
	private TreeNode selectedNode;
	private TreeNode[] nosSelecionados;
	private UsuarioDTO usuarioSelecionado = new UsuarioDTO();
	private List<UsuarioDTO> listaUsuarioDTO = new ArrayList<UsuarioDTO>();
	private List<PerfilMenuDTO> listaPermissao = new ArrayList<PerfilMenuDTO>();
	private List<MenuDTO> listaMenuDTO = new ArrayList<MenuDTO>();
	private List<MenuDTO> menusPermitidos = new ArrayList<MenuDTO>();
	private List<MenuDTO> menusUsuarioDTO = new ArrayList<MenuDTO>();
	private ControleAcessoDAO permissaoDAO = new ControleAcessoDAO();
	private MenuDAO menuDAO = new MenuDAO();
	private List<PerfilDTO> listaPerfil = new ArrayList<PerfilDTO>();
	private PerfilDAO perfilDAO = new PerfilDAO();
	private PerfilDTO perfil = new PerfilDTO();

	private TreeNode rootInactive;
	private TreeNode[] nosSelecionadosI;

	public ControleAcessoMB() {
		try {
			listaPerfil = perfilDAO.list();
		} catch (Exception ex) {
			Logger.getLogger(ControleAcessoMB.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/* Getters e Setters */
	/*
    private boolean isFilho(String[] indicesPai, String[] indicesFilho) {
        boolean isFilho = false;
        int i = 0;
        if (indicesPai.length == indicesFilho.length - 1) {
            for (String string : indicesPai) {
                isFilho = string.equals(indicesFilho[i]);
                if (!isFilho) {
                    break;
                }
                i++;
            }
        }
        return isFilho;
    }*/
	/*
    private void carregarFilhos(List<MenuDTO> menus, MenuDTO menu, TreeNode treeNode) {
        for (MenuDTO m : menus) {
            String[] indicesPai = menu.getIndice().split("\\.");
            String[] indicesFilho = m.getIndice().split("\\.");
            TreeNode tr;
            if ((indicesFilho.length > indicesPai.length)) {
                if (isFilho(indicesPai, indicesFilho)) {
                    if (m.getUrl().trim().equals("")) {
                        tr = new DefaultTreeNode(m, treeNode);
                        carregarFilhos(menus, m, tr);
                    } else {
                        tr = new DefaultTreeNode(m, treeNode);
                        if (isPermitido(m)) {
                            tr.setSelected(true);
                        }
                    }
                }
            }
        }
    }
	 */

	public void carregarFilhos(MenuDTO menu, TreeNode treeNode) throws Exception{
		List<MenuDTO> listaMenuDTO = menuDAO.listByIdSub(menu.getId());
		TreeNode tr;
		for(MenuDTO m:listaMenuDTO){
			if(StringUtils.isBlank(m.getUrl()) && StringUtils.isBlank(m.getOutcome()) ){
				if(rb.containsKey(m.getNome())){
					m.setNome(rb.getString(m.getNome()));
				}
				tr = new DefaultTreeNode(m, treeNode);
				carregarFilhos(m, tr);

			}else{
				if(rb.containsKey(m.getNome())){
					m.setNome(rb.getString(m.getNome()));
				}
				tr = new DefaultTreeNode(m, treeNode);
				//verifica se � permitidos apenas par ao ultimo node
				if (isPermitido(m)) {
					tr.setSelected(true);
				}
			}
		}
	}

	private boolean isPermitido(MenuDTO menu) {
		boolean retorno = false;
		for (MenuDTO m : menusPermitidos) {
			if (m.getId().equals(menu.getId())) {
				retorno = true;
				break;
			}
		}
		return retorno;
	}

	public void carregaPermissoesUsuarioDTO() {
		permissaoDAO = new ControleAcessoDAO();
		menuDAO = new MenuDAO();
		root = new DefaultTreeNode("root", null);
		rootInactive = new DefaultTreeNode("rootInactive", null);
		menusPermitidos = new ArrayList<MenuDTO>();
		List<MenuDTO> listMenuInativo;
		try {
			listaPermissao = permissaoDAO.listByIdPerfil(perfil.getId());
			listaMenuDTO = menuDAO.listCabecalho();
			listMenuInativo = menuDAO.listInativos();
			//pega todos os menus permitidos para esse usuario
			for (PerfilMenuDTO permissao : listaPermissao) {
				menusPermitidos.add(permissao.getMenuDTO());
			}
			if (perfil.getId() != 0) {
				for (MenuDTO menu : listaMenuDTO) {
					if(rb.containsKey(menu.getNome())){
						menu.setNome(rb.getString(menu.getNome()));
					}
					TreeNode treeNode = new DefaultTreeNode(menu, root);
					if (isPermitido(menu)) {
						treeNode.setSelected(true);
					}
					carregarFilhos(menu, treeNode);
				}
				for (MenuDTO menu : listMenuInativo) {
					if(rb.containsKey(menu.getNome())){
						menu.setNome(rb.getString(menu.getNome()));
					}
					TreeNode treeNode = new DefaultTreeNode(menu, rootInactive);
					carregarFilhos(menu, treeNode);
				}if(listMenuInativo.isEmpty()){
					//					new DefaultTreeNode(new MenuDTO() , rootInactive);
					//rootInactive.getChildren();
					rootInactive.isExpanded();
					MenuDTO menu = new MenuDTO();
					menu.setId(0);
					menu.setNome("Vazio");
					TreeNode treeNode = new DefaultTreeNode(new MenuDTO() , rootInactive);
					carregarFilhos(menu, treeNode);
				}
			}
		} catch (Exception ex) {
			Logger.getLogger(ControleAcessoMB.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void salvaPermissoes() {
		if (perfil.getId() == 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("noUserSelected"), rb.getString("error")));
		} else {
			try {
				permissaoDAO.deletePermissaoPorPerfil(perfil.getId());
				for (TreeNode no : nosSelecionados) {
					if (no.isLeaf()) {
						PerfilMenuDTO perfilMenuDTO = new PerfilMenuDTO();
						perfilMenuDTO.setMenuDTO((MenuDTO) no.getData());
						perfilMenuDTO.setPerfilDTO(perfil);

						permissaoDAO.save(perfilMenuDTO);
						salvaPai(no);
					}
				}
				carregaPermissoesUsuarioDTO();
				addMessage(rb.getString("positionChangedMenu"));
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Permiss�es Salvas", "Permiss�es Salvas"));
			} catch (Exception ex) {
				Logger.getLogger(ControleAcessoMB.class.getName()).log(Level.SEVERE, null, ex);
				addMessage(ex.getMessage());
			}
		}
	}

	public void salvaPai(TreeNode no) throws Exception {
		TreeNode tr = no.getParent();
		if (!tr.equals(root)) {
			PermissaoMenuDTO p;
			try {
				p = permissaoDAO.buscaPorMenuPerfil(((MenuDTO) tr.getData()).getId(), perfil.getId());
				if (p == null) {
					PerfilMenuDTO perfilMenuDTO = new PerfilMenuDTO();
					perfilMenuDTO.setPerfilDTO(perfil);
					perfilMenuDTO.setMenuDTO((MenuDTO) tr.getData());
					permissaoDAO.save(perfilMenuDTO);
				}
				salvaPai(tr);
			} catch (SQLException ex) {
				Logger.getLogger(ControleAcessoMB.class.getName()).log(Level.SEVERE, null, ex);
				throw ex;
			}catch (Exception e) {
				throw e;
				//addMessage(e.getMessage());
			}
		}
	}

	public void onDragDrop(TreeDragDropEvent event) {
		try{
			TreeNode dragNode = event.getDragNode();
			TreeNode dropNode = event.getDropNode();
			int dropIndex = event.getDropIndex();
			MenuDTO m = (MenuDTO) dragNode.getData();
			 m = menuDAO.getById(m.getId());
			 
			if(dropNode.getData().equals("root")){
				m.setMenuDTO(null);
			}else{
				m.setDropIndex(dropIndex);
				m.setMenuDTO(((MenuDTO)dropNode.getData()));
			}
			menuDAO.save(m);

			Tree tree = (Tree) event.getSource();//percorre toda a lista para organizar a orderm de exibi��o do menu
			for (TreeNode tn : tree.getValue().getChildren()) {
				MenuDTO menu = (MenuDTO) tn.getData();

				menu.setDropIndex(Integer.valueOf(tn.getRowKey().length() > 1 ? tn.getRowKey().substring(0,1):tn.getRowKey()));
				if(tn.getParent().getData().equals("root")){
					menu.setAtivoInativo(true);
				}else{
					menu.setAtivoInativo(false);
				}
//				if(dropNode.getData().equals("root")){
//					menu.setMenuDTO(null);
//				}else{
//					m.setMenuDTO(((MenuDTO)dropNode.getData()));
//					menuDAO.saveOnDragDrop(m);
//					menu.setMenuDTO(((MenuDTO)dropNode.getData()));
//				}
				//menu.setNome(null);//para update dinamico
				menuDAO.saveOnDragDrop(menu);
			}
			addMessage(rb.getString("positionChangedMenu"));
			//FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dragged " + dragNode.getData(), "Dropped on " + dropNode.getData() + " at " + dropIndex); 
			System.out.println("Dragged " + dragNode.getData()+ "Dropped on " + dropNode.getData() + " at " + dropIndex);
			//FacesContext.getCurrentInstance().addMessage(null, message);
		}catch(Exception e){
			e.printStackTrace();
			addMessage(e.getMessage());
		}
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public TreeNode[] getNosSelecionados() {
		return nosSelecionados;
	}

	public void setNosSelecionados(TreeNode[] nosSelecionados) {
		this.nosSelecionados = nosSelecionados;
	}

	public UsuarioDTO getUsuarioDTOSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioDTOSelecionado(UsuarioDTO usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public List<UsuarioDTO> getListaUsuarioDTO() {
		return listaUsuarioDTO;
	}

	public void setListaUsuarioDTO(List<UsuarioDTO> listaUsuarioDTO) {
		this.listaUsuarioDTO = listaUsuarioDTO;
	}

	public List<PerfilMenuDTO> getListaPermissao() {
		return listaPermissao;
	}

	public void setListaPermissao(List<PerfilMenuDTO> listaPermissao) {
		this.listaPermissao = listaPermissao;
	}

	public List<MenuDTO> getListaMenuDTO() {
		return listaMenuDTO;
	}

	public void setListaMenuDTO(List<MenuDTO> listaMenuDTO) {
		this.listaMenuDTO = listaMenuDTO;
	}

	public List<MenuDTO> getMenuDTOsPermitidos() {
		return menusPermitidos;
	}

	public void setMenuDTOsPermitidos(List<MenuDTO> menusPermitidos) {
		this.menusPermitidos = menusPermitidos;
	}

	public List<MenuDTO> getMenuDTOsUsuarioDTO() {
		return menusUsuarioDTO;
	}

	public void setMenuDTOsUsuarioDTO(List<MenuDTO> menusUsuarioDTO) {
		this.menusUsuarioDTO = menusUsuarioDTO;
	}

	public ControleAcessoDAO getPermissaoDAO() {
		return permissaoDAO;
	}

	public void setPermissaoDAO(ControleAcessoDAO permissaoDAO) {
		this.permissaoDAO = permissaoDAO;
	}


	public List<PerfilDTO> getListaPerfil() {
		return listaPerfil;
	}

	public void setListaPerfil(List<PerfilDTO> listaPerfil) {
		this.listaPerfil = listaPerfil;
	}

	public PerfilDTO getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilDTO perfil) {
		this.perfil = perfil;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public TreeNode getRootInactive() {
		return rootInactive;
	}

	public void setRootInactive(TreeNode rootInactive) {
		this.rootInactive = rootInactive;
	}

	public TreeNode[] getNosSelecionadosI() {
		return nosSelecionadosI;
	}

	public void setNosSelecionadosI(TreeNode[] nosSelecionadosI) {
		this.nosSelecionadosI = nosSelecionadosI;
	}
}


