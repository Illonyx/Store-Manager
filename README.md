# Store-Manager

TRAVAUX EN COURS sur ce projet (et donc inexploitable pour le moment): 

Projet en plein refactor  : 
- Le projet sera construit avec des modules maven
- La base de données est gérée de manière totalement indépendante et décentralisée dans un module (Spring Data + Hibernate)
- L'interface graphique standalone va être isolée dans un module puis mise en pause
- L'interface en XML plutôt boiteuse sera refactorisée, une nouvelle interface en json sera développée
- Le projet s'appuiera plutôt sur une interface web
- De la documentation est en cours d'écriture

Technos : Java, Maven, Spring
Outils : Trello, Gliffy

------------------------------------------------------------------------------------------------------------------------------------

Things to do (OLD, TO UPDATE): 

~1480 lignes actuellement (Playground non compté), Probablement 700 lignes à faire en plus
--- Sauvegarde (85%): -> Moi

- Filtrer les fichiers qui n'ont pas l'extension xml
- Rajouter étoile quand non sauvegarde (Booléen HasChanged -> Utiliser Pattern Adaptateur?)
- Mettre des numéros de création derrière les new

--- Menu (35%): -> Alex 

(Ne rien faire sur la fonctionnalité Add Department du menu)
- Nouvelle fenêtre avec Checkbox à faire (load Departments) -> Definition de l'interface à faire - Inclure code Alex
(N'afficher que les départements du magasin non chargés)
- Menu d'ajout de Produits à la BDD à faire

--- Canvas (55%)

-Tooltip sur Shelves (DONE)
- Sous-menu des shelves fini graphiquement : 
	-> Dialogue créer nouveau product Place
	-> Intéractions listeners à faire, notamment avec bDD. 
- Mettre autres choses que ces foutus carrés :)
- Fonctionnalités Renommer, Supprimer à compléter dans Menu Pop-Up


--- BDD (65%)

- Spéc des stocks à faire côté tables (DONE)
- Définition des besoins de récupération de données dans l'appli, maj le modele en fonction. 
- Fin interaction DB/GUI

--------------------------------- Erreurs/Discussion----------------------------------------

Recup structure JSON : 

{
	name="Eleclerc",
	size="Hypermarché",
	category="General/Bricolage/Food/Textile..."
	gpscoord={a voir pour cet attribut},
	searchlabel="Eleclerc 71 rue Jean Jaures 92300 Lev Perret",
	imageurl="www.google.fr/eleclercFacade", (plusieurs images!)
	Le reste est à brainstormer (a un parking?, accès handicapé, horaires ouverture, )
	departments=[{
		id="FOOD"
		nom = "Secteur food",
		category = "FOOD",
		floor=1,
		shelves=[
			{
				id="surg",
				nom="Surgelés",
				representation='x:7,y:259;w=789;h=87',
				productplaces=[{
					id="belly_basket_419",
					name="belly_basket",
					position='',
					representation="image/coordonnées?"
					capacity="40",
					product={Product(id, nom, provenance, prix, poids, category, subcateg)/stocker produits en bdd},
					batches=[{
						Batch(batchCode, comingDate, dlc, currentNum, provenance)
				}]

				}]

			}

		]



	}
	]
}






