package fr.esigelec.quiz.controller.android;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author Simon

 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.esigelec.quiz.dao.PersonneDAO;
import fr.esigelec.quiz.dao.PersonneDAOImpl;
import fr.esigelec.quiz.model.Personne;

@RestController
public class InscriptionController {

	@Autowired
	@Qualifier("personneDAOImpl")
	private PersonneDAO service;

	@RequestMapping(value = "/android/inscription", method = RequestMethod.POST)
	public String inscription(@RequestParam("name") String name, @RequestParam("fullname") String fullname,
			@RequestParam("email") String email, @RequestParam("password") String password) {

		String retourJson = "";
		Personne p = new Personne();

		p.setNom(name);
		p.setPrenom(fullname);
		p.setMail(email);
		p.setMdp(password);
		p.setDroits(1);

		// Test si l'utilisateur n'est pas d�j� enregistr� dans la base de
		// donn�e
		if (service.ajouterPersonne(p) == 1) {
			retourJson = "{'status':'OK', 'message':'inscription r�ussie', 'id':" + p.getId() + "}";
		} else if (service.ajouterPersonne(p) == -1) {
			retourJson = "{'status':'KO', 'message':'email d�j� utilis�'}";
		}

		return retourJson;
	}

}
