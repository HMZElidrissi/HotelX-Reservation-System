# Analyse et Modélisation du Système de Gestion de Réservation et de Facturation de l'Hôtel X

Les dirigeants d’un hôtel X font appel à vous pour analyser et modéliser leur système de gestion de réservation et de facturation. Le directeur du projet vous remet les informations décrites ci-dessous.

## Suivi des réservations

L’hôtel X offre à ses clients des chambres de différentes catégories (suites, chambres simples et chambres doubles). Les demandes de réservation des clients qui désirent séjourner à l’hôtel sont introduites au système par le biais des agents au comptoir.

### Processus de réservation

Le client téléphone à l’hôtel afin de réserver une chambre. Un agent au comptoir lui demande la date d’arrivée, la date de départ et le nombre de chambres. Pour chaque chambre, il faut préciser le nombre d’adultes et éventuellement le nombre d’enfants (avec leurs âges), ainsi que la catégorie de chambre souhaitée. Puis, il interroge l’application pour vérifier si la demande peut être satisfaite. S’il est possible de satisfaire la demande, l’agent au comptoir enregistre la réservation directement sur le système, en demandant au client les informations suivantes : nom, prénom et numéro de téléphone. Une fois la réservation enregistrée, l’agent communique au client son numéro de réservation et le prix total qu’il faut payer. À noter, que l’âge des enfants ne peut pas dépasser 12 ans. De plus, une chambre peut contenir au maximum 2 adultes.

Le client peut changer les informations de sa réservation en communiquant avec un agent de l’hôtel. Cette modification est possible jusqu’à 24h avant la date d’arrivée. Il peut également annuler sa réservation.

### Enregistrement du client

Dès qu’un client se présente au comptoir de l’hôtel, l’agent saisit son numéro de réservation, le système affiche toutes les informations personnelles du client, l’agent confirme ces informations avec le client. Il consulte la liste des chambres vacantes pour remettre la clé de la chambre au client.

## Facturation

Les chambres se réservent par nuit complète. Le prix par personne et par nuit varie en fonction de la catégorie de la chambre et des saisons. Chaque saison est caractérisée par une date de début, une date de fin, un identifiant et un nom (haute-saison, moyenne-saison et basse-saison). À noter que le 1er enfant est gratuit et le 2ème enfant est facturé à 50% du prix de l’adulte. Lorsqu’un client termine son séjour à l’hôtel, l’agent au comptoir enregistre le paiement et lui imprime la facture suite à une demande éventuelle. La facture comprend les informations personnelles du client, sa date de facturation, le montant total hors taxes et le montant TTC. La facture contient une ou plusieurs lignes de facture (par exemple : si le client a réservé 2 chambres, la facture va contenir deux lignes de facture). Elle est liée à une réservation et est caractérisée par une date et un numéro.

L’hôtel X offre à ses clients une réduction de 5% sur chaque nuit à partir de la 3ème nuit. Le paiement peut s’effectuer de plusieurs façons : Carte Bancaire, liquide ou chèque.

## Gestion des chambres

Un administrateur peut ajouter une chambre dans le système. Il peut également supprimer et modifier les informations sur une chambre, comme il peut rendre une chambre hors-service. Chaque chambre est identifiée par son numéro et son étage, le nombre de lits, et appartient à une seule catégorie.
