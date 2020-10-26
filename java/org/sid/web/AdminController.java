package org.sid.web;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.sid.dao.AdminRepository;
import org.sid.dao.CourRepository;
import org.sid.dao.DepartementRepository;
import org.sid.dao.EcoleRepository;
import org.sid.dao.FiliereRepository;
import org.sid.dao.LoginRepository;
import org.sid.dao.TypeCourRepisitory;
import org.sid.dao.UtilisateurRepository;
import org.sid.entities.Admin;
import org.sid.entities.Cour;
import org.sid.entities.Departement;
import org.sid.entities.Ecole;
import org.sid.entities.Filiere;
import org.sid.entities.Login;
import org.sid.entities.Roles;
import org.sid.entities.TypeCour;
import org.sid.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class AdminController {
	@Autowired
	private UtilisateurRepository userdao;
	@Autowired
	private AdminRepository admindao;
	@Autowired
	private CourRepository courdao;
	@Autowired
	private FiliereRepository filierdao;
	@Autowired
	private LoginRepository logindao;
	@Autowired
	private TypeCourRepisitory typedao;
	@Autowired
	private DepartementRepository departementdao;
	@Autowired
	private EcoleRepository ecoledao;
   
	@GetMapping("/admin")
	public String def() {
		return "redirect:/admin/home";
	}

	@GetMapping("/403")
	public String notAutorized() {
		return "403";
	}
	

	@GetMapping("/admin/home")
	public String home(Model model, HttpServletRequest  req) {
		int f = filierdao.findAll().size();
		int u = userdao.findAll().size();
		int a = admindao.findAll().size();
		int c = courdao.findAll().size();
		model.addAttribute("user", u);
		model.addAttribute("admin", a);
		model.addAttribute("cour", c);
		model.addAttribute("filiere", f);
		Principal p = req.getUserPrincipal();
		Login login = logindao.findByUsername(p.getName());
		//System.err.println(login.getAdmin().getPrenom());
		return "HomeAdmin";
	}

	@GetMapping("/admin/users")
	public String users(Model model, @RequestParam(name = "page", defaultValue = "0") int page,HttpServletRequest  req,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {
		Page<Utilisateur> pageUsers = userdao.findByNomContains(mc, PageRequest.of(page, 5));

		model.addAttribute("listeUsers", pageUsers);
		model.addAttribute("pages", new int[pageUsers.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("mc", mc);
		return "UserPage";
	}

	@GetMapping("/admin/admins")
	public String admins(HttpServletRequest  req,Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {
		Page<Admin> pageAdmins = admindao.findByNomContains(mc, PageRequest.of(page, 5));
		Principal p = req.getUserPrincipal();
		model.addAttribute("admin",logindao.findByUsername(p.getName()).getAdmin());
		model.addAttribute("listeAdmins", pageAdmins);
		model.addAttribute("pages", new int[pageAdmins.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("mc", mc);
		return "AdminPage";
	}

	@GetMapping("/admin/cours")
	public String cours(HttpServletRequest  req,Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {
		Page<Cour> pageCours = courdao.findByTitleContains(mc, PageRequest.of(page, 5));

		model.addAttribute("listeCours", pageCours);
		model.addAttribute("pages", new int[pageCours.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("mc", mc);
		return "CourPage";
	}

	@GetMapping("/admin/cours/delete")
	public String deleteCours(Model model, Long id, int page, String motCle) {
		courdao.deleteById(id);
		return "redirect:/admin/cours?page=" + page + "&motCle=" + motCle;
	}

	@GetMapping("/admin/users/delete")
	public String deleteUser(Model model, Long id, int page, String motCle) {
		userdao.deleteById(id);
		return "redirect:/admin/users?page=" + page + "&motCle=" + motCle;
	}

	@GetMapping("/admin/admins/delete")
	public String deleteAdmin(Model model, Long id, int page, String motCle) {
		admindao.deleteById(id);
		return "redirect:/admin/admins?page=" + page + "&motCle=" + motCle;
	}

	@RequestMapping(value = "/admin/users/save", method = RequestMethod.POST)
	public String saveUser(Model model, @Valid Utilisateur user, @Valid Login login, @Valid Filiere filiere,
			@Valid String name, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "FormUser";
		user.setNom(name);
		user.setFiliere(filiere);
		login.setUsername(user.getPrenom() + user.getNom());
		login.setPwd(user.getPrenom() + user.getNom());
		login.setUser(user);
		login.setRole(new  Roles("USER"));
		user.setLogin(login);
		userdao.save(user);
		return "redirect:/admin/users";
	}

	@RequestMapping(value = "/admin/cours/save", method = RequestMethod.POST)
	public String saveCour(Model model, @Valid Cour cour, @Valid TypeCour type, BindingResult bindingResult,
			@RequestParam("uploadfile") MultipartFile file) {
		try {
			if (bindingResult.hasErrors())
				return "FormCours";
			// cour.setType(type);
			cour.setName(file.getOriginalFilename());
			cour.setMimetype(file.getContentType());
			cour.setPic(file.getBytes());
			cour.setImage("defaultCour.jpg");
			courdao.save(cour);
			return "redirect:/admin/cours";

		} catch (Exception e) {
			return "FAIL! Maybe You had uploaded the file before or the file's size > 500KB";
		}

	}

	@GetMapping("/admin/cours/edit")
	public String editCour(Model model, Long id,HttpServletRequest  req) {
		Cour cour = courdao.findById(id).get();
		model.addAttribute("cour", cour);
		model.addAttribute("title", cour.getTitle());
		model.addAttribute("type", new TypeCour());
		model.addAttribute("fichier", cour.getName());
		model.addAttribute("types", typedao.findAll());
		return "EditCour";
	}

	@RequestMapping(value = "/admin/admins/save", method = RequestMethod.POST)
	public String saveAdmin(Model model, @Valid Admin admin, Login login, BindingResult bindingResult) {

		if (bindingResult.hasErrors())
			return "FormAdmin";
		
		login.setUsername(admin.getPrenom() + admin.getNom());
		login.setPwd(admin.getPrenom() + admin.getNom());
		login.setAdmin(admin);
		login.setRole(new Roles("ADMIN"));
		admin.setLogin(login);
		admindao.save(admin);

		return "redirect:/admin/admins";
	}

	@GetMapping("/admin/users/edit")
	public String editUser(HttpServletRequest  req,Model model, Long id) {

		List<Filiere> filieres = filierdao.findAll();
		Utilisateur user = userdao.findById(id).get();
		model.addAttribute("name", user.getNom());
		model.addAttribute("user", user);
		model.addAttribute("login", user.getLogin());
		model.addAttribute("filieres", filieres);

		return "EditUser";
	}

	@GetMapping("/admin/departements/edit")
	public String editDepartement(HttpServletRequest  req,Model model, Long id) {

		Departement departement = departementdao.findById(id).get();
		List<Ecole> ecoles = ecoledao.findAll();
		model.addAttribute("departement", departement);
		model.addAttribute("ecoles", ecoles);
		return "EditDepartement";
	}

	@GetMapping("/admin/admins/edit")
	public String editAdmin(HttpServletRequest  req,Model model, Long id) {
		Admin admin = admindao.findById(id).get();
		model.addAttribute("admin", admin);
		model.addAttribute("login", admin.getLogin());

		return "EditAdmin";
	}

	@GetMapping("/admin/cours/CoursForm")
	public String formCours(HttpServletRequest  req,Model model) {
		List<TypeCour> typecours = typedao.findAll();
		Cour cours = new Cour();
		model.addAttribute("cours", cours);
		model.addAttribute("typecours", typecours);
		return "FormCours";
	}

	@GetMapping("/admin/admins/AdminsForm")
	public String formAdmins(HttpServletRequest  req,Model model) {
		Login login = new Login();
		model.addAttribute("admin", new Admin());
		model.addAttribute("login", login);
		return "FormAdmin";
	}

	@GetMapping("/admin/users/UserForm")
	public String formUser(HttpServletRequest  req,Model model) {

		List<Filiere> filieres = filierdao.findAll();

		Login login = new Login();

		Utilisateur user = new Utilisateur();

		model.addAttribute("user", user);
		model.addAttribute("name", "");
		model.addAttribute("login", login);
		model.addAttribute("filieres", filieres);
		return "FormUser";
	}

	@GetMapping("/admin/departements")
	public String departements(HttpServletRequest  req,Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {
		Page<Departement> pageDepartements = departementdao.findByIntituleContains(mc, PageRequest.of(page, 5));
		model.addAttribute("listeDepartements", pageDepartements);
		model.addAttribute("pages", new int[pageDepartements.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("mc", mc);
		return "DepartementPage";
	}

	@GetMapping("/admin/departements/delete")
	public String deleteDepartement(Model model, Long id, int page, String motCle) {
		departementdao.deleteById(id);
		return "redirect:/admin/departements?page=" + page + "&motCle=" + motCle;
	}

	@RequestMapping(value = "/admin/departements/save", method = RequestMethod.POST)
	public String saveDepartement(Model model, @Valid Departement departement, Ecole ecole,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "FormDepartement";
		System.err.println(departement.getIdDep());
		departement.setEcole(ecole);
		departementdao.save(departement);
		return "redirect:/admin/departements";
	}

	@GetMapping("/admin/departements/DepartementForm")
	public String formDepartement(HttpServletRequest  req,Model model) {
		List<Ecole> ecoles = ecoledao.findAll();
		Departement departement = new Departement();
		model.addAttribute("departement", departement);
		model.addAttribute("ecoles", ecoles);
		return "FormDepartement";
	}

	@GetMapping("/admin/filieres")
	public String filieres(HttpServletRequest  req,Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {
		Page<Filiere> pageFilieres = filierdao.findByNomContains(mc, PageRequest.of(page, 5));
		model.addAttribute("listeFilieres", pageFilieres);
		model.addAttribute("pages", new int[pageFilieres.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("mc", mc);
		return "FilierePage";
	}

	@GetMapping("/admin/filieres/delete")
	public String deleteFiliere(Model model, Long id, int page, String motCle) {
		filierdao.deleteById(id);
		return "redirect:/admin/filieres?page=" + page + "&motCle=" + motCle;
	}

	@RequestMapping(value = "/admin/filieres/save", method = RequestMethod.POST)
	public String saveFiliere(Model model, @Valid Filiere filiere, Departement departement,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "FormFiliere";
		filiere.setDepartement(departement);
		filierdao.save(filiere);
		return "redirect:/admin/filieres";
	}

	@GetMapping("/admin/filieres/edit")
	public String editFiliere(HttpServletRequest  req,Model model, Long id) {
		Filiere filiere = filierdao.findById(id).get();
		List<Departement> departements = departementdao.findAll();
		model.addAttribute("filiere", filiere);
		model.addAttribute("departements", departements);
		return "EditFiliere";
	}

	@GetMapping("/admin/filieres/FiliereForm")
	public String formFiliere(HttpServletRequest  req,Model model) {
		List<Departement> departements = departementdao.findAll();
		Filiere filiere = new Filiere();
		model.addAttribute("filiere", filiere);
		model.addAttribute("departements", departements);
		return "FormFiliere";
	}
	
	
}
