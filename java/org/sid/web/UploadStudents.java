package org.sid.web;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.sid.dao.DepartementRepository;
import org.sid.entities.Departement;
import org.sid.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class UploadStudents {
	private Path uploadFolder = Paths.get(System.getProperty("user.home"), "Desktop","downloads");
	@Autowired
	private StudentService ss;
	@Autowired
	private DepartementRepository dr;
	@GetMapping("/upload")
	public String openPage(Model model) {
		model.addAttribute("departements", dr.findByEcole(1));
		return "upload";
	}
	@PostMapping("/upload")
	public String handleForm(@RequestParam("list") MultipartFile file,@RequestParam(value="departement") String departementId, @RequestParam(value="promo") String promoId, @RequestParam(value="filiere") String filiereId ) {
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getOriginalFilename().endsWith(".csv"));
		System.out.println(departementId);
		if(file.getOriginalFilename().endsWith(".csv")) {
			try {
				/*File fileInSystem = new File(Paths.get(uploadFolder.toString(), file.getOriginalFilename()).toString());
				if(fileInSystem.createNewFile()) {
					FileWriter fw = new FileWriter(fileInSystem);
					fw.write(new String(file.getBytes()));
				}*/
				System.out.println(Paths.get(uploadFolder.toString(), file.getOriginalFilename()).toString());
				Files.write(Paths.get(uploadFolder.toString(), file.getOriginalFilename()), file.getBytes());
				ss.createAccounts(Paths.get(uploadFolder.toString(), file.getOriginalFilename()).toString(),Long.parseLong(promoId),Long.parseLong(filiereId));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Only csv files are allowed");
		}
		return "upload";
	}
}
