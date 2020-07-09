package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.DossierDAO;
import dao.EvaluationDAO;
import dao.UtilisateurDAO;

import service.Critere;
import service.DossierCandidature;
import service.EtatDossier;
import service.Evaluation;
import service.GrilleEvaluation;
import service.Utilisateur;

@Controller
public class Control {

	private GrilleEvaluation grille ; 
	private Evaluation eval;
	private String dateform;
	
	
	@Autowired
	EvaluationDAO ev;
	@Autowired
	UtilisateurDAO user;
	
	@Autowired
	DossierDAO dossier;
	
	/****************************************************************** Login *****************************************************/
	@RequestMapping(value="/login")
	public String pagelogin(Model model)
	{
		return "login";
	}
	/****************************************************************** Login *****************************************************/
	
	/********************************************************* Fonctions admin *******************************************************/
	@RequestMapping(value="/tableaubord")
	public String pageTableauBord(Model model)
	{
		return "admin/tableauBord";
	}
	
	@RequestMapping(value="/gestiongrille")
	public String pageGestionGrille(Model model)
	{
		return "admin/gestionGrille";
	}
	
	@RequestMapping(value="/ajouterutilisateur")
	public String pageAjouterUtilisateur(Model model)
	{
		return "admin/ajouterUtilisateur";
	}
	/********************************************************* Fonctions admin *******************************************************/
	
	/* Fonction Receptionniste */
	@RequestMapping(value="/listcandrecep")
	public String pagelistcandrecep(Model model)
	{
		model.addAttribute("ListeCandidats", user.getAllCandidats());
		model.addAttribute("ListeDossiers", dossier.getAllDossiers());
		return "receptionniste/liste-candidats";
	}
	
	@RequestMapping(value="/ajoutercandidat")
	public String ajouterCandidat(Model model)
	{
		return "receptionniste/creerCandidat";
	}
	
	@RequestMapping(value="/checklist")
	public String verifDossier(Model model)
	{
		return "receptionniste/controlePapier";
	}
	
	
	@RequestMapping(value="/dossierAttentes")
	public String dossierenAttente(Model model)
	{
		model.addAttribute("ListeCandidats", user.getAllCandidatsAttente());
		model.addAttribute("ListeDossiers", dossier.getAllDossiers());
		return "receptionniste/DossiersAttentes";
	}
	
	
	@RequestMapping(value="/controlPapierAttente")
	public String controlPapierAttente(Model model)
	{
		model.addAttribute("ListeCandidats", user.getAllCandidatsAttente());
		model.addAttribute("ListeDossiers", dossier.getAllDossiers());
		return "receptionniste/controlePapierEnAttente";
	}
	
	@RequestMapping(value="/completerDossier")
	public String completerDossier(Model model)
	{
		user.deleteCandiatAttente(0);
		user.addUser(new Utilisateur("1","user1","nom1","username1","motdepass","Doctorant","Informatique","qs1", 111,"mail1@email.dz","Candidat"));
		dossier.addDossier(new DossierCandidature("1",EtatDossier.complet,"18/04/2018"));
		model.addAttribute("ListeCandidats", user.getAllCandidatsAttente());
		model.addAttribute("ListeDossiers", dossier.getAllDossiers());
		return "receptionniste/DossiersAttentes";
	}
	
	
	@RequestMapping(value="/listcandrecepcomplet")
	public String completerDossierdirect(Model model)
	{
		user.addUser(new Utilisateur("6","nouveauNom","nouveauPrenom","username1","motdepass","Doctorant","nouveauDomaine","qs1", 111,"mail1@email.dz","Candidat"));
		model.addAttribute("ListeCandidats", user.getAllCandidats());
		model.addAttribute("ListeDossiers", dossier.getAllDossiers());
		return "receptionniste/liste-candidats";
	}
	
	@RequestMapping(value="/listattenteincomplet")
	public String mettreDossierAttente(Model model)
	{
		user.addUserAttente(new Utilisateur("6","nouveauNom","nouveauPrenom","username1","motdepass","Doctorant","nouveauDomaine","qs1", 111,"mail1@email.dz","Candidat"));
		dossier.addDossier(new DossierCandidature("1",EtatDossier.complet,"18/04/2018"));
		model.addAttribute("ListeCandidats", user.getAllCandidatsAttente());
		model.addAttribute("ListeDossiers", dossier.getAllDossiers());
		return "receptionniste/DossiersAttentes";
	}

	
	/* Fonction  Controlleur */
	
	@RequestMapping(value="/listDossierAttente")
	public String pageControlleur (Model model)
	{
		model.addAttribute("ListeDossierAttente", dossier.dossierAttente());
		model.addAttribute("ListeCandidats", user.getAllCandidats());
				return "controlleur/attente";
	}
	
	@RequestMapping(value="/listDossierValides")
	public String pageDossierValides (Model model)
	{
		model.addAttribute("ListeDossierValides", dossier.dossierValide());
		model.addAttribute("ListeCandidats", user.getAllCandidats());
				return "controlleur/valide";
	}
	
	@RequestMapping(value="/listDossierRejetes")
	public String pageDossierRejetes (Model model)
	{
		model.addAttribute("ListeDossierRejetes", dossier.dossierRejete());
		model.addAttribute("ListeCandidats", user.getAllCandidats());
				return "controlleur/rejet";
	}
	
	@RequestMapping(value="/listDossierrRejetes")
	public String pageDossierRejetes2 (Model model)
	{
		model.addAttribute("ListeDossierRejetesAnnuler", dossier.dossierRejAnnuler());
		model.addAttribute("ListeCandidats", user.getAllCandidats2());
				return "controlleur/rejet2";
	}
	

	@RequestMapping(value="/listcandidature")
	public String pagelistcandidature(Model model)
	{
		model.addAttribute("ListeCandidatures", user.getAllCandidats());
		return "receptionniste/dossierCandidature";
	}
	@RequestMapping(value="/dossierCandidatureRejete")
	public String DossierCanRej(Model model){		
		model.addAttribute("ListeDossierRejetes", dossier.dossierRejete());
				return "controlleur/dossierCandidatureRejete";
	}
	@RequestMapping(value="/dossierCandidature")
	public String DossierCanVal(Model model){		
		model.addAttribute("ListeDossierRejetes", dossier.dossierRejete());
		return "controlleur/dossierCandidature";
	}
	@RequestMapping(value="/dossierCandidatureValide")
	public String DossierCan(Model model){		
		return "controlleur/dossierCandidatureValide";
	}
	
	/* Membre commission */
	
	@RequestMapping(value="/GrillEval")
	public String pageGrillEvall(Model model ){
		// trai....
		model.addAttribute("ListeCriteres_Pedagogique", ev.getAllcriteres_Pedagogique());
		model.addAttribute("ListeCriteres_Encadrement", ev.getAllcriteres_Encadrement());
		model.addAttribute("ListeCriteres_Production_activités_scientifique", ev.getAllcriteres_Production_activités_scientifique());
		model.addAttribute("ListeCriteres_Responsabilités_administratives", ev.getAllcriteres_Responsabilités_administratives());
		return "membrecomm/grillEval";
	}
	
	@RequestMapping(value="/ResultEval")
	public String pageResultEvall(Model model){
		model.addAttribute("ListeCandidats", user.getAllCandidats());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd ");
		Date date = new Date();
		dateform = dateFormat.format(date);
		model.addAttribute("Dateauj",dateform);
		
		
		return "membrecomm/resulteval";
	}
	
	@RequestMapping(value="/ListeCand")
	public String ListeCands(Model model){
		
		model.addAttribute("ListeCandidats", user.getAllCandidats());
		
		return "membrecomm/listcandidat";
	}
	@RequestMapping(value="/dossiercomm")
	public String DossierCanComm(Model model){		
		return "membrecomm/dossierCandidature";
	}
	
	
	/* Fonctions candidat */
	
	
	
	@RequestMapping(value="/moncompte")
	public String CompteCandidat(Model model){		
		return "candidat/etat_dossier_candidat";
	}
	
	
	@RequestMapping(value="/upload_candidat")
	public String CompteCandidatUpload(Model model){		
		return "candidat/IHM_candidat";
	}
	

	/*
	
	@RequestMapping(value="/test")
	public String pageIndex(Model model){
		// trai....
		model.addAttribute("ListeUtilisateurs", serv.getAllUsers());
		return "utilisateurs";
	}
	
	@RequestMapping(value="/supprimerUtilisateur")
	public String supprimerUtilisateur(Model model,@RequestParam String ID_user){
		
		
		System.out.println(ID_user);
		serv.deleteUser(ID_user);
		model.addAttribute("ListeUtilisateurs", serv.getAllUsers());
		model.addAttribute("ID_user",ID_user);

		return "utilisateurs";
	}
	
	@RequestMapping(value="/rechercherUtilisateur")
	public String rechercherUtilisateur(Model model,@RequestParam String ID_user){
		
		
		List<Utilisateur> liste = new ArrayList<Utilisateur>();
		liste.add(serv.getUserByID(ID_user));
		model.addAttribute("ListeUtilisateurs", liste);
		model.addAttribute("ID_user",ID_user);
		return "utilisateurs";
	}
	
	@RequestMapping(value="/ajouterUtilisateur")
	public String ajouterUtilisateur(Model model,Utilisateur u){
		serv.addUser(u);
		model.addAttribute("ListeUtilisateurs", serv.getAllUsers());
		return "utilisateurs";
	}
	
	@RequestMapping(value="/candidatsAttente")
	public String afficherCandattente(Model model)
	{
		model.addAttribute("ListeCandidats",serv.getAllCandidats());
		return "tables/attente";
	}

	
	@RequestMapping(value="/accueil")
	public String pageAccueil(Model model){
		// trai....
		model.addAttribute("ListeCriteres", ev.getAllCriteres());
		return "Evaluation";
	}
	
	@RequestMapping(value="/ajouterCritere")
	public String ajouterCritere(Model model,Critere c){
		
		
		ev.ajouterCritere(c);
		model.addAttribute("ListeCriteres", ev.getAllCriteres());

		return "Evaluation";
	}
	

	@RequestMapping(value="/evaluer")
	public String pageEvaluation(Model model){
		// trai....
		model.addAttribute("ListeCriteres", ev.getAllCriteres());
		return "teste";
	}
	
	@RequestMapping(value="/valider")
	public String pageValidation(Model model /*,@RequestParam(value ="inote") int note ){
		// trai....
		
		this.grille = new GrilleEvaluation(ev.getAllCriteres());
		for(Critere c : model.ListeCriteres) {
			   System.out.println(s);
			}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		dateform = dateFormat.format(date);
	   this.ev.createEvaluation(ev.getAllEvaluations().size() + 1,date,this.grille,ev.getAllEvaluations().size()+2);
		model.addAttribute("Dateauj",dateform);
		model.addAttribute("ListeEvaluations", ev.getAllEvaluations());

		System.out.println(dateform);
		return "teste";
	}*/
	
}
